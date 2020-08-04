import React from "react";
import { TextField, Grid } from "@material-ui/core";
import useStyles from "./useStyles";

const UsersPersonalData = ({
  firstname,
  lastname,
  handleFirstnameChange,
  handleLastnameChange,
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
        onChange={handleFirstnameChange}
        placeholder="firstname"
        value={firstname}
        type="name"
        label="firstname"
        variant="filled"
        required
      />
      <TextField
        onChange={handleLastnameChange}
        placeholder="lastname"
        value={lastname}
        type="name"
        label="lastname"
        variant="filled"
        required
      />
    </Grid>
  );
};

export default UsersPersonalData;
