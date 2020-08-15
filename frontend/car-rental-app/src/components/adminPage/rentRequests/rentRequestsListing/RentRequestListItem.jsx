import React from "react";
import { useDispatch } from "react-redux";
import { UserData, ReservationDate } from "./ItemComponents";
import { chooseRequest } from "../../../../features/rents/rentsSlice";
import { Grid, Button, Paper } from "@material-ui/core";
import { rentRequestStyles } from "../rentRequestInfo/rentRequest.styles";

const RentRequestListItem = ({ rent, index }) => {
  const linkPath = "#/adminPage/rentRequest";
  const classes = rentRequestStyles();
  const dispatch = useDispatch();
  const { userRentInfo, dateFrom, dateTo } = rent;
  function setActiveRequest() {
    dispatch(chooseRequest(index));
  }

  return (
    <Paper className={classes.paper}>
      <Grid container>
        <Grid item xs={12}>
          <UserData
            firstname={userRentInfo.name}
            lastname={userRentInfo.surname}
          />
        </Grid>
        <Grid container item xs={12}>
          <ReservationDate
            beginDate={dateFrom.slice(0, 10)}
            beginHour={dateFrom.slice(10, 19)}
            endDate={dateTo.slice(0, 10)}
            endHour={dateTo.slice(10, 19)}
          />
        </Grid>
        <Button color="primary" onClick={setActiveRequest} href={linkPath}>
          check
        </Button>
      </Grid>
    </Paper>
  );
};

export default RentRequestListItem;
