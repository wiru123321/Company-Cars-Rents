import React from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
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
        <Navbar.Brand href="#adminPage" style={{ color: "#f3f169" }}>
          Company Name or logo
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <NavDropdown
              title={
                <span style={{ color: "#f3f169", fontSize: "20px" }}>Cars</span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#adminPage/addNewCar">
                Add new car
              </NavDropdown.Item>
              <NavDropdown.Item href="#adminPage/removeCar">
                Remove car
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <NavDropdown
              title={
                <span style={{ color: "#f3f169", fontSize: "20px" }}>
                  Employer
                </span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="#adminPage/addEmployer">
                Add Employer
              </NavDropdown.Item>
              <NavDropdown.Item href="#adminPage/removeEmployer">
                Remove Employer
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <Nav.Link
              href="#adminPage/parking"
              style={{ color: "#f3f169", fontSize: "20px" }}
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
