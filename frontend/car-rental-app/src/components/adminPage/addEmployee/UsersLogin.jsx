import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  loginChange,
  selectAll,
} from "../../../features/add-employees/addEmployeeSlice";
import { TextField, Grid } from "@material-ui/core";
import useStyles from "./useStyles";

const UsersLogin = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { login } = useSelector(selectAll);
  const handleLoginChange = (event) =>
    dispatch(loginChange(event.target.value));

  return (
    <Grid
      className={classes.root}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <TextField
        onChange={handleLoginChange}
        value={login}
        label="login"
        variant="filled"
        required
      />
    </Grid>
  );
};

export default UsersLogin;
