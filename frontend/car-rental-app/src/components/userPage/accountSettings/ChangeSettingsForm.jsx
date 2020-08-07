import React, { useState, useEffect } from "react";
import { Grid, Typography, Button, TextField } from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import { selectAll } from "../../../features/user-settings/userSettingsSlice";
import { useDispatch, useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core";
/*successMessage,
errorMessage,
showSubmitInfo,*/
const useStyles = makeStyles({
  box: {
    height: "40vh",
  },
  title: { fontSize: "40px" },
  textField: {
    width: "60ch",
  },
});

const ChangeSettingsForm = ({
  changeItem,
  type,
  onSubmit,
  onPasswordChange,
  onItemchange,
  password,
  item,
  itemId,
}) => {
  const dispatch = useDispatch();
  const classes = useStyles();
  const [isSubmit, toggleSubmit] = useState(false);

  const {
    successMessage,
    errorMessage,
    showSubmitInfo,
    changeFormId,
  } = useSelector(selectAll);

  useEffect(() => {
    const timer = setTimeout(() => {
      toggleSubmit(false);
    }, 4000);
    return () => {
      if (isSubmit === true) return clearTimeout(timer);
    };
  }, [isSubmit]);

  const showAlert = () => {
    if (showSubmitInfo === true) {
      return <Alert severity="success">{successMessage}</Alert>;
    } else {
      return <Alert severity="error">{errorMessage}</Alert>;
    }
  };

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        onSubmit();
        toggleSubmit(true);
      }}
    >
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <Typography className={classes.title} variant="h1">
          Change {changeItem}
        </Typography>
        <TextField
          className={classes.textField}
          onChange={(event) => onPasswordChange(event.target.value)}
          value={password}
          margin="normal"
          variant="outlined"
          label="password"
          type="password"
          required
        />
        <TextField
          onChange={(event) => onItemchange(event.target.value)}
          value={item}
          margin="normal"
          variant="outlined"
          className={classes.textField}
          label={`new-${changeItem}`}
          type={type}
          required
        />
        <Button color="primary" variant="contained" type="submit">
          Change
        </Button>
        {isSubmit && changeFormId === itemId && showAlert()}
      </Grid>
    </form>
  );
};

export default ChangeSettingsForm;
