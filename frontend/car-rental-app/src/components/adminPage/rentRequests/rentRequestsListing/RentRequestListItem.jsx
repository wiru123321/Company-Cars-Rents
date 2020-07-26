import React from "react";
import { useDispatch } from "react-redux";
import { UserData, ReservationDate } from "./ItemComponents";
import { chooseRequest } from "../../../../features/rent-requests/rentRequestsSlice";
import { Grid, Button, Paper } from "@material-ui/core";
import { rentRequestStyles } from "../rentRequestInfo/rentRequest.styles";

const RentRequestListItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
  index,
}) => {
  const linkPath = "#/adminPage/rentRequest";
  const classes = rentRequestStyles();
  const dispatch = useDispatch();

  function setActiveRequest() {
    dispatch(chooseRequest(index));
  }

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
        <Button color="primary" onClick={setActiveRequest} href={linkPath}>
          check
        </Button>
      </Grid>
    </Paper>
  );
};

export default RentRequestListItem;
