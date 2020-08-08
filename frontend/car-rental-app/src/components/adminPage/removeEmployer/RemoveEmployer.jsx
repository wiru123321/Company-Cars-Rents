import React, { useState, useEffect } from "react";
import { Container, Grid, Paper } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import {
  selectAllUsers,
  fetchAllUsers,
  deleteUser,
} from "../../../features/add-employees/addEmployeeSlice";
import EmployerInfo from "./removeEmployerComponent/RemoveEmployerComponent";
import EditEmployee from "./EditEmployee";
import ControlPanel from "./ControlPanel";

const useStyles = makeStyles({
  paper: {
    padding: "12px",
    marginTop: "1%",
  },
});

const Employee = ({ employee, handleDelete }) => {
  const classes = useStyles();
  const [edit, setEdit] = useState(false);

  const deleteUser = () => {
    handleDelete(employee.login);
  };

  return (
    <Paper className={classes.paper}>
      <Grid container justify="center" alignItems="center">
        <Grid item xs={3}>
          <EmployerInfo employee={employee} />
        </Grid>
        {edit && (
          <Grid item xs={6}>
            <EditEmployee employee={employee} />
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

const RemoveEmployer = () => {
  const dispatch = useDispatch();
  const employees = useSelector(selectAllUsers);

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  const handleDelete = (login) => {
    dispatch(deleteUser(login));
  };

  return (
    <Container display="flex" style={{ width: "100vw" }}>
      {employees.map((employee, index) => (
        <Employee key={index} handleDelete={handleDelete} employee={employee} />
      ))}
    </Container>
  );
};

export default RemoveEmployer;
