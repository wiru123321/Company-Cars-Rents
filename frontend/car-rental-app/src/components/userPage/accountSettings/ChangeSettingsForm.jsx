import React, { useState, useEffect } from "react";
import { Grid, Typography, Button, TextField } from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
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

const ChangeSettingsForm = ({ changeItem, type, onSubmit }) => {
  const classes = useStyles();
  const [isSubmit, toggleSubmit] = useState(false);
  const [password, setPassword] = useState("");
  const [itemValue, setItemValue] = useState("");

  useEffect(() => {
    const timer = setTimeout(() => {
      toggleSubmit(false);
    }, 4000);
    return () => {
      if (isSubmit === true) return clearTimeout(timer);
    };
  }, [isSubmit]);

  const showAlert = () => {
    return <Alert severity="success" />;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit({ password, itemValue });
    toggleSubmit(true);
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
          Change {changeItem}
        </Typography>
        <TextField
          className={classes.textField}
          onChange={(event) => setPassword(event.target.value)}
          value={password}
          margin="normal"
          variant="outlined"
          label="password"
          type="password"
          required
        />
        <TextField
          onChange={(event) => setItemValue(event.target.value)}
          value={itemValue}
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
        {isSubmit && showAlert()}
      </Grid>
    </form>
  );
};

export default ChangeSettingsForm;
