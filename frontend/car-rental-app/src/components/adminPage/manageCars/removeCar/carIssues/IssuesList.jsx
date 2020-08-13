import React from "react";
import { Grid, Paper, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import Issue from "./Issue";
import NotFoundMessage from "../../../messages/NotFoundMessage";
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

const Issues = ({ faults, fetchFaults, handleFaultDelete }) => {
  const classes = useStyles();

  return (
    <Grid container>
      <Paper className={classes.box}>
        <Typography className={classes.title}>Issues</Typography>
        {faults.length > 0 ? (
          faults.map((fault, index) => (
            <Issue
              key={index + fault.description}
              fault={fault}
              handleDeleteFault={handleFaultDelete}
              fetchFaults={fetchFaults}
            />
          ))
        ) : (
          <NotFoundMessage>There are no issues.</NotFoundMessage>
        )}
      </Paper>
    </Grid>
  );
};

export default Issues;
