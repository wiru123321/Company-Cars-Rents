import React from "react";
import { Grid, Paper, InputBase, Button, makeStyles } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles({
  box: {
    marginTop: "1%",
    width: "60vw",
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
  filterActive,
  filterLicensePlate,
  filterMark,
  handleLicensePlateFilter,
  handleMarkFilter,
  toggleActiveFilter,
  handleReset,
}) => {
  const classes = useStyles();
  return (
    <Paper className={classes.box}>
      <Grid container direction="column" alignItems="center">
        <Grid container justify="space-between" alignItems="center">
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase
                onChange={handleLicensePlateFilter}
                value={filterLicensePlate}
                placeholder="Search license plate"
              />
            </Paper>
          </Grid>
          <Grid item xs={3}>
            <Paper>
              <SearchIcon />
              <InputBase
                onChange={handleMarkFilter}
                value={filterMark}
                placeholder="Search mark"
              />
            </Paper>
          </Grid>
          <Grid item xs={3}>
            <Button
              onClick={toggleActiveFilter}
              color={filterActive ? "secondary" : "primary"}
            >
              Search for {filterActive ? "Suspended" : "Active"} cars
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
