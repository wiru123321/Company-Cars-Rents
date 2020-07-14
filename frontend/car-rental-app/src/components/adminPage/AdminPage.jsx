import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "../adminNavbar/AdminNavbar";
import AddNewCar from "./addNewCar/AddNewCar";
import RemoveCar from "./removeCar/removeCar";
import AddEmployer from "./addEmployer/AddEmployer";
import RemoveEmployer from "./removeEmployer/RemoveEmployer";
import Parking from "./parking/Parking";

const AdminPage = () => {
  return (
    <div>
      <UserNavbar />
      <div>Welcome on admin page!</div>
      <HashRouter basename="/adminPage">
        <Switch>
          <Route path="/addNewCar" component={AddNewCar} />
          <Route path="/removeCar" component={RemoveCar} />
          <Route path="/addEmployer" component={AddEmployer} />
          <Route path="/removeEmployer" component={RemoveEmployer} />
          <Route path="/parking" component={Parking} />
        </Switch>
      </HashRouter>
    </div>
  );
};

export default AdminPage;
