import React from "react";
import { TextField, Grid } from "@material-ui/core";
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
      <TextField
        className={classes.textArea}
        onChange={handlePasswordChange}
        value={password}
        placeholder="password"
        label="password"
        variant="filled"
        type="password"
        required
      />
      <TextField
        className={classes.textArea}
        onChange={handleRePasswordChange}
        value={rePassword}
        placeholder="repeatPassword"
        label="repeat password"
        variant="filled"
        type="password"
        required
      />
    </Grid>
  );
};

export default UsersPassword;
