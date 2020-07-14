import React from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
import "./navbar.css";

const navbar = () => {
  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-color-nav"
        variant="dark"
      >
        <Navbar.Brand href="#home" style={{ color: "#f3f169" }}>
          Company Name
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Your Cars </span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#action/1.1">
                Show all yours cars
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/1.2">
                Cancel reservation
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/1.3">
                Report a bug
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Search Cars </span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#action/2.1">
                Show all cars
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/2.2">
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

export default navbar;
