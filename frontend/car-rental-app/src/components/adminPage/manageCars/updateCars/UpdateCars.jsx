import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Paper, Button, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import { ValidatorForm } from "react-material-ui-form-validator";
import UpdateCarsForm from "./UpdateCarsForm";
import AddFault from "./AddFault";
import {
  updateCar,
  addFault,
} from "../../../../features/car-management/carManagerSlice";

const useStyles = makeStyles({
  root: {
    padding: "8px",
  },
  form: {
    marginTop: "4%",
  },
});

const EditCars = ({ car }) => {
  const dispatch = useDispatch();
  const [licensePlate, setLicensePlate] = useState(car.licensePlate);
  const [mileage, setMileage] = useState(car.mileage);
  const [lastInspection, setLastInspection] = useState(
    car.lastInspection.slice(0, 10)
  );
  const exLicensePlate = car.licensePlate;

  const handleSubmit = (event) => {
    event.preventDefault();
    let newCar = {
      ...car,
      licensePlate: licensePlate,
      mileage: mileage,
      lastInspection: `${lastInspection}T00:00:00`,
    };
    dispatch(updateCar(exLicensePlate, newCar));
  };

  return (
    <ValidatorForm onSubmit={handleSubmit}>
      <UpdateCarsForm
        licensePlate={licensePlate}
        mileage={mileage}
        lastInspection={lastInspection}
        licensePlateChange={setLicensePlate}
        mileageChange={setMileage}
        lastInspectionChange={setLastInspection}
      />
    </ValidatorForm>
  );
};

const UpdateCars = ({ car }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [menu, toggle] = useState(true);

  const handleAddFault = (faultDTO) => {
    dispatch(addFault(faultDTO));
  };

  return (
    <Paper className={classes.root}>
      <Grid container justify="space-evenly" alignItems="center">
        <Button
          onClick={() => toggle(true)}
          color={menu ? "primary" : ""}
          variant="contained"
        >
          Edit car
        </Button>
        <Button
          onClick={() => toggle(false)}
          color={menu ? "" : "primary"}
          variant="contained"
        >
          Add fault
        </Button>
      </Grid>
      <Grid className={classes.form}>
        {menu ? (
          <EditCars car={car} />
        ) : (
          <AddFault
            carLicensePlate={car.licensePlate}
            onSubmit={handleAddFault}
          />
        )}
      </Grid>
    </Paper>
  );
};

export default UpdateCars;
