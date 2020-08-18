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
    minWidth: "30vw",
  },
  card: {
    margin: "2%",
    padding: "8px",
    backgroundColor: "#DCDCDC",
  },
});

const RentCard = ({ rent, handleMenuModeChange }) => {
  const classes = useStyles();

  return (
    <Paper className={classes.paper}>
      <Grid container direction="column">
        <Paper className={classes.card}>
          <UserInfo rent={rent} />
        </Paper>
        <Divider />
        <Paper className={classes.card}>
          <ReservationDate rent={rent} />
        </Paper>
        <Divider />
        <Paper className={classes.card}>
          <CarInfoCard car={rent.carDTO} />
        </Paper>
        <Button
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
