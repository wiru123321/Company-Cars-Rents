import React, { useEffect, useState } from "react";
import { Grid } from "@material-ui/core";
import {
  selectAll,
  fetchHistory,
} from "../../../../features/cars-manager/carsManagerSlice";
import { useSelector, useDispatch } from "react-redux";
import NotFoundMessage from "../../messages/NotFoundMessage";
import CarsHistoryItem from "./CarsHistoryItem";

const CarsHistory = () => {
  const dispatch = useDispatch();
  const { currentCar } = useSelector(selectAll);
  const [history, setHistory] = useState([]);

  useEffect(() => {
    dispatch(fetchHistory(currentCar.licensePlate, setHistory));
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
