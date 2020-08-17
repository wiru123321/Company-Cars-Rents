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
  dateIsChoosenHandler,
  isCarFormActiveHandler,
} from "../../../features/car-reservation/reservationSlice";

const SelectedCar = () => {
  const dispatch = useDispatch();
  const isChoosen = useSelector(selectIsChoosen);
  const car = useSelector(selectCar);

  const toggleCarChoose = () => dispatch(toggleChoose());

  const undoSelection = () => {
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
  };

  // const SuggestButton = () => (
  //   <Grid container direction="column" justify="center" alignItems="center">
  //     <Button onClick={toggleCarChoose} variant="contained" color="primary">
  //       Suggest a car
  //     </Button>
  //   </Grid>
  // );

  const Car = ({ car }) => (
    <div>
      <Grid
        container
        justify="center"
        alignItems="center"
        style={{ marginLeft: "22%" }}
      >
        <Box display="flex">
          <CarImage
            src={
              "http://localhost:8080/u/car/download-car-image/" +
              car.licensePlate
            }
          />
          <CarInfo car={car} />
        </Box>
        <Box display="flex">
          <Button onClick={toggleCarChoose} variant="contained" color="primary">
            Change car
          </Button>
        </Box>
        <Box display="flex">
          <Button onClick={undoSelection} variant="contained" color="secondary">
            Undo selection
          </Button>
        </Box>
      </Grid>
    </div>
  );

  if (!isChoosen) {
  } else {
    return <Car car={car} />;
  }
};

export default SelectedCar;
