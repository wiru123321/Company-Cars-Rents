import React from "react";
import { Input, Grid, Button } from "@material-ui/core";
import PhoneIcon from "@material-ui/icons/Phone";
import EmailIcon from "@material-ui/icons/Email";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import SystemUpdateAltIcon from "@material-ui/icons/SystemUpdateAlt";

const EditEmployer = ({ employee }) => {
  const { name, surname, email, phoneNumber } = employee;

  return (
    <form onSubmit={(event) => event.preventDefault()}>
      <Grid
        container
        direction="column"
        justify="center"
        alignItems="center"
        spacing={3}
      >
        <Grid item>
          <AccountCircleIcon />
          <Input placeholder="Firstname" value={name} required />
        </Grid>
        <Grid item>
          <AccountCircleIcon />
          <Input placeholder="Lastname" value={surname} required />
        </Grid>
        <Grid item>
          <EmailIcon />
          <Input placeholder="Email" value={email} required />
        </Grid>
        <Grid item>
          <PhoneIcon />
          <Input placeholder="Phone number" value={phoneNumber} required />
        </Grid>
        <Grid item>
          <Button type="submit">
            <SystemUpdateAltIcon />
            Edit
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

export default EditEmployer;
