import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  toggleDidSubmit,
  selectAll,
  reset,
} from "../../../features/add-employees/addEmployeeSlice";
import { Grid } from "@material-ui/core";
import FormControlPanel from "./FormControlPanel";
import UsersLogin from "./UsersLogin";
import UsersPassword from "./UsersPassword";
import UsersPersonalData from "./UsersPersonalData";
import useStyles from "./useStyles";

const AddEmployee = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [showSuccess, toggleShowSuccess] = useState(false);
  const [success, isSuccess] = useState(false);
  const { password, rePassword } = useSelector(selectAll);

  useEffect(() => {
    const timer = setTimeout(() => {
      toggleShowSuccess(false);
      isSuccess(false);
    }, 2000);
    return () => {
      if (showSuccess === true) return clearTimeout(timer);
    };
  }, [success, showSuccess]);

  function toggleSubmit(toggleValue) {
    dispatch(toggleDidSubmit(toggleValue));
  }

  function resetForm() {
    dispatch(reset());
  }

  function submit(event) {
    event.preventDefault();
    if (password === rePassword) {
      resetForm();
      isSuccess(true);
      toggleShowSuccess(true);
    }
    toggleSubmit(true);
  }

  return (
    <form onSubmit={submit}>
      <Grid
        className={classes.root}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <UsersPersonalData />
        <UsersLogin />
        <UsersPassword />
        <FormControlPanel success={success} showSuccess={showSuccess} />
      </Grid>
    </form>
  );
};

export default AddEmployee;
