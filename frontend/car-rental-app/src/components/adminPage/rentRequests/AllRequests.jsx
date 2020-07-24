import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  chooseRequest,
  selectChoosenRequestIndex,
  selectRequests,
  selectCurrentRequest,
} from "../../../features/rent-requests/rentRequestsSlice";
import { Container } from "@material-ui/core";
import RentRequestListItem from "./rentRequestsListing/RentRequestListItem";

const AllRequests = () => {
  const requests = useSelector(selectRequests);
  return (
    <Container>
      {requests.map((request, index) => {
        const { firstname, lastname } = request;
        return (
          <RentRequestListItem key={index} request={request} index={index} />
        );
      })}
    </Container>
  );
};

export default AllRequests;
