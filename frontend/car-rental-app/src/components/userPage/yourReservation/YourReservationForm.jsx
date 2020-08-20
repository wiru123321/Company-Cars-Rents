import React from "react";
import { Button, Box, ListItem, List, Grid, Paper } from "@material-ui/core";
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
          <Paper elevation={6} style={{ marginBottom: "1vh" }}>
            <ListItem key={index}>
              <Box display="flex">
                <CarImage
                  src={
                    "http://localhost:8080/u/car/download-car-image/" +
                    reservation.carDTO.licensePlate
                  }
                />
                <CarInfo car={reservation.carDTO} />
                <Grid
                  container
                  direction="column"
                  justify="center"
                  alignItems="flex-start"
                >
                  <Box>
                    <label>Rent date from: {reservation.dateFrom}</label>
                  </Box>
                  <Box>
                    <label>Rent date to: {reservation.dateTo}</label>
                  </Box>
                </Grid>
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
          </Paper>
        ))}
      </List>
    </Box>
  );
};

export default YouReservationForm;
