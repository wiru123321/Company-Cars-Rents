import React from "react";
import LoginPage from "./components/login/Login";
import UserPage from "./components/userPage/UserPage";
import AdminPage from "./components/adminPage/AdminPage";

import { Route, Switch, HashRouter } from "react-router-dom";

function App() {
  return (
    <HashRouter basename="/">
      <Switch>
        <Route exact path="/" component={LoginPage} />
        <Route path="/userPage" component={UserPage} />
        <Route path="/adminPage" component={AdminPage} />
      </Switch>
    </HashRouter>
  );
}
export default App;
