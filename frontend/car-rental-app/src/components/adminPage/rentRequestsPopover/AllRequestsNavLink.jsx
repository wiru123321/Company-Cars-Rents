import React from "react";
import { Popover, Button, Overlay, Badge, Nav } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import {
  selectRequests,
  chooseRequest,
} from "../../../features/rent-requests/rentRequestsSlice";

const AllRequestsNavLink = () => {
  const dispatch = useDispatch();
  return (
    <Nav.Link
      onClick={(event) => {
        dispatch(chooseRequest(""));
      }}
      href="#/adminPage/rentRequest"
      style={{ color: "#f3f169", fontSize: "25px" }}
    >
      Rent Requests
    </Nav.Link>
  );
};

export default AllRequestsNavLink;
