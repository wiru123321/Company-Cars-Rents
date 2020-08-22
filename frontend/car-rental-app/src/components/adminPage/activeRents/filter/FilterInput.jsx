import React from "react";
import { makeStyles, Paper, Input } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles({
  input: {
    padding: "4px",
  },
});

const FilterInput = ({ onChange, name, placeholder, value }) => {
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

export default FilterInput;
