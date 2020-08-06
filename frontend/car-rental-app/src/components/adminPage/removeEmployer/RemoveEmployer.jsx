import React, { useState, useEffect } from "react";
import { Container, Button, Grid, Paper, Divider } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import EmployerInfo from "./removeEmployerComponent/RemoveEmployerComponent";
import {
  selectAllUsers,
  fetchAllUsers,
  deleteUser,
} from "../../../features/add-employees/addEmployeeSlice";
import EditEmployee from "./EditEmployee";

const useStyles = makeStyles({
  paper: {
    padding: "12px",
    marginTop: "1%",
  },
});

const ControlPanel = ({ setEdit, handleDelete, edit }) => {
  return (
    <div>
      <Button
        variant="contained"
        color="primary"
        startIcon={<EditIcon />}
        onClick={(event) => setEdit(!edit)}
      >
        Edit
      </Button>
      <Button
        variant="contained"
        color="secondary"
        startIcon={<DeleteIcon />}
        style={{ marginLeft: "2vw" }}
        onClick={handleDelete}
      >
        Remove
      </Button>
    </div>
  );
};

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
        <Grid item xs={6}>
          {edit && <EditEmployee employee={employee} />}
        </Grid>
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
