package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    final private CarService carService;

    //TODO tests
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload";
    private static final String STATIC_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static";
    private static final String IMAGES_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static/images";
    private static final String CAR_IMAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static/images/cars";

    public FileService(CarService carService) {
        this.carService = carService;
    }

    public ResponseEntity<?> uploadCarImage(MultipartFile file) {
        setUpDirectories();
        final String fileName = generateNextInDirFileName(CAR_IMAGE_DIRECTORY,"car_image");
        final Path fileNamePath = Paths.get(CAR_IMAGE_DIRECTORY, fileName);
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(fileNamePath, HttpStatus.CREATED);
        } catch (final IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    private void setUpDirectories(){
        this.makeDirectoryIfNotExist(UPLOAD_DIRECTORY);
        this.makeDirectoryIfNotExist(STATIC_DIRECTORY);
        this.makeDirectoryIfNotExist(IMAGES_DIRECTORY);
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

    public ResponseEntity<?> downloadCarImage(String licensePlate) throws IOException {
        Car car = carService.getEntityByLicensePlate(licensePlate);
        String photoPath = car.getImagePath();
        File filePath;
        try{
            filePath = new File(photoPath);
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car image not found.");
        }

        byte[] image;
        try {
            image = Files.readAllBytes(filePath.toPath());
        }catch (IOException e){
            e.printStackTrace();
            throw new IOException("Reading image bytes error. Exception: " + e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
