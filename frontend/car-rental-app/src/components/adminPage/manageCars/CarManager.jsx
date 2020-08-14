import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Paper, Button, makeStyles } from "@material-ui/core";
import {
  selectAll,
  setViewId,
  setCurrentCar,
  enterManageCarMode,
} from "../../../features/cars-manager/carsManagerSlice";
import KeyboardBackspaceIcon from "@material-ui/icons/KeyboardBackspace";
import NotInterestedIcon from "@material-ui/icons/NotInterested";
import ClearIcon from "@material-ui/icons/Clear";
import ErrorIcon from "@material-ui/icons/Error";
import EditIcon from "@material-ui/icons/Edit";
import ReportProblemIcon from "@material-ui/icons/ReportProblem";

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    minHeight: "50vh",
  },
  navigation: {
    minWidth: "60vw",
  },
});

const CarManager = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { currentCar, viewId } = useSelector(selectAll);

  const toggleView = (id) => {
    dispatch(setViewId(id));
  };

  const getView = () => {
    if (viewId === 0) {
      return <div>edit</div>;
    } else if (viewId === 1) {
      return <div>issues</div>;
    } else if (viewId === 2) {
      return <div>add issues</div>;
    }
  };

  return (
    <Paper className={classes.paper}>
      <Grid
        className={classes.navigation}
        container
        justify="space-evenly"
        alignItems="center"
      >
        <Button
          onClick={() => {
            dispatch(setCurrentCar(""));
            dispatch(enterManageCarMode(false));
          }}
          startIcon={<KeyboardBackspaceIcon />}
        >
          Go Back
        </Button>
        <Button startIcon={<NotInterestedIcon />}>Suspend</Button>
        <Button onClick={() => toggleView(0)} startIcon={<EditIcon />}>
          Edit
        </Button>
        <Button onClick={() => toggleView(1)} startIcon={<ReportProblemIcon />}>
          Add issue
        </Button>
        <Button onClick={() => toggleView(2)} startIcon={<ErrorIcon />}>
          Issues
        </Button>
        <Button color="secondary" startIcon={<ClearIcon />}>
          Remove
        </Button>
      </Grid>
      {getView()}
    </Paper>
  );
};
export default CarManager;
