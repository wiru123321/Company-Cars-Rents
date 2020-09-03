import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { Grid, Button, makeStyles, Divider } from "@material-ui/core";
import {
  fetchCarsBetweenDates,
  changeRentCar,
} from "../../../../features/rents/activeRentsSlice";
import CurrentCar from "./CurrentCar";
import CarItem from "./CarItem";
import NotFound from "../../messages/NotFoundMessage";

const useStyles = makeStyles({
  button: {
    margin: "2% 0",
    width: "100%",
  },
  content: {
    margin: "2% 0",
  },
});

const ChangeCar = ({ car, rent, dateFrom, dateTo }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [cars, setCars] = useState([]);
  const [search, setSearch] = useState(false);

  useEffect(() => {
    fetchCars();
  }, []);

  const toggleSearch = () => {
    setSearch(!search);
  };

  const fetchCars = () => {
    let date = {
      dateFrom,
      dateTo,
    };
    dispatch(fetchCarsBetweenDates(date, setCars));
  };

  const changeCar = (licensePlate, alert) => {
    dispatch(changeRentCar(rent.id, licensePlate, alert));
    setSearch(false);
  };

  const getCurrentView = () => {
    if (search) {
      return (
        <>
          {cars.length > 0 ? (
            cars.map((car, index) => (
              <CarItem key={car.licensePlate} car={car} changeCar={changeCar} />
            ))
          ) : (
            <NotFound>Could not find cars.</NotFound>
          )}
        </>
      );
    } else {
      return <CurrentCar car={car} />;
    }
  };

  return (
    <Grid>
      <Button
        className={classes.button}
        onClick={toggleSearch}
        variant="outlined"
        color={search ? "secondary" : "primary"}
      >
        {search ? "Dismiss" : "Find new car"}
      </Button>
      <Divider />
      <Grid className={classes.content}>{getCurrentView()}</Grid>
    </Grid>
  );
};

export default ChangeCar;
