import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, Typography } from "@material-ui/core";
import {
  firstnameChange,
  lastnameChange,
  emailChange,
  loginChange,
  phoneNumberChange,
  passwordChange,
  rePasswordChange,
  toggleDidSubmit,
  selectAll,
  reset,
  addUser,
} from "../../../features/add-employees/addEmployeeSlice";
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
  const employee = useSelector(selectAll);
  const {
    email,
    login,
    phoneNumber,
    password,
    rePassword,
    firstname,
    lastname,
  } = employee;

  useEffect(() => {
    const timer = setTimeout(() => {
      toggleShowSuccess(false);
      isSuccess(false);
    }, 2000);
    return () => {
      if (showSuccess === true) return clearTimeout(timer);
    };
  }, [success, showSuccess]);

  const handleFirstnameChange = (event) =>
    dispatch(firstnameChange(event.target.value));
  const handleLastnameChange = (event) =>
    dispatch(lastnameChange(event.target.value));
  const handleEmailChange = (event) =>
    dispatch(emailChange(event.target.value));
  const handleLoginChange = (event) =>
    dispatch(loginChange(event.target.value));
  const handlePhoneNumberChange = (event) =>
    dispatch(phoneNumberChange(event.target.value));
  const handlePasswordChange = (event) =>
    dispatch(passwordChange(event.target.value));
  const handleRePasswordChange = (event) =>
    dispatch(rePasswordChange(event.target.value));

  function toggleSubmit(toggleValue) {
    dispatch(toggleDidSubmit(toggleValue));
  }

  function resetForm() {
    dispatch(reset());
  }

  function submit(event) {
    event.preventDefault();
    let user = {
      email: email,
      login: login,
      phoneNumber: phoneNumber,
      password: password,
      name: firstname,
      surname: lastname,
      roleDTO: { name: "EMPLOYEE" },
    };
    if (
      password === rePassword &&
      email &&
      login &&
      phoneNumber &&
      password &&
      rePassword &&
      firstname &&
      lastname
    ) {
      dispatch(addUser(user));
      resetForm();
      isSuccess(true);
      toggleShowSuccess(true);
      toggleSubmit(false);
    } else {
      isSuccess(false);
      toggleShowSuccess(true);
      toggleSubmit(true);
    }
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
        <Typography variant="h2">Register user</Typography>
        <UsersPersonalData
          firstname={firstname}
          lastname={lastname}
          handleFirstnameChange={handleFirstnameChange}
          handleLastnameChange={handleLastnameChange}
        />
        <UsersLogin
          email={email}
          login={login}
          phoneNumber={phoneNumber}
          handleEmailChange={handleEmailChange}
          handleLoginChange={handleLoginChange}
          handlePhoneNumberChange={handlePhoneNumberChange}
        />
        <UsersPassword
          password={password}
          rePassword={rePassword}
          handlePasswordChange={handlePasswordChange}
          handleRePasswordChange={handleRePasswordChange}
        />
        <FormControlPanel success={success} showSuccess={showSuccess} />
      </Grid>
    </form>
  );
};

export default AddEmployee;
