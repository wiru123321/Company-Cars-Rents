import React from "react";
import { useSelector } from "react-redux";
import { selectRequests } from "../../../features/rent-requests/rentRequestsSlice";
import { Container } from "@material-ui/core";
import RentRequestListItem from "./rentRequestsListing/RentRequestListItem";

const AllRequests = () => {
  const requests = useSelector(selectRequests);
  return (
    <Container>
      {requests.map((request, index) => {
        return (
          <RentRequestListItem key={index} request={request} index={index} />
        );
      })}
    </Container>
  );
};

export default AllRequests;
