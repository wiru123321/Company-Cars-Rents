import React from "react";
import { TextValidator, ValidatorForm } from "react-material-ui-form-validator";
import { Grid, Paper, Button, Typography, makeStyles } from "@material-ui/core";
import PhotoCameraIcon from "@material-ui/icons/PhotoCamera";

const useStyles = makeStyles({
  box: {
    minHeight: "40vh",
  },
  textInput: {
    width: "60ch",
  },
  paper: {
    padding: "8px",
  },
  dateInput: {
    width: "20ch",
  },
  button: {
    width: "24vw",
  },
});

const EditForm = ({
  mileage,
  lastInspection,
  handleMileageChange,
  handleLastInspectionChange,
  handleSubmit,
  handlePhotoUpdate,
}) => {
  const classes = useStyles();
  return (
    <ValidatorForm onSubmit={handleSubmit}>
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="space-evenly"
        alignItems="center"
      >
        <Grid item>
          <Paper className={classes.paper}>
            <TextValidator
              onChange={handleMileageChange}
              value={mileage}
              placeholder="Mileage"
              type="number"
              className={classes.textInput}
              validators={["required"]}
              errorMessages={["this field is required"]}
            />
          </Paper>
        </Grid>
        <Grid item>
          <Paper className={classes.paper}>
            <Typography>Last inspection date:</Typography>
            <TextValidator
              onChange={handleLastInspectionChange}
              value={lastInspection}
              name="lastInspection"
              type="date"
              className={classes.textInput}
              validators={["required"]}
              errorMessages={["this field is required"]}
            />
          </Paper>
        </Grid>
        <Grid item>
          <Button
            className={classes.button}
            variant="contained"
            color="primary"
            type="submit"
          >
            Apply
          </Button>
        </Grid>
        <Grid item>
          <Button
            onClick={handlePhotoUpdate}
            className={classes.button}
            startIcon={<PhotoCameraIcon />}
          >
            Upload photo
          </Button>
        </Grid>
      </Grid>
    </ValidatorForm>
  );
};

export default EditForm;
