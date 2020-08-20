import React from "react";
import { Grid, Paper, Input, Button, Divider } from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  box: {
    marginTop: "1%",
  },
  searchBar: {
    padding: "16px",
    minWidth: "50vw",
    backgroundColor: "#A9A9A9",
  },
  searchField: {
    padding: "8px",
  },
  inputField: {
    minWidth: "30ch",
  },
});

const EmployeesSearchBar = ({
  loginFilters,
  nameFilters,
  handleLoginFilterChange,
  handleNameFilterChange,
  resetChanges,
}) => {
  const classes = useStyles();
  return (
    <Grid className={classes.box}>
      <Paper elevation={4} className={classes.searchBar}>
        <Grid container justify="space-evenly" alignItems="center">
          <Grid item xs={5}>
            <Grid container>
              <Paper className={classes.searchField}>
                <SearchIcon />
                <Input
                  value={loginFilters}
                  onChange={handleLoginFilterChange}
                  className={classes.inputField}
                  placeholder="login"
                />
              </Paper>
            </Grid>
          </Grid>
          <Grid item xs={5}>
            <Grid container>
              <Paper className={classes.searchField}>
                <SearchIcon />
                <Input
                  value={nameFilters}
                  onChange={handleNameFilterChange}
                  className={classes.inputField}
                  placeholder="name surname"
                />
              </Paper>
            </Grid>
          </Grid>
          <Divider orientation="vertical" flexItem />
          <Grid item xs={1}>
            <Button onClick={resetChanges}>
              <Paper className={classes.searchField}>Reset</Paper>
            </Button>
          </Grid>
        </Grid>
      </Paper>
    </Grid>
  );
};

export default EmployeesSearchBar;
