package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    final private CarService carService;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload";
    private static final String STATIC_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static";
    private static final String IMAGES_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static/images";
    private static final String CAR_IMAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/upload/static/images/cars";

    public FileService(CarService carService) {
        this.carService = carService;
    }

    public String uploadCarImage(MultipartFile file) throws IOException {
        setUpDirectories();
        final String fileName = generateNextInDirFileName(CAR_IMAGE_DIRECTORY,"car_image");
        final Path filePath = Paths.get(CAR_IMAGE_DIRECTORY, fileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (final IOException e) {
            e.printStackTrace();
            throw new IOException("Image save in directory error.");
        }

        return filePath.toString();
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

    public byte[] downloadCarImage(String licensePlate) throws NullPointerException, IOException {
        Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
        String photoPath = car.getImagePath();
        File filePath;
        try{
            filePath = new File(photoPath);
        }catch (NullPointerException e){
            e.printStackTrace();
            throw e;
        }

        byte[] image;
        try {
            image = Files.readAllBytes(filePath.toPath());
        }catch (IOException e){
            e.printStackTrace();
            throw new IOException("Reading image bytes error.");
        }

        return image;
    }
}
