import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Paper, Button, makeStyles } from "@material-ui/core";
import {
  selectAll,
  setViewId,
  setCurrentCar,
  enterManageCarMode,
  setCarActivity,
  deleteCar,
} from "../../../features/cars-manager/carsManagerSlice";
import Edit from "./edit/Edit";
import IssuesList from "./issues/removeIssues/IssuesList";
import AddIssues from "./issues/addIssues/AddIssues";
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
  const { currentCar, viewId, filterActive } = useSelector(selectAll);

  const toggleView = (id) => {
    dispatch(setViewId(id));
  };

  const handleReturn = () => {
    dispatch(setCurrentCar(""));
    dispatch(enterManageCarMode(false));
    toggleView(0);
  };

  const toggleSuspend = () => {
    dispatch(
      setCarActivity(
        currentCar.licensePlate,
        !currentCar.isActive,
        filterActive
      )
    );
  };

  const handleCarDelete = () => {
    dispatch(deleteCar(currentCar.licensePlate, filterActive));
  };

  const getView = () => {
    if (viewId === 0) {
      return <Edit />;
    } else if (viewId === 1) {
      return <AddIssues />;
    } else if (viewId === 2) {
      return <IssuesList />;
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
        <Button onClick={handleReturn} startIcon={<KeyboardBackspaceIcon />}>
          Go Back
        </Button>
        <Button
          onClick={toggleSuspend}
          color={currentCar.isActive ? "secondary" : "primary"}
          startIcon={<NotInterestedIcon />}
        >
          Suspend
        </Button>
        <Button onClick={() => toggleView(0)} startIcon={<EditIcon />}>
          Edit
        </Button>
        <Button onClick={() => toggleView(1)} startIcon={<ReportProblemIcon />}>
          Report issue
        </Button>
        <Button onClick={() => toggleView(2)} startIcon={<ErrorIcon />}>
          Issues
        </Button>
        <Button
          onClick={handleCarDelete}
          color="secondary"
          startIcon={<ClearIcon />}
        >
          Remove
        </Button>
      </Grid>
      {getView()}
    </Paper>
  );
};
export default CarManager;
