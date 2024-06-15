$(document).ready(function() {
    let convertedImageBlob = null; // Variable to hold the converted image blob

    $("#convert-btn").click(function (event) {
        event.preventDefault();

        var formData = new FormData();
        var faceImage = document.getElementById('faceImage').files[0];
        var posterImage = document.getElementById('posterImage').files[0];

        if (faceImage && posterImage) {
            formData.append("file2", faceImage);
            formData.append("file1", posterImage);

            // Show loading indicator
            $("#result-container").html('');
            $("#loading").show();
            $("#save-btn").hide();

            fetch("http://localhost:8080/features/upload", {
                method: "POST",
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    return response.blob();
                }
                return response.json().then(err => { throw err; });
            })
            .then(blob => {
                convertedImageBlob = blob;

                var img = new Image();
                img.onload = function() {
                    // Resize the image 
                    var maxWidth = 500; 
                    var maxHeight = 500; 
                    var ratio = 0;  

                    if (img.width > maxWidth) {
                        ratio = maxWidth / img.width;  
                        img.width = maxWidth;         
                        img.height = img.height * ratio;    
                    }

                    if (img.height > maxHeight) {
                        ratio = maxHeight / img.height; 
                        img.height = maxHeight;   
                        img.width = img.width * ratio;    
                    }

                    // Hide loading indicator
                    $("#loading").hide();
                    $("#result-container").append(img);
                    $("#save-btn").show(); 
                };
                img.src = URL.createObjectURL(blob);
            })
            .catch((error) => {
                console.error('Error:', error);
                alert("An error occurred while processing your request.");
                // Hide loading indicator on error
                $("#loading").hide();
            });
        } else {
            alert("Please select both images before converting.");
        }
    });

    $("#save-btn").click(function () {
        if (convertedImageBlob) {
            const url = URL.createObjectURL(convertedImageBlob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'converted-image.jpg';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            URL.revokeObjectURL(url);
        }
    });
});
