import React, { useState } from "react";
import RequestsPopover from "../rentRequestsPopover/RequestsPopover";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
import "../../userPage/userNavbar/UserNavbar.css";
import RequestsNavLink from "../rentRequestsPopover/RequestsNavLink";
import { logout } from "../../../features/authentication/authSlice";
import { useDispatch } from "react-redux";
import {
  setCurrentRent,
  setMenuMode,
} from "../../../features/rents/activeRentsSlice";

const BrandImage = () => (
  <Navbar.Brand href="#adminPage">
    <img
      src="https://www.euvic.pl/wp-content/uploads/2019/11/logo-euvic-it-1.png"
      width="140"
      height="60"
      className="d-inline-block align-top"
      alt="logo"
    />
  </Navbar.Brand>
);

const AdminNavbar = () => {
  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logout());
  };

  const handleActiveRentsSelect = () => {
    dispatch(setCurrentRent(""));
    dispatch(setMenuMode(false));
  };

  const handlePendingRentsSelect = () => {
    dispatch(setCurrentRent(""));
    dispatch(setMenuMode(false));
  };
  return (
    <div>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-color-nav"
        variant="dark"
      >
        <BrandImage />
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link
              title="Finished rents waiting for accept"
              onClick={handlePendingRentsSelect}
              href="#/adminPage/pendingRents"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Pending rents
            </Nav.Link>
            <Nav.Link
              title="Current rents"
              onClick={handleActiveRentsSelect}
              href="#/adminPage/activeRents"
              style={{ color: "#f3f169", fontSize: "25px" }}
            >
              Active rents
            </Nav.Link>
            <RequestsNavLink />
            <RequestsPopover />
            <NavDropdown
              title={
                <span style={{ color: "#f3f169", fontSize: "25px" }}>Cars</span>
              }
              id="collasible-nav-dropdown"
            >
              <NavDropdown.Item title="Add new car." href="#adminPage">
                Add car
              </NavDropdown.Item>
              <NavDropdown.Item
                title="Remove, update or manage cars."
                href="#adminPage/removeCar"
              >
                Manage car
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
              <NavDropdown.Item
                title="Add new employee."
                href="#adminPage/addEmployee"
              >
                Add employee
              </NavDropdown.Item>
              <NavDropdown.Item
                title="Remove, update or check employees."
                href="#adminPage/removeEmployer"
              >
                Manage employees
              </NavDropdown.Item>
              <NavDropdown.Divider />
            </NavDropdown>
            <Nav.Link
              title="Logout"
              href="/login"
              onClick={handleLogout}
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

export default AdminNavbar;
