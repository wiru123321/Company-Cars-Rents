import React from "react";
import {
  TextField,
  Grid,
  Select,
  MenuItem,
  InputLabel,
} from "@material-ui/core";
import useStyles from "./useStyles";

const UsersPersonalData = ({
  firstname,
  lastname,
  role,
  handleFirstnameChange,
  handleLastnameChange,
}) => {
  const classes = useStyles();

  return (
    <Grid container direction="column" justify="center" alignItems="center">
      <TextField
        className={classes.textArea}
        onChange={handleFirstnameChange}
        placeholder="firstname"
        value={firstname}
        type="name"
        label="firstname"
        variant="filled"
        required
      />
      <TextField
        className={classes.textArea}
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
