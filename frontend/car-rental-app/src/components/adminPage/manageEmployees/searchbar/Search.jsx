import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAll,
  loginFiltersChange,
  nameFiltersChange,
} from "../../../../features/employees-management/employeesManagerSlice";
import EmployeeSearchBar from "./EmployeesSearchBar";

const Search = () => {
  const dispatch = useDispatch();
  const { loginFilters, nameFilters } = useSelector(selectAll);

  const handleLoginFilterChange = (event) => {
    dispatch(loginFiltersChange(event.target.value));
  };

  const handleNameFilterChange = (event) => {
    dispatch(nameFiltersChange(event.target.value));
  };

  const handleReset = () => {
    dispatch(loginFiltersChange(""));
    dispatch(nameFiltersChange(""));
  };

  return (
    <>
      <EmployeeSearchBar
        loginFilters={loginFilters}
        nameFilters={nameFilters}
        handleLoginFilterChange={handleLoginFilterChange}
        handleNameFilterChange={handleNameFilterChange}
        handleReset={handleReset}
      />
    </>
  );
};

export default Search;
