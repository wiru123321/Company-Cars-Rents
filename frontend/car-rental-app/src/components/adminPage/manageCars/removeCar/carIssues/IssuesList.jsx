import React from "react";
import { useDispatch } from "react-redux";
import { ListItem, Grid, Paper, Button, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import { deleteFault } from "../../../../../features/car-management/carManagerSlice";
import Issue from "./Issue";

const useStyles = makeStyles({
  title: {
    textAlign: "center",
    fontSize: "35px",
  },
  box: {
    minHeight: "30vh",
    width: "40vw",
    padding: "8px",
  },
});

const Issues = ({ faults, fetchFaults }) => {
  const dispatch = useDispatch();
  const classes = useStyles();

  const handleDeleteFault = (fault) => {
    dispatch(handleDeleteFault(fault));
    //  fetchFaults();
  };

  return (
    <Grid container>
      <Paper className={classes.box}>
        <Typography className={classes.title}>Issues</Typography>
        {faults.map((fault, index) => (
          <Issue
            key={index + fault.description}
            fault={fault}
            handleDeleteFault={handleDeleteFault}
          />
        ))}
      </Paper>
    </Grid>
  );
};

export default Issues;
