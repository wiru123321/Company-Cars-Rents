import axios from "axios";

const API_URL = "http://localhost:8080";

export const fetchFaultsForCar = async (licensePlate, setFaults) => {
  try {
    console.log(licensePlate);
    const response = await axios.get(
      API_URL + `/a/active-faults/${licensePlate}`,
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    console.log(response.data);
    setFaults(response.data);
  } catch (error) {}
};

export const deleteFault = async (fault, licensePlate, setFaults) => {
  try {
    const response = await axios(API_URL + "/a/fault", {
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      method: "DELETE",
      data: fault,
    });
    fetchFaultsForCar(licensePlate, setFaults);
    console.log(response.data);
  } catch (error) {
    console.log(error);
  }
};
