import React from "react";
import { Grid, Typography, Paper, Input, Button } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  searchBar: {
    padding: "8px",
  },
  searchField: {
    padding: "4px",
    width: "50ch",
  },
});
const EmployeesSearchBar = () => {
  const classes = useStyles();
  return (
    <Grid>
      <Paper className={classes.searchBar}>
        <Grid container justify="space-between" alignItems="center">
          <Paper>
            <SearchIcon />
            <Input className={classes.searchField} placeholder="login" />
          </Paper>
          <Paper className={classes.searchField}>
            <SearchIcon />
            <Input placeholder="name surname" />
          </Paper>
          <Button>Reset</Button>
        </Grid>
      </Paper>
    </Grid>
  );
};

export default EmployeesSearchBar;
