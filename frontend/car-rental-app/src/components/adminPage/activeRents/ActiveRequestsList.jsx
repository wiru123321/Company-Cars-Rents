import React from "react";
import { Grid, makeStyles } from "@material-ui/core";
import RentCard from "./cards/RentCard";
import NotFoundMessage from "../messages/NotFoundMessage";
import FiltersBar from "./filter/FiltersBar";

const ActiveRequestList = ({ rents, menuMode }) => {
  return (
    <Grid container>
      <Grid container direction="column" justify="center" alignItems="center">
        <FiltersBar />
        {rents.length > 0 ? (
          rents.map((rent, index) => {
            const handleMenuModeChange = () => {
              menuMode(rent);
            };
            return (
              <RentCard
                key={rent.id}
                rent={rent}
                handleMenuModeChange={handleMenuModeChange}
              />
            );
          })
        ) : (
          <NotFoundMessage>Active rents not found.</NotFoundMessage>
        )}
      </Grid>
    </Grid>
  );
};

export default ActiveRequestList;
