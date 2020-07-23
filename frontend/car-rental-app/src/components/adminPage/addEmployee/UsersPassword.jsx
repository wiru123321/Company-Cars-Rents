import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  passwordChange,
  rePasswordChange,
  selectAll,
} from "../../../features/car-reservation/addEmployeeSlice";
import { TextField, Grid } from "@material-ui/core";
import useStyles from "./useStyles";

const UsersPassword = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const { password, rePassword } = useSelector(selectAll);

  const handlePasswordChange = (event) =>
    dispatch(passwordChange(event.target.value));
  const handleRePasswordChange = (event) =>
    dispatch(rePasswordChange(event.target.value));

  return (
    <Grid
      className={classes.root}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <TextField
        onChange={handlePasswordChange}
        value={password}
        label="password"
        variant="filled"
        type="password"
        required
      />
      <TextField
        onChange={handleRePasswordChange}
        value={rePassword}
        label="repeat password"
        variant="filled"
        type="password"
        required
      />
    </Grid>
  );
};

export default UsersPassword;
