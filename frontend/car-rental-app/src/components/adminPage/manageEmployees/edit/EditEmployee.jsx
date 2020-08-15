import React from "react";
import { Grid, makeStyles, Button } from "@material-ui/core";
import { TextValidator, ValidatorForm } from "react-material-ui-form-validator";

const useStyles = makeStyles({
  details: {
    marginTop: "2%",
    minHeight: "20vh",
  },
  column: {
    minHeight: "10vh",
  },
  textField: {
    maxWidth: "30ch",
  },
  submitButton: {
    minWidth: "10vw",
  },
});

const EditEmployee = ({ employeeState, handleChange, handleSubmit }) => {
  const classes = useStyles();

  return (
    <ValidatorForm onSubmit={handleSubmit} className={classes.details}>
      <Grid
        className={classes.details}
        container
        direction="column"
        justify="space-evenly"
        alignItems="center"
      >
        <Grid container justify="space-evenly" alignItems="center">
          <Grid item xs={5}>
            <Grid
              className={classes.column}
              container
              direction="column"
              justify="space-between"
              alignItems="center"
            >
              <TextValidator
                onChange={handleChange}
                value={employeeState.name}
                name="name"
                className={classes.textField}
                placeholder="Name"
                validators={["required", "matchRegexp:^[A-Z][a-z]+$"]}
                errorMessages={[
                  "this field is required",
                  "firstname is not valid",
                ]}
              />
              <TextValidator
                onChange={handleChange}
                value={employeeState.phoneNumber}
                name="phoneNumber"
                className={classes.textField}
                placeholder="Phone number"
                validators={["required", "matchRegexp:^[5-9][0-9]{8}$"]}
                errorMessages={[
                  "this field is required",
                  "phone number is not valid",
                ]}
              />
            </Grid>
          </Grid>
          <Grid item xs={5}>
            <Grid
              className={classes.column}
              container
              direction="column"
              justify="space-between"
              alignItems="center"
            >
              <TextValidator
                onChange={handleChange}
                value={employeeState.surname}
                name="surname"
                className={classes.textField}
                placeholder="Surname"
                validators={["required", "matchRegexp:^[A-Z][a-z]+$"]}
                errorMessages={[
                  "this field is required",
                  "firstname is not valid",
                ]}
              />
              <TextValidator
                onChange={handleChange}
                value={employeeState.email}
                name="email"
                className={classes.textField}
                placeholder="Email"
                validators={["required", "isEmail"]}
                errorMessages={["this field is required", "email is not valid"]}
              />
            </Grid>
          </Grid>
        </Grid>
        <Grid item>
          <Grid container justify="center">
            <Button
              className={classes.submitButton}
              type="submit"
              variant="contained"
              color="primary"
            >
              Apply
            </Button>
          </Grid>
        </Grid>
      </Grid>
    </ValidatorForm>
  );
};

export default EditEmployee;
