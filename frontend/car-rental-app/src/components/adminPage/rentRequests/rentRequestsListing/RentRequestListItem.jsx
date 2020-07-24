import React from "react";
import { useDispatch } from "react-redux";
import { chooseRequest } from "../../../../features/rent-requests/rentRequestsSlice";
import { Grid, Box, Button, Paper } from "@material-ui/core";
import { rentRequestStyles } from "../rentRequestInfo/rentRequest.styles";

const UserData = ({ firstname, lastname }) => {
  return (
    <React.Fragment>
      <Grid item xs={4}>
        <h1>
          {firstname} {lastname}
        </h1>
      </Grid>
    </React.Fragment>
  );
};

const ReservationDate = ({ beginDate, beginHour, endDate, endHour }) => {
  return (
    <React.Fragment>
      <Grid item xs={4}>
        Reservation start: {beginDate} - {beginHour}
      </Grid>
      <Grid item xs={4}>
        Reservation end: {endDate} - {endHour}
      </Grid>
    </React.Fragment>
  );
};

const RentRequestListItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
  index,
}) => {
  const classes = rentRequestStyles();
  const dispatch = useDispatch();
  return (
    <Paper className={classes.paper}>
      <Grid container>
        <Grid item xs={12}>
          <UserData firstname={firstname} lastname={lastname} />
        </Grid>
        <Grid container item xs={12}>
          <ReservationDate
            beginDate={beginDate}
            beginHour={beginHour}
            endDate={endDate}
            endHour={endHour}
          />
        </Grid>
        <Button
          color="primary"
          onClick={(event) => {
            dispatch(chooseRequest(index));
          }}
          href="#/adminPage/rentRequest"
        >
          check
        </Button>
      </Grid>
    </Paper>
  );
};

export default RentRequestListItem;
