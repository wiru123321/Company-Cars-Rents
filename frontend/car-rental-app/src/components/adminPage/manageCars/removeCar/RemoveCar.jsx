import React, { useEffect, useState } from "react";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import UpdateCars from "../updateCars/UpdateCars";
import { Container, List, ListItem, Grid } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  fetchCars,
  selectCars,
  deleteCar,
} from "../../../../features/car-reservation/reservationSlice";

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
        {cars.map((car, index) => (
          <Car key={index} car={car} index={index} onDelete={handleCarDelete} />
        ))}
      </List>
    </Container>
  );
};

const Car = ({ car, index, onDelete }) => {
  const [edit, setEdit] = useState(false);

  const toggleEdit = () => {
    setEdit(!edit);
  };

  return (
    <ListItem>
      <Grid container justify="center" alignItems="center">
        <Grid item>
          <CarImage src={car.src} />
        </Grid>
        <Grid item>
          <CarInfo car={car} />
        </Grid>
        {edit && <UpdateCars />}
        <Grid item>
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

export default RemoveCar;
