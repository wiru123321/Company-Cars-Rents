import React from "react";
import { Grid, Typography, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";

const useStyles = makeStyles({
  title: {
    fontSize: "40px",
  },
  row: {
    marginTop: "2%",
    width: "40ch",
  },
  box: {
    height: "30vh",
    width: "40vw",
  },
});

const UpdateCarsForm = ({
  licensePlate,
  mileage,
  lastInspection,
  licensePlateChange,
  mileageChange,
  lastInspectionChange,
}) => {
  const classes = useStyles();
  return (
    <Grid
      className={classes.box}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <Typography className={classes.title} variant="h1">
        Edit car specs
      </Typography>
      <TextValidator
        className={classes.row}
        onChange={(event) => {
          licensePlateChange(event.target.value);
        }}
        value={licensePlate}
        name="licensePlate"
        label="License plate"
        type="text"
        validators={["required"]}
        errorMessages={["this field is required"]}
      />
      <TextValidator
        className={classes.row}
        onChange={(event) => {
          mileageChange(event.target.value);
        }}
        value={mileage}
        name="mileage"
        label="mileage"
        type="number"
        validators={["required"]}
        errorMessages={["this field is required"]}
      />
      <TextValidator
        className={classes.row}
        onChange={(event) => {
          lastInspectionChange(event.target.value);
        }}
        value={lastInspection}
        label="Last inspection"
        name="lastInspection"
        type="date"
        validators={["required"]}
        errorMessages={["this field is required"]}
      />

      <Button className={classes.row} type="submit">
        Edit
      </Button>
    </Grid>
  );
};

export default UpdateCarsForm;
