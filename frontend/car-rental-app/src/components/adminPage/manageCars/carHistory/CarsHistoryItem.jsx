import React, { useState } from "react";
import {
  Grid,
  Paper,
  Divider,
  makeStyles,
  Button,
  Typography,
} from "@material-ui/core";
import ParkingInfo from "../../activeRents/cards/ParkingInfo";
import UserInfo from "../../activeRents/resrvationUi/UserInfo";
import ReservationDate from "../../activeRents/resrvationUi/ReservationDate";

const useStyles = makeStyles({
  title: {
    margin: "2%",
    fontSize: "2rem",
  },
  paper: {
    margin: "2%",
    padding: "8px",
    minWidth: "30vw",
  },
  card: {
    margin: "2%",
    padding: "8px",
    backgroundColor: "#DCDCDC",
  },
  item: {
    margin: "2%",
    padding: "4px",
    backgroundColor: "#DCDCDC",
  },
  title: {
    fontSize: "1.5rem",
  },
  textArea: {
    wordWrap: "break-word",
  },
});

const CarsHistoryItem = ({ rent }) => {
  const [hide, setHide] = useState(true);

  console.log(rent);

  const toggleHide = () => {
    setHide(!hide);
  };

  const classes = useStyles();
  return (
    <Paper elevation={6} className={classes.paper}>
      <Grid container direction="column">
        {!hide && (
          <Paper className={classes.card}>
            <UserInfo user={rent.userDTO} />
          </Paper>
        )}
        <Divider />
        <Paper className={classes.card}>
          <ReservationDate dateFrom={rent.dateFrom} dateTo={rent.dateTo} />
        </Paper>
        <Divider />
        {!hide && (
          <>
            <Paper className={classes.card}>
              <ParkingInfo
                title="Start parking"
                parking={rent.parkingHistoryDTOFrom}
              />
            </Paper>
            <Paper className={classes.card}>
              <ParkingInfo
                title="End parking"
                parking={rent.parkingHistoryDTOTo}
              />
            </Paper>
            {rent.adminResponseForTheRequest && (
              <Paper className={classes.item}>
                <Typography className={classes.title}>
                  Admins response
                </Typography>
                <Typography className={classes.textArea}>
                  {rent.adminResponseForTheRequest}
                </Typography>
              </Paper>
            )}
          </>
        )}
        <Button onClick={toggleHide}>{hide ? "Show more" : "Hide"}</Button>
        <Divider />
      </Grid>
    </Paper>
  );
};

export default CarsHistoryItem;
