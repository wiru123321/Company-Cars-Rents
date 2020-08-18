import React from "react";
import { Typography, Button, Grid, Divider } from "@material-ui/core";
import { useDispatch } from "react-redux";
import { chooseRequest } from "../../../features/rents/rentsSlice";
import { makeStyles } from "@material-ui/core";
const useStyles = makeStyles({
  box: {
    maxHeight: "20vh",
    width: "30vw",
    padding: "8px",
  },
});

const ContentItem = ({ rent, index }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { userRentInfo, dateFrom, dateTo } = rent;

  const linkPath = "#/adminPage/rentRequest";

  function setActiveRequest() {
    dispatch(chooseRequest(index));
  }

  return (
    <Grid>
      <Grid
        container
        className={classes.box}
        direction="column"
        justify="center"
        alignItems="flex-start"
      >
        <Typography>
          {userRentInfo.name} {userRentInfo.surname}
        </Typography>
        <Typography>Reservation start: {dateFrom.slice(0, 10)}</Typography>
        <Typography>Reservation end: {dateTo.slice(0, 10)}</Typography>
        <Button variant="contained" onClick={setActiveRequest} href={linkPath}>
          consider
        </Button>
      </Grid>
      <Divider />
    </Grid>
  );
};

export default ContentItem;
