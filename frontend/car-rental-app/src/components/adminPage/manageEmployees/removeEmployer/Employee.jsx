import React, { useState } from "react";
import { Grid, Paper } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import EmployerInfo from "./removeEmployerComponent/RemoveEmployerComponent";
import EditEmployee from "./EditEmployee";
import ControlPanel from "./ControlPanel";

const useStyles = makeStyles({
  paper: {
    padding: "12px",
    marginTop: "1%",
  },
});

const Employee = ({ employee, handleDelete, handleUpdate }) => {
  const classes = useStyles();
  const [edit, setEdit] = useState(false);

  const deleteUser = () => {
    handleDelete(employee.login);
  };

  const updateUser = (login, user) => {
    handleUpdate(login, user);
  };

  return (
    <Paper className={classes.paper}>
      <Grid container justify="center" alignItems="center">
        <Grid item xs={3}>
          <EmployerInfo employee={employee} />
        </Grid>
        {edit && (
          <Grid item xs={6}>
            <EditEmployee employee={employee} updateUser={updateUser} />
          </Grid>
        )}
        <Grid item xs={3}>
          <Grid
            container
            direction="column"
            justify="center"
            alignItems="center"
          >
            <ControlPanel
              edit={edit}
              setEdit={setEdit}
              handleDelete={deleteUser}
            />
          </Grid>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default Employee;
