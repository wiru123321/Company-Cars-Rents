package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("h2")
public class FileServiceTest {
    private static final String sampleCarLicensePlate = "WN101";
    private static final String urlControllerPath = String.format("/a/car/upload-car-image/%s", sampleCarLicensePlate);
    private static final String targetFolder = System.getProperty("user.dir") + "/src/main/upload/static/images/cars";
    private static final String mainTestFileCreatedDuringTestNamePart = "car_image";

    private static MockMvc mockMvc;
    private static DefaultMockMvcBuilder builder;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GearboxTypeService gearboxTypeService;
    @Autowired
    private GearboxTypeRepository gearboxTypeRepository;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private MarkService markService;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private ColourService colourService;
    @Autowired
    private ColourRepository colourRepository;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    public void setUp(){
        builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();


        final GearboxType gearboxType1 = new GearboxType(null, "Manual");
        final GearboxType gearboxType2 = new GearboxType(null, "Automatic");
        final GearboxType gearboxType3 = new GearboxType(null, "Other");

        gearboxTypeRepository.save(gearboxType1);
        gearboxTypeRepository.save(gearboxType2);
        gearboxTypeRepository.save(gearboxType3);


        final FuelType fuelType1 = new FuelType(null, "Gasoline");
        final FuelType fuelType2 = new FuelType(null, "Petrol");
        final FuelType fuelType3 = new FuelType(null, "Diesel");

        fuelTypeRepository.save(fuelType1);
        fuelTypeRepository.save(fuelType2);
        fuelTypeRepository.save(fuelType3);

        final Mark mark1 = new Mark(null, "Audi");
        final Mark mark2 = new Mark(null, "Opel");
        final Mark mark3 = new Mark(null, "BMW");

        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);

        final Colour colour1 = new Colour(null, "Red");
        final Colour colour2 = new Colour(null, "Blue");
        final Colour colour3 = new Colour(null, "Green");

        colourRepository.save(colour1);
        colourRepository.save(colour2);
        colourRepository.save(colour3);

        final Type type1 = new Type(null, "Sedan");
        final Type type2 = new Type(null, "Coupe");
        final Type type3 = new Type(null, "Van");

        typeRepository.save(type1);
        typeRepository.save(type2);
        typeRepository.save(type3);
    }

    @AfterEach
    void tearDown() {
        carRepository.deleteAll();
        gearboxTypeRepository.deleteAll();
        fuelTypeRepository.deleteAll();
        modelRepository.deleteAll();
        markRepository.deleteAll();
        parkingRepository.deleteAll();
        colourRepository.deleteAll();
        typeRepository.deleteAll();
    }

    @Test
    public void testController() throws Exception {
        final Model model1 = new Model(null, "C350", markService.getEntityByName("Audi"));
        final Model model2 = new Model(null, "Astra", markService.getEntityByName("Opel"));
        final Model model3 = new Model(null, "M5", markService.getEntityByName("BMW"));

        final Long modelId1 = modelService.addEntityToDB(model1);
        final Long modelId2 = modelService.addEntityToDB(model2);
        final Long modelId3 = modelService.addEntityToDB(model3);


        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId = parkingService.addEntityToDB(parking);

        final Car car = new Car(null, "WN101", 100, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 1990, true, 200000, modelService.getEntityByName("C350"),
                parkingService.getEntityById(parkingId), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));

        carService.addEntityToDB(car);


        File sampleFile = new File(targetFolder+"/tests/test_sample.png");

        int inDirPhotoNumber = new File(targetFolder).list().length+1;
        String photoName = String.format("%s%d.png", mainTestFileCreatedDuringTestNamePart, inDirPhotoNumber);
        final Path pathForFileCreatedDuringTests = Paths.get(targetFolder, photoName);

        MockMultipartFile file = new MockMultipartFile("imageFile", photoName, MediaType.IMAGE_PNG_VALUE, Files.readAllBytes(sampleFile.toPath()));
        mockMvc.perform(MockMvcRequestBuilders.multipart(urlControllerPath).file(file))
                .andDo(print())
                .andExpect(status().isInternalServerError());
        assertTrue(pathForFileCreatedDuringTests.toFile().exists());

        File fileCreatedDuringTests = new File(pathForFileCreatedDuringTests.toString());
        fileCreatedDuringTests.delete();
    }
}
