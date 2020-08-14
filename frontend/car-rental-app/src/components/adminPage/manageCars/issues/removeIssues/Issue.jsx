import React from "react";
import { Grid, Paper, Typography, Button, makeStyles } from "@material-ui/core";
import DoneIcon from "@material-ui/icons/Done";

const useStyles = makeStyles({
  paper: {
    marginTop: "1%",
    padding: "8px",
  },
});

const Issue = ({ issue, handleFaultDelete }) => {
  const classes = useStyles();

  const onCheck = () => {
    handleFaultDelete(issue);
  };

  return (
    <Paper className={classes.paper}>
      <Grid container alignItems="center">
        <Grid item xs={10}>
          <Typography color="secondary">{issue.description}</Typography>
        </Grid>
        <Grid item xs={2}>
          <Button
            onClick={onCheck}
            color="secondary"
            startIcon={<DoneIcon />}
          ></Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default Issue;
