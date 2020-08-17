import React, { useState } from "react";
import {
  Container,
  Paper,
  Divider,
  Button,
  Grid,
  Dialog,
  makeStyles,
} from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import {
  selectAll,
  acceptRentRequest,
  rejectRentRequest,
  fetchCarsBetweenDates,
  changeRentCar,
  getCar,
} from "../../../features/rents/rentsSlice";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
import ParkingInfo from "./rentRequestInfo/ParkingInfo";
import RequestedCarInfo from "./rentRequestInfo/RequestedCarInfo";
import CarImage from "../../carsListing/CarImage";
import CarInfo from "../../carsListing/CarInfo";

const SingleRequest = () => {
  const { currentRent } = useSelector(selectAll);
  const dispatch = useDispatch();
  const classes = rentRequestStyles();
  const [response, setResponse] = useState("");

  const handleResponseChange = (event) => {
    setResponse(event.target.value);
  };

  const handleAccept = () => {
    dispatch(
      acceptRentRequest(currentRent.id, {
        licensePlate: currentRent.carDTO.licensePlate,
        response: response,
      })
    );
  };

  const handleReject = () => {
    dispatch(
      rejectRentRequest(currentRent.id, {
        licensePlate: currentRent.carDTO.licensePlate,
        response: response,
      })
    );
  };

  return (
    <Container
      style={{
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
      <Paper className={classes.paper}>
        <RequestInfo rent={currentRent} />
        <ParkingInfo
          parkingFrom={currentRent.parkingFrom}
          parkingTo={currentRent.parkingTo}
        />
        <Divider />

        <RequestedCarInfo carDTO={currentRent.carDTO} />
        <ChangeCar
          id={currentRent.id}
          dateFrom={currentRent.dateFrom}
          dateTo={currentRent.dateTo}
        />
        <RentRequestControlPanel
          response={response}
          handleResponseChange={handleResponseChange}
          handleAccept={handleAccept}
          handleReject={handleReject}
        />
      </Paper>
    </Container>
  );
};

const ChangeCar = ({ id, dateFrom, dateTo }) => {
  const [open, setOpen] = useState(false);
  const [cars, setCars] = useState([]);

  const dispatch = useDispatch();

  const handleOpen = () => {
    setOpen(true);
    fetchCars();
  };

  const handleClose = () => {
    setOpen(false);
  };

  const fetchCars = () => {
    let dateFromDateTo = {
      dateFrom,
      dateTo,
    };
    dispatch(fetchCarsBetweenDates(dateFromDateTo, setCars));
  };

  return (
    <div>
      <Button onClick={handleOpen} style={{ width: "100%" }} variant="outlined">
        ChangeCar
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <ChangeCarModalMenu cars={cars} id={id} handleClose={handleClose} />
      </Dialog>
    </div>
  );
};

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    minHeight: "60vh",
    minWidth: "60vh",
  },
  item: {
    marginTop: "1%",
    cursor: "pointer",
  },
});

const ChangeCarModalMenu = ({ id, cars, handleClose }) => {
  const classes = useStyles();
  const [currentCarLicensePlate, setCurrentCar] = useState("");
  const dispatch = useDispatch();
  const handleCarChange = (car) => {
    setCurrentCar(car.licensePlate);
    console.log(car);
  };

  return (
    <Paper className={classes.paper}>
      <Grid>
        {cars.map((car, index) => {
          return (
            <Paper
              onClick={() => handleCarChange(car)}
              className={classes.item}
            >
              <Grid container justify="space-evenly" alignItems="center">
                <Grid item>
                  <CarImage
                    src={
                      "http://localhost:8080/u/car/download-car-image/" +
                      car.licensePlate
                    }
                  />
                </Grid>
                <Grid item>
                  <CarInfo car={car} />
                </Grid>
              </Grid>
            </Paper>
          );
        })}
        <Button
          onClick={() => {
            if (currentCarLicensePlate) {
              dispatch(changeRentCar(id, currentCarLicensePlate));
              dispatch(getCar(id));
              handleClose();
            }
          }}
        >
          Apply
        </Button>
      </Grid>
    </Paper>
  );
};

export default SingleRequest;
