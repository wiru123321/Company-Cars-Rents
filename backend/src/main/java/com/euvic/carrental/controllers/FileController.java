package com.euvic.carrental.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("file/")
@CrossOrigin
public class FileController {

    private static final String imageDirectory = System.getProperty("user.dir") + "/images/";

    @RequestMapping(method = RequestMethod.POST, value = "/upload", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") final MultipartFile file,
                                         @RequestParam("imageName") final String name) {
        this.makeDirectoryIfNotExist(imageDirectory);
        final Path fileNamePath = Paths.get(imageDirectory, name);

        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        } catch (final IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    private void makeDirectoryIfNotExist(final String imageDirectory) {
        final File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
