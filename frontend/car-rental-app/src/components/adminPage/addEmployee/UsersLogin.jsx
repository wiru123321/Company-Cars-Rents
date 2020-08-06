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

const UsersLogin = ({
  email,
  login,
  phoneNumber,
  handleEmailChange,
  handleLoginChange,
  handlePhoneNumberChange,
}) => {
  const classes = useStyles();

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <TextField
        className={classes.textArea}
        onChange={handleEmailChange}
        value={email}
        label="email"
        placeholder="email"
        variant="filled"
        type="email"
        required
      />
      <TextField
        className={classes.textArea}
        onChange={handleLoginChange}
        placeholder="login"
        value={login}
        label="login"
        variant="filled"
        required
      />
      <TextField
        className={classes.textArea}
        onChange={handlePhoneNumberChange}
        placeholder="phoneNumber"
        value={phoneNumber}
        label="phone number"
        variant="filled"
        required
      />
    </Grid>
  );
};

export default UsersLogin;
