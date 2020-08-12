import React from "react";
import { useSelector } from "react-redux";
import { selectAll } from "../../../features/rents/rentsSlice";
import { Container } from "@material-ui/core";
import RentRequestListItem from "./rentRequestsListing/RentRequestListItem";

const AllRequests = () => {
  const { pendingRents } = useSelector(selectAll);
  return (
    <Container style={{ minHeight: "80vh", height: "auto", height: "100%" }}>
      {pendingRents.map((rent, index) => {
        return <RentRequestListItem key={index} rent={rent} index={index} />;
      })}
    </Container>
  );
};

export default AllRequests;
