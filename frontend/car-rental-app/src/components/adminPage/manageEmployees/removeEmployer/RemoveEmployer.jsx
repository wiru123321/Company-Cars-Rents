import React, { useEffect } from "react";
import { Container } from "@material-ui/core";
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
} from "../../../../features/add-employees/addEmployeeSlice";
import Employee from "./Employee";
import SearchBar from "../employeesSearchBar/EmployeesSearchBar";

const RemoveEmployer = () => {
  const dispatch = useDispatch();
  const employees = useSelector(selectAllUsers);
  const { loginFilters, nameFilters, filteredEmployees } = useSelector(
    selectAll
  );

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  useEffect(() => {
    dispatch(filterUsers(employees, loginFilters, nameFilters));
  }, [employees, loginFilters, nameFilters]);

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

  return (
    <Container display="flex" style={{ width: "100vw" }}>
      <SearchBar
        loginFilters={loginFilters}
        nameFilters={nameFilters}
        handleLoginsFilterChange={handleLoginsFilterChange}
        handleNameFilterChange={handleNameFilterChange}
      />
      {filteredEmployees.map((employee, index) => (
        <Employee
          key={index}
          handleDelete={handleDelete}
          handleUpdate={handleUpdate}
          employee={employee}
        />
      ))}
    </Container>
  );
};

export default RemoveEmployer;
