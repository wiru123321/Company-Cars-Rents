import React from "react";
import { Grid } from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import useStyles from "./useStyles";

const UsersPersonalData = ({
  firstname,
  lastname,
  role,
  handleFirstnameChange,
  handleLastnameChange,
}) => {
  const classes = useStyles();

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <TextValidator
        className={classes.textArea}
        onChange={handleFirstnameChange}
        value={firstname}
        type="text"
        label="Firstname"
        validators={["required", "matchRegexp:^[A-Z][a-z]+$"]}
        errorMessages={["this field is required", "firstname is not valid"]}
      />
      <TextValidator
        className={classes.textArea}
        onChange={handleLastnameChange}
        value={lastname}
        type="text"
        label="Lastname"
        validators={["required", "matchRegexp:^[A-Z][a-z]+$"]}
        errorMessages={["this field is required", "firstname is not valid"]}
      />
    </Grid>
  );
};

export default UsersPersonalData;
