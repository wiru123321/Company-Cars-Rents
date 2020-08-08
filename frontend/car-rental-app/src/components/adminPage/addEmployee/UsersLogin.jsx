import React from "react";
import { Grid } from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
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
      <TextValidator
        className={classes.textArea}
        label="Email"
        onChange={handleEmailChange}
        value={email}
        name="email"
        type="email"
        validators={["required", "isEmail"]}
        errorMessages={["this field is required", "email is not valid"]}
      />
      <TextValidator
        className={classes.textArea}
        onChange={handleLoginChange}
        value={login}
        type="login"
        name="login"
        label="Login"
        validators={["required"]}
        errorMessages={["this field is required"]}
      />
      <TextValidator
        className={classes.textArea}
        label="Phone number"
        onChange={handlePhoneNumberChange}
        name="phoneNumber"
        type="phone"
        validators={["required", "matchRegexp:^[5-9][0-9]{8}$"]}
        errorMessages={["this field is required", "phone number is not valid"]}
        value={phoneNumber}
      />
    </Grid>
  );
};

export default UsersLogin;
/***<TextValidator
        className={classes.textArea}
        onChange={handleEmailChange}
        value={email}
        label="email"
        placeholder="email"
        variant="filled"
        type="email"
        required
      />
      <TextValidator
        className={classes.textArea}
        onChange={handleLoginChange}
        placeholder="login"
        value={login}
        label="login"
        variant="filled"
        required
      />
      <TextValidator
        className={classes.textArea}
        onChange={handlePhoneNumberChange}
        placeholder="phoneNumber"
        value={phoneNumber}
        label="phone number"
        variant="filled"
        required
      /> */
