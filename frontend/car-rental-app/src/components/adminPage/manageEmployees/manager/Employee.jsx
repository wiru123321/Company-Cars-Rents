import React, { useState } from "react";
import {
  Grid,
  Paper,
  Typography,
  Divider,
  makeStyles,
  Button,
} from "@material-ui/core";
import EditEmployee from "../edit/EditEmployee";
import FaceIcon from "@material-ui/icons/Face";
import AccountBoxIcon from "@material-ui/icons/AccountBox";
import PhoneIcon from "@material-ui/icons/Phone";
import EmailIcon from "@material-ui/icons/Email";
import FingerprintIcon from "@material-ui/icons/Fingerprint";

const useStyles = makeStyles({
  card: {
    minHeight: "20vh",
    minWidth: "45vw",
  },
  paper: {
    marginTop: "1%",
    padding: "8px",
  },
  title: {
    fontSize: "30px",
    textAlign: "center",
  },
  text: {
    fontSize: "25px",
  },
  details: {
    marginTop: "2%",
    minHeight: "20vh",
  },
  navPanel: {
    padding: "8px",
  },
});

const Employee = ({ employee, handleDelete, handleUpdate }) => {
  const classes = useStyles();
  const [edit, setEdit] = useState(false);
  const [employeeState, setEmployeeState] = useState({
    name: employee.name,
    surname: employee.surname,
    phoneNumber: employee.phoneNumber,
    email: employee.email,
  });

  const handleChange = (event) => {
    setEmployeeState({
      ...employeeState,
      [event.target.name]: event.target.value,
    });
  };

  const handleEdit = () => {
    handleUpdate(employee.login, employeeState);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    handleEdit();
    setEdit(false);
  };

  const handleRemove = () => {
    handleDelete(employee.login);
  };

  const toggleEdit = () => {
    setEdit(!edit);
  };

  return (
    <Paper elevation={6} className={classes.paper}>
      <Grid className={classes.card}>
        <Typography className={classes.title}>
          <FaceIcon /> {employee.name} {employee.surname}
        </Typography>
        <Divider />
        {edit ? (
          <EditEmployee
            employeeState={employeeState}
            handleChange={handleChange}
            handleSubmit={handleSubmit}
          />
        ) : (
          <EmployeeDetails employee={employee} />
        )}
        <Divider />
        <ControlPanel
          edit={edit}
          toggleEdit={toggleEdit}
          handleRemove={handleRemove}
        />
      </Grid>
    </Paper>
  );
};

const ControlPanel = ({ edit, toggleEdit, handleRemove }) => {
  const classes = useStyles();

  return (
    <Grid className={classes.navPanel} container justify="space-evenly">
      <Button onClick={toggleEdit} variant="contained" color="primary">
        {edit ? "Info" : "Edit"}
      </Button>
      <Button onClick={handleRemove} variant="contained" color="secondary">
        Remove
      </Button>
    </Grid>
  );
};

const EmployeeDetails = ({ employee }) => {
  const classes = useStyles();

  return (
    <Grid
      className={classes.details}
      container
      justify="space-evenly"
      alignItems="center"
    >
      <Grid item>
        <Typography className={classes.text}>
          <AccountBoxIcon /> {employee.login}
        </Typography>
        <Typography className={classes.text}>
          <EmailIcon /> {employee.email}
        </Typography>
      </Grid>
      <Grid item>
        <Typography className={classes.text}>
          <PhoneIcon /> {employee.phoneNumber}
        </Typography>
        <Typography className={classes.text}>
          <FingerprintIcon /> {employee.roleDTO.name}
        </Typography>
      </Grid>
    </Grid>
  );
};
export default Employee;
