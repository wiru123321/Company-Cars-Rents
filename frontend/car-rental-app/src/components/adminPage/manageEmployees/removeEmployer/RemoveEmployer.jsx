import React, { useEffect } from "react";
import { Container, Alert } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAllUsers,
  selectAll,
  fetchAllUsers,
  deleteUser,
  updateUser,
  loginFiltersChange,
  nameFiltersChange,
  filterUsers,
  resetFilters,
} from "../../../../features/employees-management/employeesManagerSlice";
import Employee from "./Employee";
import SearchBar from "../employeesSearchBar/EmployeesSearchBar";
import NotFoundMessage from "../../messages/NotFoundMessage";

const RemoveEmployer = () => {
  const dispatch = useDispatch();

  const {
    users,
    loginFilters,
    nameFilters,
    filteredEmployees,
    didUpdate,
    didUpdateSuccess,
  } = useSelector(selectAll);

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  useEffect(() => {
    dispatch(filterUsers(users, loginFilters, nameFilters));
  }, [users, loginFilters, nameFilters]);

  const handleDelete = (login) => {
    dispatch(deleteUser(login));
  };

  const handleUpdate = (login, user) => {
    dispatch(updateUser(login, user));
  };

  const handleLoginsFilterChange = (event) => {
    dispatch(loginFiltersChange(event.target.value));
  };

  const handleNameFilterChange = (event) => {
    dispatch(nameFiltersChange(event.target.value));
  };
  const handleReseet = () => {
    dispatch(resetFilters());
  };
  return (
    <Container
      display="flex"
      style={{
        width: "100vw",
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
      <SearchBar
        loginFilters={loginFilters}
        nameFilters={nameFilters}
        handleLoginsFilterChange={handleLoginsFilterChange}
        handleNameFilterChange={handleNameFilterChange}
        resetChanges={handleReseet}
      />
      {filteredEmployees.length > 0 ? (
        filteredEmployees.map((employee, index) => (
          <Employee
            key={index}
            handleDelete={handleDelete}
            handleUpdate={handleUpdate}
            employee={employee}
          />
        ))
      ) : (
        <NotFoundMessage>Employees not found.</NotFoundMessage>
      )}
    </Container>
  );
};

export default RemoveEmployer;
