import axios from "axios";

const API_URL = "http://localhost:8080";

export const updateEmail = async ({ password, itemValue }) => {
  try {
    let editAccount = { password: password, newEmail: itemValue };
    const response = await axios.put(API_URL + "/e/modify/email", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    if (error.response.status === 406) {
      console.log(error);
    } else {
      console.log(error);
    }
  }
};

export const updatePhoneNumber = async ({ password, itemValue }) => {
  try {
    let editAccount = { password: password, newPhoneNumber: itemValue };
    const response = await axios.put(API_URL + "/e/modify/phone", editAccount, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
  } catch (error) {
    if (error.response.status === 406) {
      console.log(error);
    } else {
      console.log(error);
    }
  }
};

export const updatePassword = async ({ password, itemValue }) => {
  try {
    let editAccount = { password: password, newPassword: itemValue };
    const response = await axios.put(
      API_URL + "/e/modify/password",
      editAccount,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
  } catch (error) {
    if (error.response.status === 406) {
      console.log(error);
    } else {
      console.log(error);
    }
  }
};
