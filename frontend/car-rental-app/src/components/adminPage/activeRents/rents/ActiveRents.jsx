import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Grid, makeStyles } from "@material-ui/core";
import {
  selectAll,
  getActiveRents,
  setCurrentRent,
  setMenuMode,
  filterRents,
} from "../../../../features/rents/activeRentsSlice";
import ActiveRentsMenu from "./ActiveRentsMenu";
import ActiveRequestList from "./ActiveRequestsList";

const useStyles = makeStyles({
  content: {
    minHeight: "80vh",
  },
});

const ActiveRents = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [filters, setFilters] = useState({
    name: "",
    surname: "",
    mark: "",
    model: "",
    licensePlate: "",
  });

  const { filteredRents, enterMenuMode, currentRent, rents } = useSelector(
    selectAll
  );

  useEffect(() => {
    dispatch(getActiveRents());
  }, [dispatch]);

  useEffect(() => {
    dispatch(filterRents(rents, filters));
  }, [dispatch, filters]);

  const menuMode = (rent) => {
    dispatch(setCurrentRent(rent));
    dispatch(setMenuMode(true));
  };

  const exitMenu = () => {
    dispatch(setCurrentRent(""));
    dispatch(setMenuMode(false));
  };

  const handleFilterChange = (event) => {
    setFilters({ ...filters, [event.target.name]: event.target.value });
  };

  const handleFiltersReset = () => {
    setFilters({
      name: "",
      surname: "",
      mark: "",
      model: "",
      licensePlate: "",
    });
  };

  return (
    <Grid className={classes.content}>
      {enterMenuMode === true ? (
        <ActiveRentsMenu exitMenu={exitMenu} currentRent={currentRent} />
      ) : (
        <ActiveRequestList
          menuMode={menuMode}
          rents={filteredRents}
          handleFilterChange={handleFilterChange}
          filters={filters}
          handleReset={handleFiltersReset}
        />
      )}
    </Grid>
  );
};

export default ActiveRents;
