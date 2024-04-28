package com.example.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream; // Import for InputStream
import java.io.BufferedReader; // Import for BufferedReader
import java.io.InputStreamReader; // Import for InputStreamReader

@RestController
@RequestMapping("/features")
public class Faceswap {
    Path tempDir = null;
    Path tempFile1 = null;
    Path tempFile2 = null;

    @GetMapping("/apitest")
    public ResponseEntity<String> testServer() {
        return ResponseEntity.ok("Server is running.");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        if (file1.isEmpty() || file2.isEmpty()) {
            return new ResponseEntity<>("Please select two files to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            tempDir = Files.createTempDirectory("uploads-");
            tempFile1 = Files.createTempFile(tempDir, "upload1-", ".jpg");
            tempFile2 = Files.createTempFile(tempDir, "upload2-", ".jpg");
            file1.transferTo(tempFile1);
            file2.transferTo(tempFile2);

            Path resultFile = runPythonScript(tempFile1, tempFile2);

            if (!Files.exists(resultFile)) {
                throw new IOException("Result file not found after script execution at " + resultFile);
            }

            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(resultFile));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resultFile.getFileName().toString() + "\"");

            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files: " + e.getMessage());
        } finally {
            cleanUpFiles(tempFile1, tempFile2, tempDir);
        }
    }

    private void cleanUpFiles(Path... paths) {
        for (Path path : paths) {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                System.err.println("Failed to delete " + path + ": " + e.getMessage());
            }
        }
    }



    private Path runPythonScript(Path sourcePath, Path targetPath) throws IOException, InterruptedException {
        // Assuming the Python script is in the root of the project directory and named 'process_images.py'
        String scriptPath = "main.py";

        // Build the command to run the Python script with the given source and target images
        ProcessBuilder builder = new ProcessBuilder(
                "python", scriptPath,
                "--source_img", sourcePath.toString(),
                "--target_img", targetPath.toString()
        );
        // Set the working directory if needed, otherwise the script will run in the current directory
        builder.directory(new File("/projects/milestone1/faceswap"));

        // Redirect the error stream to the standard output stream
        builder.redirectErrorStream(true);

        // Start the process
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("Script output:\n" + output.toString());
            throw new RuntimeException("Python script execution failed with exit code: " + exitCode);
        }

        // Return the path to the output file, assuming that it's named 'result.jpg' and located in 'faceswap/outputs'
        return Paths.get("/projects/milestone1/faceswap/outputs/result.jpg");  // Use absolute path for clarity
    }
}