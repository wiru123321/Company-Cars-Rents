import React from "react";
import { Typography, Divider } from "@material-ui/core";
import PhoneIcon from "@material-ui/icons/Phone";
import EmailIcon from "@material-ui/icons/Email";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import FingerprintIcon from "@material-ui/icons/Fingerprint";

const RemoveEmployerComponent = ({ employee }) => {
  const { name, surname, email, login, phoneNumber, roleDTO } = employee;
  return (
    <>
      <Typography gutterBottom variant="h5">
        {name} {surname}
      </Typography>
      <Divider />
      <Typography
        variant="h5"
        component="h2"
        style={{ wordWrap: "break-word" }}
      >
        <AccountCircleIcon /> {login}
      </Typography>
      <Typography
        variant="h5"
        component="h2"
        style={{ wordWrap: "break-word" }}
      >
        <EmailIcon /> {email}
      </Typography>
      <Typography
        variant="h5"
        component="h2"
        style={{ wordWrap: "break-word" }}
      >
        <PhoneIcon /> {phoneNumber}
      </Typography>
      <Typography
        variant="h5"
        component="h2"
        style={{ wordWrap: "break-word" }}
      >
        <FingerprintIcon /> {roleDTO.name}
      </Typography>
    </>
  );
};

export default RemoveEmployerComponent;
