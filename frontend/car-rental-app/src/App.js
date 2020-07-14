import React from "react";
import LoginPage from "./components/login/Login";
import UserNavbar from "./components/userNavbar/UserNavbar";
import AdminNavbar from "./components/adminNavbar/AdminNavbar";

import { Route, Switch, HashRouter } from "react-router-dom";

function App() {
  return (
    <HashRouter basename="/">
      <Switch>
        <Route exact path="/" component={LoginPage} />
        <Route path="/userPage" component={UserNavbar} />
        <Route path="/adminPage" component={AdminNavbar} />
      </Switch>
    </HashRouter>
  );
}
export default App;
