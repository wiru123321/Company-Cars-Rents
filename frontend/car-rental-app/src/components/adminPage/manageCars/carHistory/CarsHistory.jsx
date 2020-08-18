import React, { useEffect, useState } from "react";
import { Grid } from "@material-ui/core";
import { selectAll } from "../../../../features/cars-manager/carsManagerSlice";
import { useSelector } from "react-redux";
import axios from "axios";
import NotFoundMessage from "../../messages/NotFoundMessage";
import CarsHistoryItem from "./CarsHistoryItem";

const API_URL = "http://localhost:8080";

const CarsHistory = () => {
  const { currentCar } = useSelector(selectAll);
  const [history, setHistory] = useState("");
  const fetchHistory = () => {
    axios
      .get(API_URL + "/a/rent/car_history/" + currentCar.licensePlate, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((resp) => {
        console.log(resp.data);
        setHistory(resp.data);
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    fetchHistory();
  }, []);

  return (
    <Grid>
      {history.length > 0 ? (
        history.map((rent, index) => (
          <CarsHistoryItem key={`${rent.id} ${index}`} rent={rent} />
        ))
      ) : (
        <NotFoundMessage>History is empty.</NotFoundMessage>
      )}
    </Grid>
  );
};

export default CarsHistory;
