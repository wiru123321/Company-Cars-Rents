import React, { useEffect } from "react";
import CarInfo from "../../carsListing/CarInfo";
import CarImage from "../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import { Container, List, ListItem } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  fetchCars,
  selectCars,
  deleteCar,
} from "../../../features/car-reservation/reservationSlice";

const RemoveCar = () => {
  const cars = useSelector(selectCars);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchCars());
  }, []);

  const handleCarDelete = (index) => {
    let { licensePlate } = cars[index];
    dispatch(deleteCar(licensePlate));
  };

  return (
    <Container>
      <List>
        {cars.map((car, index) => {
          return (
            <ListItem key={index}>
              <CarImage src={car.src} />
              <CarInfo car={car} />
              <CarControlPanel index={index} onDelete={handleCarDelete} />
            </ListItem>
          );
        })}
      </List>
    </Container>
  );
};

export default RemoveCar;
