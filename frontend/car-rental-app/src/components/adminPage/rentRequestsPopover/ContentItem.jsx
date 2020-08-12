import React from "react";
import { Paper, Typography, Button, Grid, Divider } from "@material-ui/core";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  box: {
    maxHeight: "20vh",
    width: "30vw",
    padding: "8px",
  },
});

const ContentItem = ({ rent }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { userRentInfo, dateFrom, dateTo } = rent;

  const linkPath = "#/adminPage/rentRequest";

  function setActiveRequest() {
    //  dispatch(chooseRequest(index));
  }

  return (
    <Grid>
      <Grid
        container
        className={classes.box}
        direction="row"
        justify="center"
        alignItems="center"
      >
        <Typography>
          {userRentInfo.name} {userRentInfo.surname}
        </Typography>
        <Typography>
          Reservation: {dateFrom} {dateTo}
        </Typography>
        <Button
          variant="contained"
          size="sm"
          // onClick={setActiveRequest}
          href={linkPath}
        >
          consider
        </Button>
      </Grid>
      <Divider />
    </Grid>
  );
};

export default ContentItem;
