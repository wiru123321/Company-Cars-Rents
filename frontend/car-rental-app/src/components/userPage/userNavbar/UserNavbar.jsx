import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import { logout } from "../../../features/authentication/authSlice";
import { useDispatch } from "react-redux";
import "./UserNavbar.css";

const UserNavbar = () => {
  const dispatch = useDispatch();
  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-color-nav"
        variant="dark"
      >
        <Navbar.Brand
          href="#userPage/yourReservation"
          style={{ color: "#f3f169", fontSize: "25px" }}
        >
          <img
            src="https://www.euvic.pl/wp-content/uploads/2019/11/logo-euvic-it-1.png"
            width="140"
            height="60"
            className="d-inline-block align-top"
            alt="logo"
          />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link
              href="#userPage/yourReservation"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Your Cars
            </Nav.Link>
            <Nav.Link
              href="#userPage/reservation"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Reservation
            </Nav.Link>
            <Nav.Link
              href="#userPage/settings"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Settings
            </Nav.Link>
            <Nav.Link
              href="/login"
              onClick={() => dispatch(logout())}
              style={{ color: "red", fontSize: "25px" }}
            >
              Logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default UserNavbar;
