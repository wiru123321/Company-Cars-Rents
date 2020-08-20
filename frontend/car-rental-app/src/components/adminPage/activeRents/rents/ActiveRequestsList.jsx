import React from "react";
import { Grid, makeStyles, Paper } from "@material-ui/core";
import RentCard from "../cards/RentCard";
import NotFoundMessage from "../../messages/NotFoundMessage";
import FiltersBar from "../filter/FiltersBar";

const useStyles = makeStyles({
  title: {
    margin: "2%",
    fontSize: "2rem",
  },
  nav: {
    margin: "1%",
    padding: "18px",
    backgroundColor: "#A9A9A9",
    minWidth: "70vw",
  },
  paper: {
    margin: "2%",
    padding: "8px",
    minHeight: "30vh",
    minWidth: "50vw",
  },
  card: {
    margin: "2%",
    padding: "8px",
    backgroundColor: "#DCDCDC",
  },
});

const ActiveRequestList = ({
  rents,
  menuMode,
  handleFilterChange,
  filters,
  handleReset,
}) => {
  const classes = useStyles();

  return (
    <Grid container>
      <Grid container direction="column" justify="center" alignItems="center">
        <Paper elevation={6} className={classes.nav}>
          <FiltersBar
            handleChange={handleFilterChange}
            filters={filters}
            handleReset={handleReset}
          />
        </Paper>
        <Grid>
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
    </Grid>
  );
};

export default ActiveRequestList;
