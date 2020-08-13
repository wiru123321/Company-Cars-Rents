import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Paper, Button, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import AddFault from "./AddFault";
import { addFault } from "../../../../features/car-management/carManagerSlice";
import EditCars from "./EditCars";

const useStyles = makeStyles({
  root: {
    padding: "8px",
  },
  form: {
    marginTop: "4%",
  },
});

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
