POST API request

bash: curl -X POST -F "file1=@images/elon_musk.jpg" -F "file2=@images/mark.jpg" http://localhost:8888/features/upload
This command should be correctly refer the image in local env

result: {"iamge": {base64 information of converted image}, "message": {Faces swapped successfully.}}
