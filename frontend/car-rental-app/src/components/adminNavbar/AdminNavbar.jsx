import React from "react";
import RentRequestsPopover from "./RentRequestsPopover";
import { Navbar, Nav, NavDropdown, Badge, Button } from "react-bootstrap";
import "../userNavbar/UserNavbar.css";

const AdminNavbar = () => {
  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-color-nav"
        variant="dark"
      >
        <Navbar.Brand
          href="#adminPage"
          style={{ color: "#f3f169", fontSize: "25px" }}
        >
          Company Name or logo
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Item>
              <RentRequestsPopover />
            </Nav.Item>
            <NavDropdown
              title={
                <span style={{ color: "#f3f169", fontSize: "25px" }}>Cars</span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#adminPage">Add new car</NavDropdown.Item>
              <NavDropdown.Item href="#adminPage/removeCar">
                Remove car
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <NavDropdown
              title={
                <span style={{ color: "#f3f169", fontSize: "25px" }}>
                  Employer
                </span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#adminPage/addEmployee">
                Add Employer
              </NavDropdown.Item>
              <NavDropdown.Item href="#adminPage/removeEmployer">
                Remove Employer
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <Nav.Link
              href="#adminPage/parking"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Parking
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default AdminNavbar;
