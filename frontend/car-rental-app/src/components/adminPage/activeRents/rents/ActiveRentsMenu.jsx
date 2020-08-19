import React, { useState } from "react";
import { Grid, Paper, Button, makeStyles, Divider } from "@material-ui/core";
import KeyboardBackspaceIcon from "@material-ui/icons/KeyboardBackspace";
import InfoIcon from "@material-ui/icons/Info";
import EditIcon from "@material-ui/icons/Edit";
import ChangeCar from "../changeCarMenu/ChangeCar";
import ReservationInfo from "../cards/ReservationInfo";

const useStyles = makeStyles({
  paper: {
    margin: "2%",
    padding: "8px",
    minWidth: "40vw",
    minHeight: "40vh",
  },
  nav: {
    padding: "8px",
  },
});

const ActiveRentsMenu = ({ exitMenu, currentRent }) => {
  const classes = useStyles();
  const [info, toggleInfo] = useState(true);
  return (
    <Grid container justify="center">
      <Paper className={classes.paper}>
        <Grid
          className={classes.nav}
          container
          justify="space-evenly"
          alignItems="center"
        >
          <Grid>
            <Button
              onClick={exitMenu}
              variant="text"
              color="secondary"
              startIcon={<KeyboardBackspaceIcon />}
            >
              Back
            </Button>
          </Grid>
          <Grid>
            <Button
              onClick={() => toggleInfo(true)}
              variant={info ? "contained" : "text"}
              color="primary"
              startIcon={<InfoIcon />}
            >
              Info
            </Button>
          </Grid>
          <Grid>
            <Button
              onClick={() => toggleInfo(false)}
              variant={!info ? "contained" : "text"}
              color="primary"
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

export default ActiveRentsMenu;
