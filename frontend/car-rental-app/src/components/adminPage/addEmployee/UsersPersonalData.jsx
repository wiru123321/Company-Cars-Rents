import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  firstnameChange,
  lastnameChange,
  selectAll,
} from "../../../features/add-employees/addEmployeeSlice";
import { TextField, Grid } from "@material-ui/core";
import useStyles from "./useStyles";

const UsersPersonalData = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const { firstname, lastname } = useSelector(selectAll);

  const handleFirstnameChange = (event) =>
    dispatch(firstnameChange(event.target.value));
  const handleLastnameChange = (event) =>
    dispatch(lastnameChange(event.target.value));
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
        value={firstname}
        type="name"
        label="firstname"
        variant="filled"
        required
      />
      <TextField
        onChange={handleLastnameChange}
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
