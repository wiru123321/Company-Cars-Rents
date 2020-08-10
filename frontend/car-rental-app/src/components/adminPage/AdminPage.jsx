import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "../adminNavbar/AdminNavbar";
import AddNewCar from "./addNewCar/AddNewCar";
import RemoveCar from "./manageCars/removeCar/RemoveCar";
import AddEmployee from "./addEmployee/AddEmployee";
import RemoveEmployer from "./removeEmployer/RemoveEmployer";
import Parking from "./parking/Parking";
import RentRequests from "./rentRequests/RentRequests";

const AdminPage = () => {
  return (
    <div>
      <UserNavbar />
      <HashRouter basename="/adminPage">
        <Switch>
          <Route path="/" exact component={AddNewCar} />
          <Route path="/removeCar" component={RemoveCar} />
          <Route path="/addEmployee" component={AddEmployee} />
          <Route path="/removeEmployer" component={RemoveEmployer} />
          <Route path="/parking" component={Parking} />
          <Route path="/rentRequest" component={RentRequests} />
        </Switch>
      </HashRouter>
    </div>
  );
};

export default AdminPage;
