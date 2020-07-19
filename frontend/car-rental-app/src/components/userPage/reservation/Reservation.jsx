import React from "react";
import CarSuggestion from "./CarSuggestion";
import { makeStyles } from "@material-ui/core/styles";
import { Container, Grid, Button, Box } from "@material-ui/core";
import {
  RequestDate,
  ReservationDate,
  UserPersonalData,
} from "./ReservationForm";

const useStyles = makeStyles((theme) => ({
  root: {
    marginTop: "2%",
    height: "800px",
    padding: "8px",
  },
  leftColumn: {
    // padding: "14px",
    width: "40%",
  },
  rightColumn: {
    width: "60%",
  },
}));

const Reservation = () => {
  const classes = useStyles();

  return (
    <Container className={classes.root} maxWidth="lg">
      <Grid container direction="row" justify="left" alignItems="flex-start">
        <Box className={classes.leftColumn}>
          <Grid direction="column" justify="flex-start" alignItems="center">
            <UserPersonalData />
            <ReservationDate />
            <Button
              style={{ marginTop: "2%", width: "100%" }}
              variant="contained"
              color="primary"
            >
              Submit
            </Button>
          </Grid>
        </Box>
        <Container className={classes.rightColumn}>
          <CarSuggestion />
        </Container>
      </Grid>
    </Container>
  );
};

export default Reservation;
