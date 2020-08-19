import React, { useState } from "react";
import { Grid, Paper, Typography, Button } from "@material-ui/core";
import ChangeEmail from "./ChangeEmail";
import ChangePassword from "./ChangePassword";
import ChangePhoneNumber from "./ChangePhoneNumber";

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
  const [formId, setFormId] = useState(0);

  const getFormById = () => {
    switch (formId) {
      case 0:
        return <ChangeHeader />;
      case 1:
        return <ChangeEmail />;
      case 2:
        return <ChangePhoneNumber />;
      case 3:
        return <ChangePassword />;
      default:
        return <ChangeHeader />;
    }
  };

  return (
    <Grid
      style={{ height: "81vh" }}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      <Paper style={{ padding: "8px", height: "50vh", width: "50vw" }}>
        <Paper style={{ padding: "4px" }}>
          <Grid container justify="space-around" alignItems="center">
            <Button onClick={() => setFormId(1)}>Change email</Button>
            <Button onClick={() => setFormId(2)}>Change phone number</Button>
            <Button onClick={() => setFormId(3)}>Change password</Button>
          </Grid>
        </Paper>

        {getFormById()}
      </Paper>
    </Grid>
  );
};

export default AccountSettings;
