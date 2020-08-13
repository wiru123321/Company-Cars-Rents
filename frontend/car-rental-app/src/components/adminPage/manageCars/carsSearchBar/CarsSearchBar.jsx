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

const CarsSearchBar = ({
  searchLicesnsePlate,
  searchMark,
  handleLicensePlateChange,
  handleMarkChange,
  getInactiveCars,
  getActiveCars,
  reset,
}) => {
  const classes = useStyles();
  const [searchForActive, toggleSearchForActive] = useState(true);

  const toggleActiveFilter = () => {
    if (searchForActive === true) {
      getInactiveCars();
    } else {
      getActiveCars();
    }
    toggleSearchForActive(!searchForActive);
  };

  const onLicensePlateChange = (event) => {
    handleLicensePlateChange(event.target.value);
  };

  const onMarkChange = (event) => {
    handleMarkChange(event.target.value);
  };

  const handleReset = () => {
    reset();
    toggleSearchForActive(true);
  };
  return (
    <Paper className={classes.box}>
      <Grid container direction="column" alignItems="center">
        <Grid container justify="space-between" alignItems="center">
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase
                placeholder="Search license plate"
                value={searchLicesnsePlate}
                onChange={onLicensePlateChange}
              />
            </Paper>
          </Grid>
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase
                placeholder="Search mark"
                value={searchMark}
                onChange={onMarkChange}
              />
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
            <Button onClick={handleReset}>Reset</Button>
          </Grid>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default CarsSearchBar;
