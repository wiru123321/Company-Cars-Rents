import React from "react";
import { Grid, Typography } from "@material-ui/core";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import EmailIcon from "@material-ui/icons/Email";
import PhoneIcon from "@material-ui/icons/Phone";

const UserInfo = ({ rent }) => {
  return (
    <Grid container justify="space-evenly">
      <Typography>
        <AccountCircleIcon /> {rent.userRentInfo.name}{" "}
        {rent.userRentInfo.surname}
      </Typography>
      <Typography>
        <EmailIcon /> {rent.userRentInfo.email}
      </Typography>
      <Typography>
        <PhoneIcon /> {rent.userRentInfo.phoneNumber}
      </Typography>
    </Grid>
  );
};

export default UserInfo;
