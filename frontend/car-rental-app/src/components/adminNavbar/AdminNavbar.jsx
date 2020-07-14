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
        <Navbar.Brand href="/" style={{ color: "#f3f169" }}>
          Company Name or logo
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Cars</span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="/">Add new car</NavDropdown.Item>
              <NavDropdown.Item href="/">Remove car</NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <NavDropdown
              title={<span style={{ color: "#f3f169" }}>Employer</span>}
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item href="/">Add Employer</NavDropdown.Item>
              <NavDropdown.Item href="/">Remove Employer</NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <Nav.Link href="/" style={{ color: "#f3f169" }}>
              Parking
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default AdminNavbar;
