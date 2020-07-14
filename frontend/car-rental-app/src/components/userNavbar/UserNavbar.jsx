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
        <Navbar.Brand href="/" style={{ color: "#f3f169" }}>
          Company Name or logo
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Your Cars </span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="/">Show all yours cars</NavDropdown.Item>
              <NavDropdown.Item href="/">Cancel reservation</NavDropdown.Item>
              <NavDropdown.Item href="/">Report a bug</NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Search Cars </span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="/">Show all cars</NavDropdown.Item>
              <NavDropdown.Item href="/">Reservation</NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default UserNavbar;
