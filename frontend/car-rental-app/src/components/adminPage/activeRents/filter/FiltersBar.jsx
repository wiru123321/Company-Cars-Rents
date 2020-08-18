import React from "react";
import { makeStyles, Grid, Paper, Input, Button } from "@material-ui/core";

const useStyles = makeStyles({
  input: {
    padding: "4px",
  },
});

const FiltersInput = () => {
  const classes = useStyles();

  return (
    <Paper className={classes.input}>
      <Input />
    </Paper>
  );
};

const FiltersBar = () => {
  const classes = useStyles();
  return (
    <Grid container justify="center">
      <FiltersInput />
      <FiltersInput />
      <FiltersInput />
      <FiltersInput />
      <FiltersInput />
      <Button>Reset</Button>
    </Grid>
  );
};

export default FiltersBar;
