package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    final private CarService carService;

    //TODO tests
    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources";
    private static final String CAR_IMAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources";

    public FileService(CarService carService) {
        this.carService = carService;
    }

    public ResponseEntity<?> uploadCarImage(MultipartFile file) {
        final String fileName = generateNextInDirFileName(CAR_IMAGE_DIRECTORY,"car_image");
        final Path fileNamePath = Paths.get(CAR_IMAGE_DIRECTORY, fileName);
        setUpDirectories();
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(fileName, HttpStatus.CREATED);
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

    public ResponseEntity<byte[]> downloadCarImage(String licensePlate) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        Car car = carService.getEntityByLicensePlate(licensePlate);
        String photoPath = car.getImageName();
        InputStream in = ClassLoader.getSystemResourceAsStream("car_image5.png");
        if (in == null) {
            throw new FileNotFoundException("readFilesInBytes: File " + photoPath
                    + " does not exist");
        }
        byte[] media = IOUtils.toByteArray(in);

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
