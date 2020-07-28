import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import {
  toggleDidSubmit,
  selectAll,
  reset,
} from "../../../features/add-employees/addEmployeeSlice";
import FormControlPanel from "./FormControlPanel";
import UsersLogin from "./UsersLogin";
import UsersPassword from "./UsersPassword";
import UsersPersonalData from "./UsersPersonalData";
import addEmployee from "../../../apis/addEmployeeApi";
import useStyles from "./useStyles";

const AddEmployee = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [showSuccess, toggleShowSuccess] = useState(false);
  const [success, isSuccess] = useState(false);
  const employee = useSelector(selectAll);
  const { password, rePassword } = employee;

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
      addEmployee(employee)
        .then((response) => {
          resetForm();
          isSuccess(true);
          toggleShowSuccess(true);
        })
        .catch((error) => {
          console.log(error);
          isSuccess(false);
          toggleShowSuccess(true);
        });
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
