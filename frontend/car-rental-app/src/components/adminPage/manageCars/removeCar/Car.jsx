import React, { useState } from "react";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import UpdateCars from "../updateCars/UpdateCars";
import { ListItem, Grid } from "@material-ui/core";

const Car = ({ car, index, onDelete }) => {
  const [edit, setEdit] = useState(false);

  const toggleEdit = () => {
    setEdit(!edit);
  };

  return (
    <ListItem>
      <Grid container justify="center" alignItems="center">
        {edit ? (
          <Grid item xs={8}>
            <UpdateCars car={car} />
          </Grid>
        ) : (
          <>
            <Grid item xs={4}>
              <CarImage src={car.src} />
            </Grid>
            <Grid item xs={4}>
              <CarInfo car={car} />
            </Grid>
          </>
        )}

        <Grid item xs={4}>
          <CarControlPanel
            index={index}
            onDelete={onDelete}
            toggleEdit={toggleEdit}
          />
        </Grid>
      </Grid>
    </ListItem>
  );
};

export default Car;
