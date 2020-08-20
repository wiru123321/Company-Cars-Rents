import React from "react";
import { makeStyles, Grid, Paper, Button } from "@material-ui/core";
import FilterInput from "./FilterInput";

const useStyles = makeStyles({
  button: {
    padding: "4px",
  },
});

const FiltersBar = ({ handleChange, filters, handleReset }) => {
  const classes = useStyles();

  return (
    <Grid container justify="space-evenly">
      <FilterInput
        onChange={handleChange}
        value={filters.name}
        name="name"
        placeholder="Users name"
      />
      <FilterInput
        onChange={handleChange}
        value={filters.surname}
        name="surname"
        placeholder="Users surname"
      />
      <FilterInput
        onChange={handleChange}
        value={filters.mark}
        name="mark"
        placeholder="Car mark"
      />
      <FilterInput
        onChange={handleChange}
        value={filters.model}
        name="model"
        placeholder="Car model"
      />
      <FilterInput
        onChange={handleChange}
        value={filters.licensePlate}
        name="licensePlate"
        placeholder="Car license plate"
      />
      <Paper className={classes.button}>
        <Button onClick={handleReset}>Reset</Button>
      </Paper>
    </Grid>
  );
};

export default FiltersBar;
