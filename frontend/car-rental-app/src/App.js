import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "./components/userNavbar/UserNavbar";
import AdminNavbar from "./components/adminNavbar/AdminNavbar";
import Login from "./components/login/Login";

function App() {
  return (
    <div>
      <HashRouter basename="/">
        <Switch>
          <Route exact path="/" component={Login} />
          <Route path="/userPage" component={UserNavbar} />
          <Route path="/adminPage" component={AdminNavbar} />
        </Switch>
      </HashRouter>
    </div>
  );
}
export default App;
