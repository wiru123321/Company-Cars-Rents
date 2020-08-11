import React, { useEffect } from "react";
import { Container } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAllUsers,
  firstnameChange,
  lastnameChange,
  emailChange,
  phoneNumberChange,
  fetchAllUsers,
  deleteUser,
  updateUser,
} from "../../../features/add-employees/addEmployeeSlice";
import Employee from "./Employee";
import SearchBar from "../employeesSearchBar/EmployeesSearchBar";

const RemoveEmployer = () => {
  const dispatch = useDispatch();
  const employees = useSelector(selectAllUsers);

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  const handleDelete = (login) => {
    dispatch(deleteUser(login));
  };

  const handleUpdate = (login, user) => {
    dispatch(updateUser(login, user));
  };

  const handleFirstnameChange = (value) => {
    dispatch(firstnameChange(value));
  };

  const handleLastnameChange = (value) => {
    dispatch(lastnameChange(value));
  };

  const handleEmailChange = (value) => {
    dispatch(emailChange(value));
  };

  const handlePhoneNumberChange = (value) => {
    dispatch(phoneNumberChange(value));
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
      <SearchBar />
      {employees.map((employee, index) => (
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
