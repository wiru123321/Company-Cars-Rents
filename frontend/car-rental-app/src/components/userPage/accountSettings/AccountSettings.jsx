import React, { useState } from "react";
import { Grid, Paper, Typography, Button } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  toggleForm,
  updateEmail,
  updatePhoneNumber,
  updatePassword,
  setNewEmail,
  setNewPassword,
  setPassword,
  setNewPhoneNumber,
  selectAll,
} from "../../../features/user-settings/userSettingsSlice";
import ChangeSettingsForm from "./ChangeSettingsForm";
import SettingsIcon from "@material-ui/icons/Settings";

const ChangeHeader = () => (
  <Grid
    style={{ height: "40vh" }}
    container
    direction="column"
    justify="center"
    alignItems="center"
  >
    <Typography style={{ textAlign: "center" }} variant="h4">
      Edit your account details.
    </Typography>
  </Grid>
);

const AccountSettings = () => {
  const dispatch = useDispatch();
  const {
    changeFormId,
    password,
    newEmail,
    newPassword,
    newPhoneNumber,
  } = useSelector(selectAll);

  const handleEmailSubmit = () => {
    dispatch(updateEmail({ password, newEmail }));
  };
  const handlePhoneNumberSubmit = () => {
    dispatch(updatePhoneNumber({ password, newPhoneNumber }));
  };
  const handlePasswordSubmit = () => {
    dispatch(updatePassword({ password, newPassword }));
  };

  const getFormById = () => {
    switch (changeFormId) {
      case 0:
        return <ChangeHeader />;
      case 1:
        return (
          <ChangeSettingsForm
            changeItem="email"
            type="email"
            password={password}
            item={newEmail}
            onSubmit={handleEmailSubmit}
            onPasswordChange={(value) => dispatch(setPassword(value))}
            onItemchange={(value) => dispatch(setNewEmail(value))}
          />
        );
      case 2:
        return (
          <ChangeSettingsForm
            changeItem="phone-number"
            type="number"
            password={password}
            item={newPhoneNumber}
            onSubmit={handlePhoneNumberSubmit}
            onPasswordChange={(value) => dispatch(setPassword(value))}
            onItemchange={(value) => dispatch(setNewPhoneNumber(value))}
          />
        );
      case 3:
        return (
          <ChangeSettingsForm
            changeItem="password"
            type="password"
            password={password}
            item={newPassword}
            onSubmit={handlePasswordSubmit}
            onPasswordChange={(value) => dispatch(setPassword(value))}
            onItemchange={(value) => dispatch(setNewPassword(value))}
          />
        );
      default:
        return <ChangeHeader />;
    }
  };

  return (
    <Grid
      style={{ height: "80vh" }}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <Paper style={{ padding: "8px", height: "50vh", width: "50vw" }}>
        <Paper style={{ padding: "4px" }}>
          <Grid container justify="space-around" alignItems="center">
            <Button onClick={() => dispatch(toggleForm(1))}>
              Change email
            </Button>
            <Button onClick={() => dispatch(toggleForm(2))}>
              Change phone number
            </Button>
            <Button onClick={() => dispatch(toggleForm(3))}>
              Change password
            </Button>
          </Grid>
        </Paper>
        {getFormById()}
      </Paper>
    </Grid>
  );
};

export default AccountSettings;
