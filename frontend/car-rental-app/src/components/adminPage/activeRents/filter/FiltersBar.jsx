import React from "react";
import { makeStyles, Grid, Paper, Input, Button } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles({
  input: {
    padding: "4px",
  },
});

const FiltersInput = ({ onChange, name, placeholder, value }) => {
  const classes = useStyles();

  return (
    <Paper className={classes.input}>
      <SearchIcon />
      <Input
        onChange={onChange}
        value={value}
        name={name}
        placeholder={placeholder}
      />
    </Paper>
  );
};

const FiltersBar = ({ handleChange, filters, handleReset }) => {
  const classes = useStyles();

  return (
    <Grid container justify="space-evenly">
      <FiltersInput
        onChange={handleChange}
        value={filters.name}
        name="name"
        placeholder="Users name"
      />
      <FiltersInput
        onChange={handleChange}
        value={filters.surname}
        name="surname"
        placeholder="Users surname"
      />
      <FiltersInput
        onChange={handleChange}
        value={filters.mark}
        name="mark"
        placeholder="Car mark"
      />
      <FiltersInput
        onChange={handleChange}
        value={filters.model}
        name="model"
        placeholder="Car model"
      />
      <FiltersInput
        onChange={handleChange}
        value={filters.licensePlate}
        name="licensePlate"
        placeholder="Car license plate"
      />
      <Paper className={classes.input}>
        <Button onClick={handleReset}>Reset</Button>
      </Paper>
    </Grid>
  );
};

export default FiltersBar;
