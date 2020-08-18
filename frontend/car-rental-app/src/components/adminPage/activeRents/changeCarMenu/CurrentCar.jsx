import React from "react";
import { Grid, Paper, Typography, makeStyles } from "@material-ui/core";
import CarInfoCard from "../resrvationUi/CarInfoCard";

const useStyles = makeStyles({
  paper: {
    margin: "2% 0",
    minWidth: "35vw",
    backgroundColor: "#DCDCDC",
    padding: "8px",
  },
  title: {
    fontSize: "1.4rem",
    textAlign: "center",
  },
});

const CurrentCar = ({ car }) => {
  const classes = useStyles();
  return (
    <>
      <Paper className={classes.paper}>
        <Grid container justify="center">
          <CarInfoCard car={car} />
        </Grid>
      </Paper>
    </>
  );
};

export default CurrentCar;
