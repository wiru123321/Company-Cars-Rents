import React from "react";
import { Paper, Typography, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
const useStyles = makeStyles({
  paper: {
    marginTop: "1%",
    padding: "4px",
    backgroundColor: "red",
    color: "white",
  },
});
const NotFoundMessage = ({ children, raw }) => {
  const classes = useStyles();
  return (
    <Paper className={classes.paper}>
      <Grid container justify="center" alignItems="center">
        <Typography>{children}</Typography>
      </Grid>
    </Paper>
  );
};

export default NotFoundMessage;
