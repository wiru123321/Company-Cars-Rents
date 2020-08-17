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
} from "../../../features/rents/rentsSlice";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
import ParkingInfo from "./rentRequestInfo/ParkingInfo";
import RequestedCarInfo from "./rentRequestInfo/RequestedCarInfo";

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

const ChangeCar = ({ dateFrom, dateTo }) => {
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
    dispatch(fetchCarsBetweenDates(dateFromDateTo));
  };

  return (
    <div>
      <Button onClick={handleOpen} style={{ width: "100%" }} variant="outlined">
        ChangeCar
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <ChangeCarModalMenu />
      </Dialog>
    </div>
  );
};

const useStyles = makeStyles({
  paper: {
    minHeight: "40vh",
    minWidth: "50vh",
  },
});

const ChangeCarModalMenu = () => {
  const classes = useStyles();
  return (
    <Paper className={classes.paper}>
      <Button>Apply</Button>
    </Paper>
  );
};

export default SingleRequest;
