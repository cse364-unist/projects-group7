package com.example.features;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FaceswapTest {

    //File img1 = new File("/projects/milestone2/faceswap/images/elon_musk.jpg");
    //File img2 = new File("/projects/milestone2/faceswap/images/mark.jpg");

    @Autowired
    private MockMvc mockMvc;

    //private MockMultipartFile file1;
    //private MockMultipartFile file2;

    private MockMultipartFile file1;
    private MockMultipartFile file2;



    @BeforeEach
    public void setUp() throws IOException {
        Path path1 = Paths.get("/projects/milestone1/faceswap/images/elon_musk.jpg");
        Path path2 = Paths.get("/projects/milestone1/faceswap/images/mark.jpg");

        byte[] content1 = Files.readAllBytes(path1);
        byte[] content2 = Files.readAllBytes(path2);

        file1 = new MockMultipartFile("file1", "elon_musk.jpg", "image/jpg", content1);
        file2 = new MockMultipartFile("file2", "mark.jpg", "image/jpg", content2);
    }


    @Test
    public void testHandleFileUpload_NonEmptyFiles() throws Exception {
        mockMvc.perform(multipart("/features/upload")
                        .file(file1)
                        .file(file2))
                .andExpect(result -> {
                    if (result.getResolvedException() != null) {
                        // Log the exception to understand why there is a 500 error
                        System.out.println("Exception during test: " + result.getResolvedException().getMessage());
                        result.getResolvedException().printStackTrace();
                    }
                })
                .andExpect(status().isOk());
    }


    @Test
    public void testHandleFileUpload_EmptyFiles() throws Exception {
        MockMultipartFile emptyFile1 = new MockMultipartFile("file1", "empty1.jpg", "image/jpeg", new byte[0]);
        MockMultipartFile emptyFile2 = new MockMultipartFile("file2", "empty2.jpg", "image/jpeg", new byte[0]);

        mockMvc.perform(multipart("/features/upload")
                        .file(emptyFile1)
                        .file(emptyFile2))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Please select two files to upload."));
    }
}
