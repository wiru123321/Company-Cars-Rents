import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAll,
  toggleFilterActive,
  setMarkFilter,
  setLicensePlateFilter,
} from "../../../../features/cars-manager/carsManagerSlice";
import CarsSearchBar from "./CarsSearchBar";

const Search = () => {
  const dispatch = useDispatch();
  const { filterActive, filterLicensePlate, filterMark } = useSelector(
    selectAll
  );

  const handleLicensePlateFilter = (event) => {
    dispatch(setLicensePlateFilter(event.target.value));
  };

  const handleMarkFilter = (event) => {
    dispatch(setMarkFilter(event.target.value));
  };

  const toggleActiveFilter = () => {
    dispatch(toggleFilterActive(!filterActive));
  };

  const handleReset = () => {
    dispatch(setLicensePlateFilter(""));
    dispatch(setMarkFilter(""));
    dispatch(toggleFilterActive(true));
  };
  return (
    <>
      <CarsSearchBar
        filterActive={filterActive}
        filterLicensePlate={filterLicensePlate}
        filterMark={filterMark}
        handleLicensePlateFilter={handleLicensePlateFilter}
        handleMarkFilter={handleMarkFilter}
        toggleActiveFilter={toggleActiveFilter}
        handleReset={handleReset}
      />
    </>
  );
};

export default Search;
