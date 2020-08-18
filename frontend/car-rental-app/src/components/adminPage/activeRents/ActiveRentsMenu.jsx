import React, { useState } from "react";
import {
  Grid,
  Paper,
  Button,
  makeStyles,
  Divider,
  Typography,
} from "@material-ui/core";
import KeyboardBackspaceIcon from "@material-ui/icons/KeyboardBackspace";
import InfoIcon from "@material-ui/icons/Info";
import EditIcon from "@material-ui/icons/Edit";
import ChangeCar from "./ChangeCar";
import UserInfo from "./card/UserInfo";
import ReservationDate from "./card/ReservationDate";
import CarInfoCard from "./card/CarInfoCard";

const useStyles = makeStyles({
  paper: {
    margin: "2%",
    padding: "8px",
    minWidth: "40vw",
    minHeight: "40vh",
  },
  item: {
    margin: "1%",
    padding: "4px",
    backgroundColor: "#DCDCDC",
  },
  title: {
    fontSize: "1.5rem",
    textAlign: "center",
  },
});

const ActiveRentsMenu = ({ exitMenu, currentRent }) => {
  const classes = useStyles();
  const [info, toggleInfo] = useState(true);
  return (
    <Grid container justify="center">
      <Paper className={classes.paper}>
        <Grid container justify="space-evenly" alignItems="center">
          <Grid>
            <Button
              onClick={exitMenu}
              variant="contained"
              startIcon={<KeyboardBackspaceIcon />}
            >
              Back
            </Button>
          </Grid>
          <Grid>
            <Button
              onClick={() => toggleInfo(true)}
              variant="contained"
              startIcon={<InfoIcon />}
            >
              Info
            </Button>
          </Grid>
          <Grid>
            <Button
              onClick={() => toggleInfo(false)}
              variant="contained"
              startIcon={<EditIcon />}
            >
              Change car
            </Button>
          </Grid>
        </Grid>
        <Divider />
        <Grid container direction="column" justify="center" alignItems="center">
          {info ? (
            <ReservationInfo rent={currentRent} />
          ) : (
            <ChangeCar
              rent={currentRent}
              car={currentRent.carDTO}
              dateFrom={currentRent.dateFrom}
              dateTo={currentRent.dateTo}
            />
          )}
        </Grid>
      </Paper>
    </Grid>
  );
};

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

const ParkingInfo = ({ title, parking }) => {
  const classes = useStyles();
  return (
    <Grid container direction="column">
      <Typography className={classes.title}>{title}</Typography>
      <Divider />
      <Grid container justify="space-evenly">
        <Grid>
          <Typography>Town: {parking.town}</Typography>
          <Typography>Postal code: {parking.postalCode}</Typography>
        </Grid>
        <Grid>
          <Typography>Street name: {parking.streetName}</Typography>
          <Typography>Number: {parking.number}</Typography>
        </Grid>
      </Grid>
      <Divider />
      <Grid container justify="center">
        <Typography>{parking.comment}</Typography>
      </Grid>
    </Grid>
  );
};

export default ActiveRentsMenu;
