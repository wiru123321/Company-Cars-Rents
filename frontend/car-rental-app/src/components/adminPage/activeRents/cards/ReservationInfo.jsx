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

const ReservationInfo = ({
  user,
  dateFrom,
  dateTo,
  car,
  parkingFrom,
  parkingTo,
}) => {
  const classes = useStyles();
  return (
    <Grid container direction="column">
      <Paper className={classes.item}>
        <UserInfo user={user} />
      </Paper>
      <Paper className={classes.item}>
        <ReservationDate dateFrom={dateFrom} dateTo={dateTo} />
      </Paper>
      <Paper className={classes.item}>
        <CarInfoCard car={car} />
      </Paper>
      <Paper className={classes.item}>
        <ParkingInfo title="Start parking" parking={parkingFrom} />
      </Paper>
      <Paper className={classes.item}>
        <ParkingInfo title="End parking" parking={parkingTo} />
      </Paper>
    </Grid>
  );
};

export default ReservationInfo;
