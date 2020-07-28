import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  emailChange,
  phoneNumberChange,
  loginChange,
  selectAll,
} from "../../../features/add-employees/addEmployeeSlice";
import { TextField, Grid } from "@material-ui/core";
import useStyles from "./useStyles";

const UsersLogin = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { email, login, phoneNumber } = useSelector(selectAll);

  const handleEmailChange = (event) =>
    dispatch(emailChange(event.target.value));

  const handleLoginChange = (event) =>
    dispatch(loginChange(event.target.value));

  const handlePhoneNumberChange = (event) =>
    dispatch(phoneNumberChange(event.target.value));

  return (
    <Grid
      className={classes.root}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <TextField
        onChange={handleEmailChange}
        value={email}
        label="email"
        variant="filled"
        type="email"
        required
      />
      <TextField
        onChange={handleLoginChange}
        value={login}
        label="login"
        variant="filled"
        required
      />
      <TextField
        onChange={handlePhoneNumberChange}
        value={phoneNumber}
        label="phone number"
        variant="filled"
        required
      />
    </Grid>
  );
};

export default UsersLogin;
