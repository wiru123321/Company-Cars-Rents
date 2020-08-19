import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, makeStyles } from "@material-ui/core";
import {
  fetchAllUsers,
  selectAll,
  filterUsers,
  deleteUser,
  updateUser,
} from "../../../../features/employees-management/employeesManagerSlice";
import NotFoundMessage from "../../messages/NotFoundMessage";
import Search from "../searchbar/Search";
import Employee from "./Employee";
import { useAlert } from "react-alert";

const useStyles = makeStyles({
  content: {
    minHeight: "80vh",
  },
});

const EmployeesManager = () => {
  const alert = useAlert();
  const classes = useStyles();
  const dispatch = useDispatch();
  const { users, filteredEmployees, loginFilters, nameFilters } = useSelector(
    selectAll
  );

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  useEffect(() => {
    dispatch(filterUsers(users, loginFilters, nameFilters));
  }, [loginFilters, nameFilters]);

  const handleDelete = (login) => {
    dispatch(deleteUser(login, alert));
  };
  const handleUpdate = (login, user) => {
    dispatch(updateUser(login, alert, user));
  };
  return (
    <Grid className={classes.content}>
      <Grid container justify="center">
        <Search />
      </Grid>
      <Grid container direction="column" alignItems="center">
        {filteredEmployees.length > 0 ? (
          filteredEmployees.map((employee, index) => (
            <Employee
              key={employee.login + index}
              employee={employee}
              handleDelete={handleDelete}
              handleUpdate={handleUpdate}
            />
          ))
        ) : (
          <NotFoundMessage>Empty</NotFoundMessage>
        )}
      </Grid>
    </Grid>
  );
};

export default EmployeesManager;
