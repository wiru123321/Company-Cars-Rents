import React, { useEffect } from "react";
import Alert from "@material-ui/lab/Alert";
import { Grid, Typography, Button, TextField } from "@material-ui/core";
import {
  setPassword,
  setNewPhoneNumber,
  selectAll,
  stopDisplayingResults,
  updatePhoneNumber,
} from "../../../features/user-settings/userPhoneNumberSettingsSlice";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  box: {
    height: "40vh",
  },
  title: { fontSize: "40px" },
  textField: {
    width: "60ch",
  },
});

const ChangePhoneNumber = () => {
  const dispatch = useDispatch();
  const classes = useStyles();
  const {
    password,
    newPhoneNumber,
    responseMessage,
    isResultOk,
    isSubmit,
    showResult,
  } = useSelector(selectAll);

  const handleSubmit = (event) => {
    event.preventDefault();
    dispatch(updatePhoneNumber({ password, newPhoneNumber }));
  };

  useEffect(() => {
    const timer = setTimeout(() => {
      dispatch(stopDisplayingResults());
    }, 300);
    return () => {
      if (showResult === true) return clearTimeout(timer);
    };
  }, [isSubmit]);

  const showAlert = () => {
    if (isResultOk === true) {
      return <Alert severity="success">{responseMessage}</Alert>;
    } else {
      return <Alert severity="error">{responseMessage}</Alert>;
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <Typography className={classes.title} variant="h1">
          Change phone number
        </Typography>
        <TextField
          className={classes.textField}
          onChange={(event) => dispatch(setPassword(event.target.value))}
          value={password}
          margin="normal"
          variant="outlined"
          label="password"
          type="password"
          required
        />
        <TextField
          onChange={(event) => dispatch(setNewPhoneNumber(event.target.value))}
          value={newPhoneNumber}
          margin="normal"
          variant="outlined"
          className={classes.textField}
          label="new-phone-number"
          type="phone"
          required
        />
        <Button color="primary" variant="contained" type="submit">
          Change
        </Button>
        {showResult && showAlert()}
      </Grid>
    </form>
  );
};

export default ChangePhoneNumber;
