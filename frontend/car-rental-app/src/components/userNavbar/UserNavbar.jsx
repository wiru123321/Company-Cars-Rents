import React from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
import "./UserNavbar.css";

const UserNavbar = () => {
  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-color-nav"
        variant="dark"
      >
        <Navbar.Brand
          href="#userPage"
          style={{ color: "#f3f169", fontSize: "25px" }}
        >
          Company Name or logo
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
            <NavDropdown
              title={
                <span
                  style={{
                    color: "#f3f169",
                    fontSize: "25px",
                    marginRight: "15px",
                  }}
                >
                  Search Cars
                </span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#userPage/showAllCars">
                Show all cars
              </NavDropdown.Item>
              <NavDropdown.Item href="#userPage/reservation">
                Reservation
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default UserNavbar;
