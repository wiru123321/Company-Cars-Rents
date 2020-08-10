import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import YourReservation from "./yourReservation/YourReservation";
import UserNavbar from "../userNavbar/UserNavbar";
import Reservation from "./reservation/Reservation";
import AccountSettings from "./accountSettings/AccountSettings";
import Footer from "../footer/Footer";

const UserPage = () => {
  return (
    <div>
      <UserNavbar />

      <HashRouter basename="/userPage">
        <Switch>
          <Route path="/yourReservation" component={YourReservation} />
          <Route path="/reservation" component={Reservation} />
          <Route path="/settings" component={AccountSettings} />
        </Switch>
      </HashRouter>
      <Footer />
    </div>
  );
};

export default UserPage;
