# Project Name

This repository contains the code for a feature that allows users to merge the faces of two individuals.

## Description

The project implements a feature that enables users to merge the faces of two individuals in an image. It utilizes computer vision techniques to detect and swap the faces, resulting in a combined image.

## Changes from Proposal

There were no significant changes from the initial proposal.

## REST APIs Implemented

### Merge Faces API

- **Description:** This API merges the faces of two individuals in an image.
- **HTTP Method:** POST
- **Endpoint:** `/features/upload`
- **Request Parameters:**
  - `file1`: The image file of the first individual.
  - `file2`: The image file of the second individual.
- **Response:** 
  - `image`: Base64 encoded string of the resulting image.
  - `message`: A confirmation message indicating the successful face swap.

## Example Curl Commands

### POST API Request

curl -X POST -F "file1=@images/elon_musk.jpg" -F "file2=@images/mark.jpg" http://localhost:8888/features/upload

Note: Replace http://localhost:8888 with the appropriate server address

#### Expected Output:

{
  "image": "Base64_encoded_image",
  "message": "Faces swapped successfully."
}

This command uploads two image files (elon_musk.jpg and mark.jpg) and swaps the faces of the individuals. The response contains the merged image in base64 format along with a success message.
