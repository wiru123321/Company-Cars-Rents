import React from "react";
import Login from "./components/login/Login";
import { Route, Switch, HashRouter } from "react-router-dom";
import Login from "./components/login/Login";

function App() {
  return (
    <Container bgr="#22d1ee" col height="100vh">
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
