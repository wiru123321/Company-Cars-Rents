import React from "react";
import LoginPage from "./components/login/Login";
import UserNavbar from "./components/userNavbar/UserNavbar";
import AdminNavbar from "./components/adminNavbar/AdminNavbar";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserPage from "./components/UserPage";

function App() {
  return (
    <HashRouter basename="/">
      <Switch>
        <Route exact path="/" component={LoginPage} />
        <Route path="/userPage" component={UserPage} />
        <Route path="/adminPage" component={AdminNavbar} />
      </Switch>
    </HashRouter>
  );
}
export default App;
