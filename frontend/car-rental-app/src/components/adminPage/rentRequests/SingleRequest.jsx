import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Container, Paper, Divider } from "@material-ui/core";
import {
  selectAll,
  acceptRentRequest,
  rejectRentRequest,
  fetchCarsBetweenDates,
} from "../../../features/rents/rentsSlice";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import ParkingInfo from "./rentRequestInfo/ParkingInfo";
import CarsModal from "../modal/CarsModal";

const SingleRequest = () => {
  const { currentRent } = useSelector(selectAll);
  const dispatch = useDispatch();
  const classes = rentRequestStyles();
  const [response, setResponse] = useState("");
  const [carLicensePlate, setCarLicensePlate] = useState(
    currentRent.carDTO.licensePlate
  );

  const handleResponseChange = (event) => {
    setResponse(event.target.value);
  };

  const handleAccept = () => {
    dispatch(
      acceptRentRequest(currentRent.id, {
        licensePlate: carLicensePlate,
        response: response,
      })
    );
  };

  const handleReject = () => {
    dispatch(
      rejectRentRequest(currentRent.id, {
        licensePlate: carLicensePlate,
        response: response,
      })
    );
  };

  const fetchAvailableCars = (setCars) => {
    let dateFromDateTo = {
      dateFrom: currentRent.dateFrom,
      dateTo: currentRent.dateTo,
    };
    dispatch(fetchCarsBetweenDates(dateFromDateTo, setCars));
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
        <CarsModal car={currentRent.carDTO} fetchCars={fetchAvailableCars} />
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

export default SingleRequest;
