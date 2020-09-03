import React from "react";
import { Grid, Paper, Typography, makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  root: {
    padding: "8px",
    marginTop: "1%",
  },
  paper: {
    padding: "4px",
  },
  title: {
    fontSize: "1.4rem",
    textAlign: "center",
  },
  textArea: {
    padding: "4px",
    width: "40vw",
    wordWrap: "break-word",
  },
});

const FaultMessage = ({ faultMessage }) => {
  const classes = useStyles();
  if (faultMessage) {
    return (
      <Grid className={classes.root}>
        <Typography className={classes.title} color="secondary">
          Reported issues
        </Typography>
        <Grid container direction="column" justify="center" alignItems="center">
          <Paper elevation={3} color="primary" className={classes.paper}>
            <Typography color="secondary" className={classes.textArea}>
              {faultMessage}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    );
  } else {
    return <></>;
  }
};

export default FaultMessage;
