import React, { useState } from "react";
import { Grid, Paper, InputBase, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles({
  box: {
    marginTop: "1%",
    padding: "8px",
  },
  searchActive: {
    backgroundColor: "green",
    color: "white",
  },
  searchInactive: {
    backgroundColor: "red",
    color: "white",
  },
});

const SearchBar = () => {
  const classes = useStyles();
  const [searchForActive, toggleSearchForActive] = useState(true);

  const toggleActiveFilter = () => {
    toggleSearchForActive(!searchForActive);
  };

  return (
    <Paper className={classes.box}>
      <Grid container direction="column" alignItems="center">
        <Grid container justify="space-between" alignItems="center">
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase placeholder="Search license plate" />
            </Paper>
          </Grid>
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase placeholder="Search mark" />
            </Paper>
          </Grid>
          <Grid item xs={3}>
            <Button
              className={
                searchForActive ? classes.searchInactive : classes.searchActive
              }
              onClick={toggleActiveFilter}
            >
              Search for {searchForActive ? "inactive" : "active"} cars
            </Button>
          </Grid>
          <Grid item xs={2}>
            <Button>Reset</Button>
          </Grid>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default SearchBar;
