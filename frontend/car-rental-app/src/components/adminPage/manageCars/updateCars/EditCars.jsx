import React, { useState, useEffect } from "react";
import { ValidatorForm } from "react-material-ui-form-validator";
import { useDispatch } from "react-redux";
import { updateCar } from "../../../../features/car-management/carManagerSlice";
import UpdateCarsForm from "./UpdateCarsForm";

const EditCars = ({ car }) => {
  const dispatch = useDispatch();
  const [licensePlate, setLicensePlate] = useState(car.licensePlate);
  const [mileage, setMileage] = useState(car.mileage);
  const [lastInspection, setLastInspection] = useState(
    car.lastInspection.slice(0, 10)
  );
  const exLicensePlate = car.licensePlate;

  const handleSubmit = (event) => {
    event.preventDefault();
    let newCar = {
      ...car,
      licensePlate: licensePlate,
      mileage: mileage,
      lastInspection: `${lastInspection}T00:00:00`,
    };
    dispatch(updateCar(exLicensePlate, newCar));
  };

  return (
    <ValidatorForm onSubmit={handleSubmit}>
      <UpdateCarsForm
        licensePlate={licensePlate}
        mileage={mileage}
        lastInspection={lastInspection}
        licensePlateChange={setLicensePlate}
        mileageChange={setMileage}
        lastInspectionChange={setLastInspection}
      />
    </ValidatorForm>
  );
};

export default EditCars;
