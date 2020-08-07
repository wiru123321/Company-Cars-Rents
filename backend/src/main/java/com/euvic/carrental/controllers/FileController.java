package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/images";
    private static final String CAR_IMAGE_DIRECTORY = System.getProperty("user.dir") + "/images/cars";

    @Autowired CarService carService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload-car-image/{licensePlate}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadCarImage(@RequestParam("imageFile") final MultipartFile file, @PathVariable final String licensePlate) {
        setUpDirectories();

        final Path fileNamePath = Paths.get(CAR_IMAGE_DIRECTORY, generateNextInDirFileName(CAR_IMAGE_DIRECTORY,"car_image"));
        try {
            Files.write(fileNamePath, file.getBytes());
            Car car = carService.getEntityByLicensePlate(licensePlate);
            car.setPhotoFolderPath(fileNamePath.toString());
            carService.addEntityToDB(car);

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
