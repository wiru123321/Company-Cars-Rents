import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@material-ui/core";
import {
  selectAll,
  getActiveRents,
  setCurrentRent,
  setMenuMode,
} from "../../../features/rents/activeRentsSlice";
import ActiveRentsMenu from "./ActiveRentsMenu";
import ActiveRequestList from "./ActiveRequestsList";

const ActiveRents = () => {
  const dispatch = useDispatch();
  const { rents, enterMenuMode, currentRent } = useSelector(selectAll);

  useEffect(() => {
    dispatch(getActiveRents());
  }, [dispatch]);

  const menuMode = (rent) => {
    dispatch(setCurrentRent(rent));
    dispatch(setMenuMode(true));
  };

  const exitMenu = () => {
    dispatch(setCurrentRent(""));
    dispatch(setMenuMode(false));
  };

  return (
    <Grid style={{ minHeight: "80vh" }}>
      {enterMenuMode === true ? (
        <ActiveRentsMenu exitMenu={exitMenu} currentRent={currentRent} />
      ) : (
        <ActiveRequestList menuMode={menuMode} rents={rents} />
      )}
    </Grid>
  );
};

export default ActiveRents;
