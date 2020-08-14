import React from "react";
import { Paper, Typography, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
const useStyles = makeStyles({
  paper: {
    marginTop: "1%",
  },
  box: {
    height: "40vh",
    width: "40vw",
  },
});
const NotFoundMessage = ({ children, raw }) => {
  const classes = useStyles();
  return (
    <Paper className={classes.paper}>
      <Grid
        className={!raw && classes.box}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <Typography>{children}</Typography>
      </Grid>
    </Paper>
  );
};

export default NotFoundMessage;
