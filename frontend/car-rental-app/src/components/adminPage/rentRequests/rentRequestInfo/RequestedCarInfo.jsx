import React from "react";
import { Container, Paper, Typography, Grid, Divider } from "@material-ui/core";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";

const API_URL = "http://localhost:8080";

const RequestedCarInfo = ({ carDTO }) => (
  <>
    <Typography>Requested car:</Typography>
    <Grid container direction="row" justify="flex-start" alignItems="center">
      <Grid item>
        <CarImage
          src={API_URL + "/u/car/download-car-image/" + carDTO.licensePlate}
        />
      </Grid>
      <Grid item>
        <CarInfo car={carDTO} />
      </Grid>
    </Grid>
  </>
);

export default RequestedCarInfo;
