import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  chooseRequest,
  selectChoosenRequestIndex,
  selectRequests,
  selectCurrentRequest,
} from "../../../../features/rent-requests/rentRequestsSlice";
import { Grid, Box, Button } from "@material-ui/core";

function UserData({ firstname, lastname }) {
  return (
    <React.Fragment>
      <Grid item xs={4}>
        <h1>
          {firstname} {lastname}
        </h1>
      </Grid>
    </React.Fragment>
  );
}

function ReservationDate({ beginDate, beginHour, endDate, endHour }) {
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
}

const RentRequestListItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
  index,
}) => {
  const dispatch = useDispatch();
  return (
    <Box border={1} borderRadius="20px" padding="8px">
      <Grid container xs={12}>
        <Grid item xs={12} spacing={1}>
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
          onClick={(event) => {
            dispatch(chooseRequest(index));
          }}
          href="#/adminPage/rentRequest"
        >
          check
        </Button>
      </Grid>
    </Box>
  );
};

export default RentRequestListItem;
