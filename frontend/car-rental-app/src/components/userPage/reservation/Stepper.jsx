import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Stepper,
  Step,
  StepLabel,
  Button,
  Typography,
  Grid,
} from "@material-ui/core";
import {
  selectStepOne,
  selectStepTwo,
  selectStepThree,
  toggleChoose,
  setFinishForm,
  setisChoosen,
  setChoose,
  dateIsChoosenHandler,
  isCarFormActiveHandler,
  setStepThree,
  setStepFour,
  beginDateChange,
  beginHourChange,
  endDateChange,
  endHourChange,
} from "../../../features/car-reservation/reservationSlice";

import { useDispatch, useSelector } from "react-redux";

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
    marginTop: "2%",
    marginBottom: "3%",
  },
  button: {
    marginRight: theme.spacing(1),
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
}));

function getSteps() {
  return ["Select car rent data...", "Select car...", "Reserv a car!"];
}

function getStepContent(step) {
  switch (step) {
    case 0:
      return "Select car rent data...";
    case 1:
      return "Select car...";
    case 2:
      return "Reserv a car!";
    default:
      return "Unknown step";
  }
}

const HorizontalLinearStepper = ({
  sumbitDataHander,
  isNextDataStep,
  backToDataFrom,
  isCarSelectStep,
}) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [activeStep, setActiveStep] = React.useState(0);
  const steps = getSteps();
  const StepOne = useSelector(selectStepOne);
  const StepTwo = useSelector(selectStepTwo);
  const StepThree = useSelector(selectStepThree);

  const isStepOptional = (step) => {
    return step === 1;
  };

  const handleNext = () => {
    if (StepOne && !StepThree) {
      sumbitDataHander();
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
    if (StepTwo && !StepThree) {
      dispatch(toggleChoose());
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
    if (StepThree) {
      dispatch(setFinishForm());
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
  };

  const handleBack = () => {
    if (!StepOne && StepTwo) {
      setActiveStep((prevActiveStep) => prevActiveStep - 1);
      backToDataFrom();
    }
    if (!StepTwo) {
      dispatch(toggleChoose());
      setActiveStep((prevActiveStep) => prevActiveStep - 1);
    }
  };

  const handleReset = () => {
    setActiveStep(0);
    dispatch(setisChoosen());
    dispatch(setChoose());
    dispatch(dateIsChoosenHandler());
    dispatch(isCarFormActiveHandler());
    dispatch(setStepThree());
    dispatch(setStepFour());
    dispatch(setFinishForm());
    dispatch(beginDateChange(""));
    dispatch(beginHourChange(""));
    dispatch(endDateChange(""));
    dispatch(endHourChange(""));
  };

  return (
    <div className={classes.root}>
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      <Grid container direction="row" justify="center">
        {activeStep === steps.length ? (
          <div>
            <Typography className={classes.instructions}>
              All steps completed - you&apos;re finished
            </Typography>
            <Button onClick={handleReset} className={classes.button}>
              Reset
            </Button>
          </div>
        ) : (
          <div>
            <Typography className={classes.instructions}>
              {getStepContent(activeStep)}
            </Typography>
            <div>
              <Button
                disabled={activeStep === 0}
                onClick={handleBack}
                className={classes.button}
              >
                Back
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={handleNext}
                className={classes.button}
              >
                {activeStep === steps.length - 1 ? "Finish" : "Next"}
              </Button>
            </div>
          </div>
        )}
      </Grid>
    </div>
  );
};
export default HorizontalLinearStepper;
