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
    <Grid
      className={classes.root}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <TextField
        onChange={handlePasswordChange}
        value={password}
        placeholder="password"
        label="password"
        variant="filled"
        type="password"
        required
      />
      <TextField
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
