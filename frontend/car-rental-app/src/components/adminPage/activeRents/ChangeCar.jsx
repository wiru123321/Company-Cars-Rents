import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { Grid, Button, Paper, Typography } from "@material-ui/core";
import {
  fetchCarsBetweenDates,
  changeRentCar,
} from "../../../features/rents/activeRentsSlice";
import { useAlert } from "react-alert";
import CarInfo from "../../carsListing/CarInfo";
import CarImage from "../../carsListing/CarImage";
import NotFound from "../messages/NotFoundMessage";

const API_URL = "http://localhost:8080";

const ChangeCar = ({ car, rent, dateFrom, dateTo }) => {
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
    let dateFromDateTo = {
      dateFrom,
      dateTo,
    };
    dispatch(fetchCarsBetweenDates(dateFromDateTo, setCars));
  };

  const changeCar = (licensePlate, alert) => {
    dispatch(changeRentCar(rent.id, licensePlate, alert));
  };

  const getView = () => {
    if (search) {
      return (
        <>
          {cars.length > 0 ? (
            cars.map((car, index) => (
              <Car key={car.licensePlate} car={car} changeCar={changeCar} />
            ))
          ) : (
            <NotFound>Not found cars.</NotFound>
          )}
        </>
      );
    } else {
      return (
        <Paper>
          <Typography>Current rent</Typography>
          <Grid container justify="center">
            <Grid item>
              <CarImage
                src={API_URL + "/u/car/download-car-image/" + car.licensePlate}
              />
            </Grid>
            <Grid item>
              <CarInfo car={car} />
            </Grid>
          </Grid>
        </Paper>
      );
    }
  };

  return (
    <Grid>
      <Button
        style={{ width: "100%" }}
        onClick={toggleSearch}
        variant="outlined"
      >
        Find car
      </Button>
      {getView()}
    </Grid>
  );
};

const Car = ({ car, changeCar }) => {
  const alert = useAlert();
  const handleCarChange = () => {
    changeCar(car.licensePlate, alert);
  };

  return (
    <Grid>
      <Paper>
        <Grid container justify="center">
          <Grid item>
            <CarImage
              src={API_URL + "/u/car/download-car-image/" + car.licensePlate}
            />
          </Grid>
          <Grid item>
            <CarInfo car={car} />
          </Grid>
        </Grid>
        <Button onClick={handleCarChange}>Change</Button>
      </Paper>
    </Grid>
  );
};

export default ChangeCar;
