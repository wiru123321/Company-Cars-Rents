import React from "react";
import { Grid, Paper, makeStyles, Button, Divider } from "@material-ui/core";
import CarInfoCard from "../resrvationUi/CarInfoCard";
import UserInfo from "../resrvationUi/UserInfo";
import ReservationDate from "../resrvationUi/ReservationDate";

const useStyles = makeStyles({
  title: {
    margin: "2%",
    fontSize: "2rem",
  },
  paper: {
    margin: "1%",
    padding: "8px",
    minHeight: "30vh",
    minWidth: "35vw",
    backgroundColor: "#A9A9A9",
  },
  card: {
    margin: "2%",
    padding: "8px",
    backgroundColor: "#DCDCDC",
  },
  button: {
    backgroundColor: "white",
  },
});

const RentCard = ({ rent, handleMenuModeChange }) => {
  const classes = useStyles();

  return (
    <Paper elevation={6} className={classes.paper}>
      <Grid container direction="column">
        <Paper className={classes.card}>
          <UserInfo user={rent.userRentInfo} />
        </Paper>
        <Divider />
        <Paper className={classes.card}>
          <ReservationDate dateFrom={rent.dateFrom} dateTo={rent.dateTo} />
        </Paper>
        <Divider />
        <Paper className={classes.card}>
          <CarInfoCard car={rent.carDTO} />
        </Paper>
        <Button
          className={classes.button}
          onClick={handleMenuModeChange}
          variant="outlined"
          color="primary"
        >
          Manage
        </Button>
      </Grid>
    </Paper>
  );
};

export default RentCard;
