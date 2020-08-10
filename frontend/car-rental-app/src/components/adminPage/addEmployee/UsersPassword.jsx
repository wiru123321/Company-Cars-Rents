import React from "react";
import { Grid } from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import useStyles from "./useStyles";

const UsersPassword = ({
  password,
  rePassword,
  handlePasswordChange,
  handleRePasswordChange,
}) => {
  const classes = useStyles();

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <TextValidator
        className={classes.textArea}
        onChange={handlePasswordChange}
        value={password}
        label="Password"
        type="password"
        validators={[
          "required",
          "matchRegexp:^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
        ]}
        errorMessages={["this field is required", "password is not valid"]}
      />
      <TextValidator
        className={classes.textArea}
        onChange={handleRePasswordChange}
        value={rePassword}
        label="Repeat password"
        type="password"
        validators={[
          "required",
          "matchRegexp:^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
        ]}
        errorMessages={["this field is required", "password is not valid"]}
      />
    </Grid>
  );
};

export default UsersPassword;
