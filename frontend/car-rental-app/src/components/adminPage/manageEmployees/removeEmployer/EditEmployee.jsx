import React, { useState } from "react";
import { Input, Grid, Button } from "@material-ui/core";
import PhoneIcon from "@material-ui/icons/Phone";
import EmailIcon from "@material-ui/icons/Email";
import EmployeeAddedAlert from "./EmployeeAddedAlert";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import SystemUpdateAltIcon from "@material-ui/icons/SystemUpdateAlt";

const EditEmployer = ({ employee, updateUser }) => {
  const { login } = employee;
  const [name, setName] = useState(employee.name);
  const [surname, setSurname] = useState(employee.surname);
  const [email, setEmail] = useState(employee.email);
  const [phoneNumber, setPhoneNumber] = useState(employee.phoneNumber);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        updateUser(login, {
          email: email,
          name: name,
          surname: surname,
          phoneNumber: phoneNumber,
        });
      }}
    >
      <Grid
        container
        direction="column"
        justify="center"
        alignItems="center"
        spacing={3}
      >
        <Grid item>
          <AccountCircleIcon />
          <Input
            placeholder="Firstname"
            value={name}
            onChange={(event) => setName(event.target.value)}
            required
          />
        </Grid>
        <Grid item>
          <AccountCircleIcon />
          <Input
            placeholder="Lastname"
            value={surname}
            onChange={(event) => setSurname(event.target.value)}
            required
          />
        </Grid>
        <Grid item>
          <EmailIcon />
          <Input
            placeholder="Email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            required
          />
        </Grid>
        <Grid item>
          <PhoneIcon />
          <Input
            placeholder="Phone number"
            value={phoneNumber}
            onChange={(event) => setPhoneNumber(event.target.value)}
            required
          />
        </Grid>
        <Grid item>
          <Grid
            container
            direction="column"
            justify="center"
            alignItems="center"
          >
            <Button type="submit">
              <SystemUpdateAltIcon />
              Edit
            </Button>
            <EmployeeAddedAlert />
          </Grid>
        </Grid>
      </Grid>
    </form>
  );
};

export default EditEmployer;
