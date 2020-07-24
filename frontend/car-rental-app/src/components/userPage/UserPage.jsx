import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import ShowCars from "./showCars/ShowCars";
import YourReservation from "./yourReservation/YourReservation";
import ReportBug from "./reportBug/ReportBug";
import UserNavbar from "../userNavbar/UserNavbar";
import ShowAllCars from "./showAllCars/ShowAllCars";
import Reservation from "./reservation/Reservation";

const UserPage = () => {
  return (
    <div>
      <UserNavbar />
      <HashRouter basename="/userPage">
        <Switch>
          <Route path="/showAllCars" component={ShowCars} />
          <Route path="/yourReservation" component={YourReservation} />
          <Route path="/reportBug" component={ReportBug} />
          <Route path="/showAllCars" component={ShowAllCars} />
          <Route path="/reservation" component={Reservation} />
        </Switch>
      </HashRouter>
    </div>
  );
};

export default UserPage;
