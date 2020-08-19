import React from "react";
import { Grid, Typography } from "@material-ui/core";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import EmailIcon from "@material-ui/icons/Email";
import PhoneIcon from "@material-ui/icons/Phone";

const UserInfo = ({ user }) => {
  return (
    <Grid container justify="space-evenly">
      <Typography>
        <AccountCircleIcon /> {user.name} {user.surname}
      </Typography>
      <Typography>
        <EmailIcon /> {user.email}
      </Typography>
      <Typography>
        <PhoneIcon /> {user.phoneNumber}
      </Typography>
    </Grid>
  );
};

export default UserInfo;
