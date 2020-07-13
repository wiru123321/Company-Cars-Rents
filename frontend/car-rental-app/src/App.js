import React from "react";
import Navbar from "./components/navbar/navbar";
import { Route, Switch, HashRouter } from "react-router-dom";
import Login from "./components/login/Login";

function App() {
  return (
    <div>
      <HashRouter basename="/">
        <Switch>
          <Route exact path="/" component={Login} />
          <Route path="/userPage" component={Navbar} />
        </Switch>
      </HashRouter>
    </div>
  );
}
export default App;
