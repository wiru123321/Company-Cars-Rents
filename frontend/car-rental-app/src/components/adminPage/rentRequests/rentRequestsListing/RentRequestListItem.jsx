import React from "react";
import { useDispatch } from "react-redux";
import { UserData, ReservationDate } from "./ItemComponents";
import { chooseRequest } from "../../../../features/rents/rentsSlice";
import {
  Grid,
  Button,
  Paper,
  Divider,
  makeStyles,
  Typography,
} from "@material-ui/core";

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    marginTop: "1%",
  },
  box: {
    minHeight: "30vh",
  },
  checkButton: {
    padding: "4px",
    marginTop: "1%",
    width: "100%",
  },
  title: {
    fontSize: "1.6rem",
  },
  reason: {
    padding: "8px",
    wordWrap: "break-word",
  },
});

const RentRequestListItem = ({ rent, index }) => {
  const linkPath = "#/adminPage/rentRequest";
  const classes = useStyles();
  const dispatch = useDispatch();
  const { userRentInfo, dateFrom, dateTo } = rent;
  function setActiveRequest() {
    dispatch(chooseRequest(index));
  }

  return (
    <Paper elevation={6} className={classes.paper}>
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="center"
      >
        <Grid item>
          <UserData
            firstname={userRentInfo.name}
            lastname={userRentInfo.surname}
          />
          <Divider />
        </Grid>
        <Grid item>
          <ReservationDate
            beginDate={dateFrom.slice(0, 10)}
            beginHour={dateFrom.slice(11, 19)}
            endDate={dateTo.slice(0, 10)}
            endHour={dateTo.slice(11, 19)}
          />
        </Grid>
        <Grid item>
          <Paper elevation={6} className={classes.reason}>
            <Typography className={classes.title}>Rent reason:</Typography>
            {rent.reasonForTheLoan ? (
              <Typography>{rent.reasonForTheLoan}</Typography>
            ) : (
              <Typography>None</Typography>
            )}
          </Paper>
        </Grid>
        <Grid item>
          <Button
            className={classes.checkButton}
            color="primary"
            onClick={setActiveRequest}
            href={linkPath}
          >
            check
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default RentRequestListItem;
