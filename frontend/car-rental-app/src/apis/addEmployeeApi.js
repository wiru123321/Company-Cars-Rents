import axios from "axios";

const API_URL = "";

export default function addEmployee({
  firstname,
  lastname,
  email,
  phoneNumber,
  password,
}) {
  return axios.post(`${API_URL}`, {
    params: {
      firstname: firstname,
      surname: lastname,
      email: email,
      phoneNumber: phoneNumber,
      password: password,
      isActive: true,
    },
  });
}
