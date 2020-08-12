import React, { useEffect } from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import UserNavbar from "./adminNavbar/AdminNavbar";
import AddNewCar from "./manageCars/addNewCar/AddNewCar";
import RemoveCar from "./manageCars/removeCar/RemoveCar";
import AddEmployee from "./manageEmployees/addEmployee/AddEmployee";
import RemoveEmployer from "./manageEmployees/removeEmployer/RemoveEmployer";
import RentRequests from "./rentRequests/RentRequests";
import Footer from "../footer/Footer";

import { useDispatch } from "react-redux";
import { fetchPendingRents } from "../../features/rents/rentsSlice";

const AdminPage = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchPendingRents());
  }, []);
  return (
    <div style={{ height: "100%" }}>
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
