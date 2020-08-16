import React from "react";
import LoginPage from "./components/login/LoginPage";
import UserPage from "./components/userPage/UserPage";
import AdminPage from "./components/adminPage/AdminPage";
import {
  Route,
  Switch,
  BrowserRouter as Router,
  Redirect,
} from "react-router-dom";

function PrivateRoute({ component: Component, role, ...rest }) {
  return (
    <Route
      {...rest}
      render={() =>
        localStorage.getItem("role") === role ? (
          <Component {...rest} />
        ) : (
          <Redirect to="/login" />
        )
      }
    />
  );
}

function App() {
  return (
    <Router basename="/">
      <Switch>
        <Route exact path="/login" component={LoginPage} />
        <PrivateRoute path="/adminPage" role="ADMIN" component={AdminPage} />
        <PrivateRoute path="/userPage" role="EMPLOYEE" component={UserPage} />
      </Switch>
    </Router>
  );
}
export default App;
