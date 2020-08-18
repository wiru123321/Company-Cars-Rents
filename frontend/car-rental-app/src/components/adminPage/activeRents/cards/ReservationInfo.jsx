import React from "react";
import { Grid, Paper, makeStyles } from "@material-ui/core";
import UserInfo from "../resrvationUi/UserInfo";
import ReservationDate from "../resrvationUi/ReservationDate";
import CarInfoCard from "../resrvationUi/CarInfoCard";
import ParkingInfo from "./ParkingInfo";

const useStyles = makeStyles({
  item: {
    margin: "2%",
    padding: "4px",
    backgroundColor: "#DCDCDC",
  },
});

const ReservationInfo = ({ rent }) => {
  const classes = useStyles();
  return (
    <Grid container direction="column">
      <Paper className={classes.item}>
        <UserInfo rent={rent} />
      </Paper>
      <Paper className={classes.item}>
        <ReservationDate rent={rent} />
      </Paper>
      <Paper className={classes.item}>
        <CarInfoCard car={rent.carDTO} />
      </Paper>
      <Paper className={classes.item}>
        <ParkingInfo title="Start parking" parking={rent.parkingFrom} />
      </Paper>
      <Paper className={classes.item}>
        <ParkingInfo title="End parking" parking={rent.parkingTo} />
      </Paper>
    </Grid>
  );
};

export default ReservationInfo;
