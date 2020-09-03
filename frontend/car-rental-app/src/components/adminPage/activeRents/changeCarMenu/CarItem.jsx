import React from "react";
import { Grid, Button, Paper, makeStyles } from "@material-ui/core";
import { useAlert } from "react-alert";
import CarInfoCard from "../resrvationUi/CarInfoCard";

const useStyles = makeStyles({
  paper: {
    margin: "2% 0",
    minWidth: "35vw",
    backgroundColor: "#DCDCDC",
    padding: "8px",
  },
  button: {
    width: "100%",
    padding: "4px",
  },
});

const CarItem = ({ car, changeCar }) => {
  const classes = useStyles();
  const alert = useAlert();

  const handleCarChange = () => {
    changeCar(car.licensePlate, alert);
  };

  return (
    <Grid>
      <Paper className={classes.paper}>
        <CarInfoCard car={car} />
        <Button
          className={classes.button}
          onClick={handleCarChange}
          variant="contained"
          color="primary"
        >
          Change
        </Button>
      </Paper>
    </Grid>
  );
};

export default CarItem;
