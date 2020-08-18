import React from "react";
import CarImage from "../../../carsListing/CarImage";
import CarInfo from "../../../carsListing/CarInfo";
import { Grid } from "@material-ui/core";

const API_URL = "http://localhost:8080";

const CarInfoCard = ({ car }) => {
  return (
    <Grid container justify="center">
      <Grid item>
        <CarImage
          src={API_URL + "/u/car/download-car-image/" + car.licensePlate}
        />
      </Grid>
      <Grid item>
        <CarInfo car={car} />
      </Grid>
    </Grid>
  );
};
export default CarInfoCard;
