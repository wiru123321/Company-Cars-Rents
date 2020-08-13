import React from "react";
import { Container, Paper, Divider } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import {
  selectAll,
  setResponse,
  acceptRentRequest,
  rejectRentRequest,
} from "../../../features/rents/rentsSlice";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
import ParkingInfo from "./rentRequestInfo/ParkingInfo";
import RequestedCarInfo from "./rentRequestInfo/RequestedCarInfo";

const SingleRequest = () => {
  const { currentRent, response } = useSelector(selectAll);
  const dispatch = useDispatch();
  const classes = rentRequestStyles();

  const handleResponseChange = (event) => {
    dispatch(setResponse(event.target.value));
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
      acceptRentRequest(currentRent.id, {
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
