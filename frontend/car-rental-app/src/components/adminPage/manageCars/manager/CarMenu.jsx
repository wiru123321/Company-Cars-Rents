import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Paper, Button, Badge, makeStyles } from "@material-ui/core";
import {
  selectAll,
  setViewId,
  setCurrentCar,
  enterManageCarMode,
  setCarActivity,
  deleteCar,
} from "../../../../features/cars-manager/carsManagerSlice";
import { fetchFaultsForCar } from "../../../../services/FaultsService";
import Edit from "../edit/Edit";
import IssuesList from "../issues/removeIssues/IssuesList";
import AddIssues from "../issues/addIssues/AddIssues";
import CarsHistory from "../carHistory/CarsHistory";
import KeyboardBackspaceIcon from "@material-ui/icons/KeyboardBackspace";
import NotInterestedIcon from "@material-ui/icons/NotInterested";
import ClearIcon from "@material-ui/icons/Clear";
import ErrorIcon from "@material-ui/icons/Error";
import EditIcon from "@material-ui/icons/Edit";
import ReportProblemIcon from "@material-ui/icons/ReportProblem";
import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import HistoryIcon from "@material-ui/icons/History";
import { useAlert } from "react-alert";

const useStyles = makeStyles({
  paper: {
    marginTop: "3%",
    padding: "8px",
    minHeight: "50vh",
  },
  navigation: {
    minWidth: "60vw",
  },
});

const CarMenu = () => {
  const alert = useAlert();
  const classes = useStyles();
  const dispatch = useDispatch();
  const { currentCar, viewId, filterActive } = useSelector(selectAll);
  const [issues, setIssues] = useState("");

  useEffect(() => {
    fetchFaultsForCar(currentCar.licensePlate, setIssues);
  }, []);

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
        alert,
        filterActive
      )
    );
  };

  const handleCarDelete = () => {
    dispatch(deleteCar(currentCar.licensePlate, alert, filterActive));
  };

  const getView = () => {
    if (viewId === 0) {
      return <Edit />;
    } else if (viewId === 1) {
      return <AddIssues />;
    } else if (viewId === 2) {
      return <IssuesList />;
    } else if (viewId === 3) {
      return <CarsHistory />;
    }
  };

  return (
    <Paper elevation={6} className={classes.paper}>
      <Grid
        className={classes.navigation}
        container
        justify="space-evenly"
        alignItems="center"
      >
        <Button
          onClick={handleReturn}
          color="primary"
          startIcon={<KeyboardBackspaceIcon />}
        >
          Go Back
        </Button>
        <Button
          onClick={() => toggleView(0)}
          color="primary"
          variant={viewId === 0 ? "contained" : ""}
          startIcon={<EditIcon />}
        >
          Edit
        </Button>
        <Button
          onClick={() => toggleView(1)}
          color={viewId === 1 ? "primary" : ""}
          variant={viewId === 1 ? "contained" : ""}
          startIcon={<ReportProblemIcon />}
        >
          Report issue
        </Button>

        <Button
          onClick={() => toggleView(2)}
          color={viewId === 2 ? "primary" : ""}
          variant={viewId === 2 ? "contained" : ""}
          startIcon={<ErrorIcon />}
        >
          <Badge badgeContent={issues.length}>Issues</Badge>
        </Button>

        <Button
          onClick={() => toggleView(3)}
          color={viewId === 3 ? "primary" : ""}
          variant={viewId === 3 ? "contained" : ""}
          startIcon={<HistoryIcon />}
        >
          History
        </Button>

        <Button
          onClick={toggleSuspend}
          color={currentCar.isActive ? "secondary" : "primary"}
          startIcon={
            currentCar.isActive ? <NotInterestedIcon /> : <CheckCircleIcon />
          }
        >
          {currentCar.isActive ? "Suspend" : "Activate"}
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
export default CarMenu;
