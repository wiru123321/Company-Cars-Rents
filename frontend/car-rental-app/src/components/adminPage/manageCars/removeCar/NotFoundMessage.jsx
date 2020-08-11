import React from "react";
import { Paper, Typography, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
const useStyles = makeStyles({
  box: {
    height: "40vh",
  },
});
const NotFoundMessage = () => {
  const classes = useStyles();
  return (
    <Paper>
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <Typography>Cars not found.</Typography>
      </Grid>
    </Paper>
  );
};

export default NotFoundMessage;
