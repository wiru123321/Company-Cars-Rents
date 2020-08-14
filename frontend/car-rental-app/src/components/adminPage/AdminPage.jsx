import React, { useEffect } from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import { useDispatch } from "react-redux";
import UserNavbar from "./adminNavbar/AdminNavbar";
import AddNewCar from "./manageCars/addNewCar/AddNewCar";
import CarsManager from "./manageCars/CarsManager";
import AddEmployee from "./manageEmployees/addEmployee/AddEmployee";
import RemoveEmployer from "./manageEmployees/removeEmployer/RemoveEmployer";
import RentRequests from "./rentRequests/RentRequests";
import Footer from "../footer/Footer";
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
          <Route path="/removeCar" component={CarsManager} />
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
