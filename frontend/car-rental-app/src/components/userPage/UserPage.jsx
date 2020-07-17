import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import ShowCars from "./showCars/ShowCars";
import CancelReservation from "./cancelReservation/CancelReservation";
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
          <Route path="/" exact component={ShowCars} />
          <Route path="/cancelReservation" component={CancelReservation} />
          <Route path="/reportBug" component={ReportBug} />
          <Route path="/showAllCars" component={ShowAllCars} />
          <Route path="/reservation" component={Reservation} />
        </Switch>
      </HashRouter>
    </div>
  );
};

export default UserPage;
