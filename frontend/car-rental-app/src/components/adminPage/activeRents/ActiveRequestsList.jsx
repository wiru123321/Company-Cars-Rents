import React from "react";
import {
  Grid,
  Paper,
  makeStyles,
  Typography,
  Button,
  Divider,
} from "@material-ui/core";
import CarInfoCard from "./card/CarInfoCard";
import UserInfo from "./card/UserInfo";
import ReservationDate from "./card/ReservationDate";

const useStyles = makeStyles({
  title: {
    margin: "2%",
    fontSize: "2rem",
  },
  paper: {
    margin: "2%",
    padding: "8px",
    minHeight: "30vh",
    minWidth: "50vw",
  },
  card: {
    margin: "2%",
    padding: "8px",
    backgroundColor: "#DCDCDC",
  },
});

const ActiveRequestList = ({ rents, menuMode }) => {
  const classes = useStyles();
  return (
    <Grid container>
      <Grid container direction="column" justify="center" alignItems="center">
        <Typography className={classes.title}>Active rents</Typography>
        {rents.length > 0 ? (
          rents.map((rent, index) => {
            const handleMenuModeChange = () => {
              menuMode(rent);
            };
            return (
              <RentCard
                key={rent.id}
                rent={rent}
                handleMenuModeChange={handleMenuModeChange}
              />
            );
          })
        ) : (
          <Grid>Active rents not found.</Grid>
        )}
      </Grid>
    </Grid>
  );
};

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

export default ActiveRequestList;
