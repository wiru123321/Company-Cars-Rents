import React from "react";
import { useSelector } from "react-redux";
import { selectAll } from "../../../features/rents/rentsSlice";
import { Container } from "@material-ui/core";
import RentRequestListItem from "./rentRequestsListing/RentRequestListItem";
import NotFoundMessage from "../messages/NotFoundMessage";
import RentAlert from "./RentAlert";

const AllRequests = () => {
  const { pendingRents } = useSelector(selectAll);
  return (
    <Container style={{ minHeight: "80vh", height: "auto", height: "100%" }}>
      {pendingRents.length > 0 ? (
        pendingRents.map((rent, index) => (
          <RentRequestListItem key={index} rent={rent} index={index} />
        ))
      ) : (
        <NotFoundMessage>There are no pending requests.</NotFoundMessage>
      )}
      <RentAlert />
    </Container>
  );
};

export default AllRequests;
