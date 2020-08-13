import React from "react";
import { Button, Box, ListItem, List } from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { chooseCar } from "../../../features/your-cars/yourCarsSlice";
import { useDispatch } from "react-redux";

const YouReservationForm = ({ reservations }) => {
  const dispatch = useDispatch();
  return (
    <div>
      <Box display="flex" justifyContent="center" alignItems="center">
        <List>
          {reservations.map((reservation, index) => (
            <ListItem key={index}>
              <Box display="flex">
                <CarImage
                  src={
                    "http://localhost:8080/u/car/download-car-image/" +
                    reservation.carDTO.licensePlate
                  }
                />
                <CarInfo car={reservation.carDTO} />
              </Box>
              <Button
                variant="outlined"
                color="primary"
                onClick={(event) => {
                  event.preventDefault();
                  // var newCar = {
                  //   ...car,
                  //   isActive: false,
                  // };
                  // dispatch(updateCar(reservation.CarDTO.licensePlate, newCar));
                  dispatch(chooseCar(index));
                }}
              >
                Give the car back
              </Button>
            </ListItem>
          ))}
        </List>
      </Box>
    </div>
  );
};

export default YouReservationForm;
