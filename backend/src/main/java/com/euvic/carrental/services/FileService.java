package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    //TODO tests
    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/images";
    private static final String CAR_IMAGE_DIRECTORY = System.getProperty("user.dir") + "/images/cars";

    public ResponseEntity<?> uploadCarImage(MultipartFile file) {
        final Path fileNamePath = Paths.get(CAR_IMAGE_DIRECTORY, generateNextInDirFileName(CAR_IMAGE_DIRECTORY,"car_image"));
        setUpDirectories();
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(fileNamePath, HttpStatus.CREATED);
        } catch (final IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    private void setUpDirectories(){
        this.makeDirectoryIfNotExist(IMAGE_DIRECTORY);
        this.makeDirectoryIfNotExist(CAR_IMAGE_DIRECTORY);
    }

    private void makeDirectoryIfNotExist(final String imageDirectory) {
        final File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private String generateNextInDirFileName(String dirPath, String mainNamePart){
        int inDirPhotoNumber = new File(dirPath).list().length+1;
        String photoName = String.format("%s%d.png", mainNamePart, inDirPhotoNumber);
        return photoName;
    }
}
