import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "./adminNavbar/AdminNavbar";
import AddNewCar from "./manageCars/addNewCar/AddNewCar";
import RemoveCar from "./manageCars/removeCar/RemoveCar";
import AddEmployee from "./addEmployee/AddEmployee";
import RemoveEmployer from "./removeEmployer/RemoveEmployer";
import RentRequests from "./rentRequests/RentRequests";
import Footer from "../footer/Footer";

const AdminPage = () => {
  return (
    <div style={{ height: "100vh", position: "relative" }}>
      <UserNavbar />
      <HashRouter basename="/adminPage">
        <Switch>
          <Route path="/" exact component={AddNewCar} />
          <Route path="/removeCar" component={RemoveCar} />
          <Route path="/addEmployee" component={AddEmployee} />
          <Route path="/removeEmployer" component={RemoveEmployer} />
          <Route path="/rentRequest" component={RentRequests} />
        </Switch>
      </HashRouter>
      <Footer />
    </div>
  );
};

export default AdminPage;
