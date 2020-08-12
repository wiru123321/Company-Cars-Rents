import React from "react";
import { Container, Paper, Divider } from "@material-ui/core";
import { useSelector } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import { selectAll } from "../../../features/rents/rentsSlice";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
import ParkingInfo from "./rentRequestInfo/ParkingInfo";
import RequestedCarInfo from "./rentRequestInfo/RequestedCarInfo";

const SingleRequest = () => {
  const { currentRent } = useSelector(selectAll);
  const classes = rentRequestStyles();
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
        <RentRequestControlPanel />
      </Paper>
    </Container>
  );
};

export default SingleRequest;
