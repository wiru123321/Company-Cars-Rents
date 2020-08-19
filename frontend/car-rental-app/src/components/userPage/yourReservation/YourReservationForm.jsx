import React from "react";
import { Button, Box, ListItem, List } from "@material-ui/core";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import { chooseCar } from "../../../features/your-cars/yourCarsSlice";
import { useDispatch } from "react-redux";

const YouReservationForm = ({ reservations, isActive }) => {
  const dispatch = useDispatch();

  return (
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
              <Box>
                <label>Rent date from: {reservation.dateFrom}</label>
              </Box>
              <Box>
                <label>Rent date to: {reservation.dateTo}</label>
              </Box>
            </Box>
            <Box display="flex">
              <Box>
                {isActive ? (
                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={(event) => {
                      dispatch(chooseCar(index));
                    }}
                  >
                    Give the car back
                  </Button>
                ) : null}
              </Box>
            </Box>
          </ListItem>
        ))}
      </List>
    </Box>
  );
};

export default YouReservationForm;
