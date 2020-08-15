import React from "react";
import { Nav } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { chooseRequest } from "../../../features/rents/rentsSlice";

const RequestsNavLink = () => {
  const linkPath = "#/adminPage/rentRequest";
  const dispatch = useDispatch();

  function setActiveRequestToNone() {
    dispatch(chooseRequest(""));
  }

  return (
    <Nav.Link
      onClick={setActiveRequestToNone}
      href={linkPath}
      style={{ color: "#f3f169", fontSize: "25px" }}
    >
      Rent requests
    </Nav.Link>
  );
};

export default RequestsNavLink;
