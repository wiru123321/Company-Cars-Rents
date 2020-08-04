import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Button, Box } from "@material-ui/core";
import useStyles from "./useStyles";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";
import {
  selectCar,
  selectIsChoosen,
  toggleChoose,
  undoChoose,
} from "../../../features/car-reservation/reservationSlice";

const SelectedCar = () => {
  const dispatch = useDispatch();
  const isChoosen = useSelector(selectIsChoosen);
  const car = useSelector(selectCar);

  const toggleCarChoose = () => dispatch(toggleChoose());

  const undoSelection = () => dispatch(undoChoose());

  const SuggestButton = () => (
    <Grid container direction="column" justify="center" alignItems="center">
      <Button onClick={toggleCarChoose} variant="contained" color="primary">
        Suggest a car
      </Button>
    </Grid>
  );

  const Car = ({ car }) => (
    <div>
      <Box display="flex">
        <CarImage src={car.src} />
        <CarInfo car={car} />
      </Box>
      <div className={useStyles.btnPanel}>
        <Button onClick={toggleCarChoose} variant="contained" color="primary">
          Change car
        </Button>
        <Button onClick={undoSelection} variant="contained" color="secondary">
          Undo selection
        </Button>
      </div>
    </div>
  );

  if (!isChoosen) {
    return <SuggestButton />;
  } else {
    return <Car car={car} />;
  }
};

export default SelectedCar;
