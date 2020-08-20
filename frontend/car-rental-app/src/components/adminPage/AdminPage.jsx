import React, { useEffect } from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import { useDispatch } from "react-redux";
import UserNavbar from "./adminNavbar/AdminNavbar";
import AddNewCar from "./manageCars/addNewCar/AddNewCar";
import CarsManager from "./manageCars/manager/CarsManager";
import AddEmployee from "./manageEmployees/addEmployee/AddEmployee";
import EmployeesManager from "./manageEmployees/manager/EmployeesManager";
import RentRequests from "./rentRequests/RentRequests";
import Footer from "../footer/Footer";
import ActiveRents from "./activeRents/rents/ActiveRents";
import PendingRents from "./pendingRents/PendingRents";
import { fetchPendingRents } from "../../features/rents/rentsSlice";

const AdminPage = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchPendingRents());
  }, []);

  return (
    <div style={{ height: "100%", backgroundColor: "#708090" }}>
      <UserNavbar />
      <HashRouter basename="/adminPage/">
        <Switch>
          <Route path="/" exact component={AddNewCar} />
          <Route path="/pendingRents" component={PendingRents} />
          <Route path="/activeRents" component={ActiveRents} />
          <Route path="/removeCar" component={CarsManager} />
          <Route path="/addEmployee" component={AddEmployee} />
          <Route path="/removeEmployer" component={EmployeesManager} />
          <Route path="/rentRequest" component={RentRequests} />
          <Route path="*" exact component={AddNewCar} />
        </Switch>
      </HashRouter>
      <Footer />
    </div>
  );
};

export default AdminPage;
