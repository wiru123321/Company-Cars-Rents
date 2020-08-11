import React from "react";
import {
  Grid,
  Typography,
  Paper,
  Input,
  Button,
  Divider,
} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  searchBar: {
    padding: "16px",
  },
  searchField: {
    padding: "4px",
  },
  inputField: {
    width: "50ch",
  },
});

const EmployeesSearchBar = ({
  loginFilters,
  nameFilters,
  handleLoginsFilterChange,
  handleNameFilterChange,
}) => {
  const classes = useStyles();
  return (
    <Grid>
      <Paper className={classes.searchBar}>
        <Grid container justify="space-between" alignItems="center">
          <Paper className={classes.searchField}>
            <SearchIcon />
            <Input
              value={loginFilters}
              onChange={handleLoginsFilterChange}
              className={classes.inputField}
              placeholder="login"
            />
          </Paper>
          <Paper className={classes.searchField}>
            <SearchIcon />
            <Input
              value={nameFilters}
              onChange={handleNameFilterChange}
              className={classes.inputField}
              placeholder="name surname"
            />
          </Paper>
          <Divider orientation="vertical" flexItem />
          <Button>Reset</Button>
        </Grid>
      </Paper>
    </Grid>
  );
};

export default EmployeesSearchBar;
