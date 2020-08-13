import React, { useEffect } from "react";
import {
  Grid,
  Button,
  Box,
  ListItem,
  List,
  Container,
} from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { useSelector, useDispatch } from "react-redux";
import {
  selectReservation,
  chooseCar,
  fetchReservation,
  fetchCarImage,
  selectImg,
  updateCar,
} from "../../../features/your-cars/yourCarsSlice";

// TODO Fetch users cars from api.

const YourCarsList = () => {
  const dispatch = useDispatch();
  const reservations = useSelector(selectReservation);
  const img = useSelector(selectImg);
  useEffect(() => {
    dispatch(fetchReservation());
  }, []);
  return (
    <Container
      style={{
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
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
    </Container>
  );
};

export default YourCarsList;
