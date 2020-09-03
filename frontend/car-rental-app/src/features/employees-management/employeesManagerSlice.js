import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

export const employeesManagerSlice = createSlice({
  name: "employees",
  initialState: {
    users: [],
    loginFilters: "",
    nameFilters: "",
    filteredEmployees: [],
    didUpdate: false,
    didUpdateSuccess: false,
  },
  reducers: {
    setUsers: (state, action) => {
      state.users = action.payload;
    },

    loginFiltersChange: (state, action) => {
      state.loginFilters = action.payload;
    },

    nameFiltersChange: (state, action) => {
      state.nameFilters = action.payload;
    },

    setFilteredEmployees: (state, action) => {
      state.filteredEmployees = action.payload;
    },

    setUpdateResult: (state, action) => {
      state.didUpdate = true;
      state.didUpdateSuccess = action.payload;
    },

    resetFilters: (state) => {
      state.loginFilters = "";
      state.nameFilters = "";
    },

    resetUpdate: (state) => {
      state.didUpdate = false;
      state.didUpdateSuccess = false;
    },
  },
});

export const {
  setUsers,
  loginFiltersChange,
  nameFiltersChange,
  setFilteredEmployees,
  resetFilters,
  setUpdateResult,
  resetUpdate,
} = employeesManagerSlice.actions;

export const selectAll = (state) => state.employees;

export const selectAllUsers = (state) => state.employees.users;

export const fetchAllUsers = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/users", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(setUsers(response.data));
    dispatch(setFilteredEmployees(response.data));
  } catch (error) {
    console.log(error);
  }
};

export const updateUser = (login, alert, userUpdate) => async (dispatch) => {
  try {
    await axios.put(API_URL + `/a/user/${login}`, userUpdate, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(fetchAllUsers());
    alert.success("Successfully updated user!");
  } catch (err) {
    alert.error("Failed to update user!");
  }
};

export const deleteUser = (login, alert) => async (dispatch) => {
  try {
    await axios.delete(API_URL + `/a/user/${login}`, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    dispatch(fetchAllUsers());
    alert.success("Successfully deleted user!");
  } catch (err) {
    alert.error("Failed to delete user!");
  }
};

export const filterUsers = (users, loginFilters, nameFilters) => (dispatch) => {
  let filteredEmployees = users.filter((employee) =>
    employee.login.toLowerCase().includes(loginFilters.toLowerCase())
  );

  filteredEmployees = filteredEmployees.filter(
    (employee) =>
      employee.name.toLowerCase().includes(nameFilters.toLowerCase()) ||
      employee.surname.toLowerCase().includes(nameFilters.toLowerCase())
  );
  dispatch(setFilteredEmployees(filteredEmployees));
};

export default employeesManagerSlice.reducer;
