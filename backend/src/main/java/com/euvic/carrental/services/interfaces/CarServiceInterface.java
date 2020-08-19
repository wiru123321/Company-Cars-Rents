package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.responses.CarDTO;

import java.util.List;

public interface CarServiceInterface {

    Boolean checkIfOnCompanyCarWithLicensePlateExists(String licensePlate);

    Car mapRestModel(Long id, CarDTO carDTO, Long parkingId, Long modelId);

    Car getOnCompanyEntityByLicensePlate(String licensePlate);

    CarDTO mapToCarDTO(Car car);

    CarDTO getDTOByLicensePlate(String licensePlate);

    Long addEntityToDB(Car car);

    Long updateCarInDB(String oldCarLicensePlate, CarDTO newCarDTO);

    Long setCarIsNotInCompany(String licensePlate);

    Long addExistingImageToExistingCar(String carImagePath, String licensePlate);

    Long setCarActivity(Boolean isActive, String licensePlate);

    List<CarDTO> getAllDTOs();

    List<CarDTO> getInCompanyCarDTOs();

    List<CarDTO> getInCompanyActiveCarDTOs();

    List<CarDTO> getInCompanyInactiveCarDTOs();
}
