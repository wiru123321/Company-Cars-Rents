import React, { useEffect } from "react";
import { Container, List } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  fetchCars,
  selectAll,
  deleteCar,
  filterCars,
  setLicenseFilters,
  setMarkFilters,
  fetchActiveCars,
  fetchInactiveCars,
} from "../../../../features/car-management/carManagerSlice";
import SearchBar from "../carsSearchBar/CarsSearchBar";
import Car from "./Car";
import NotFoundMessage from "./NotFoundMessage";

const RemoveCar = () => {
  const { cars, filteredCars, filterLicensePlate, filterMark } = useSelector(
    selectAll
  );
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchCars());
  }, []);

  useEffect(() => {
    dispatch(filterCars(cars, filterLicensePlate, filterMark));
  }, [cars, filterLicensePlate, filterMark]);

  const handleCarDelete = (index) => {
    let { licensePlate } = filteredCars[index];
    dispatch(deleteCar(licensePlate));
  };
  const handleLicensePlateChange = (value) => {
    dispatch(setLicenseFilters(value));
  };

  const handleMarkChange = (value) => {
    dispatch(setMarkFilters(value));
  };

  const updateFilters = () => {
    dispatch(filterCars(cars, filterLicensePlate, filterMark));
  };

  const getInactiveCars = () => {
    dispatch(fetchInactiveCars());
  };

  const getActiveCars = () => {
    dispatch(fetchActiveCars());
  };

  const reset = () => {
    dispatch(setLicenseFilters(""));
    dispatch(setMarkFilters(""));
  };
  return (
    <Container>
      <>
        <SearchBar
          searchLicesnsePlate={filterLicensePlate}
          searchMark={filterMark}
          handleLicensePlateChange={handleLicensePlateChange}
          handleMarkChange={handleMarkChange}
          updateFilters={updateFilters}
          getInactiveCars={getInactiveCars}
          getActiveCars={getActiveCars}
          reset={reset}
        />
      </>
      <List>
        {filteredCars.length > 0 ? (
          filteredCars.map((car, index) => (
            <Car
              key={index}
              car={car}
              index={index}
              onDelete={handleCarDelete}
            />
          ))
        ) : (
          <NotFoundMessage />
        )}
      </List>
    </Container>
  );
};

export default RemoveCar;
