import React from "react";
import Login from "./components/login/Login";
import {
  Container,
  ShadowContainer,
  ImageContainer,
} from "./styles/styles.style";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "./components/userNavbar/UserNavbar";
import AdminNavbar from "./components/adminNavbar/AdminNavbar";
    <ImageContainer img>
      <ShadowContainer shadow="0.4">
        <Container col height="100vh">
          <Login />
        </Container>
      </ShadowContainer>
    </ImageContainer>
  );
};

function App() {
  return (
    <HashRouter basename="/">
      <Switch>
        <Route exact path="/" component={LoginPage} />
        <Route path="/userPage" component={Navbar} />
          <Route path="/adminPage" component={AdminNavbar} />
      </Switch>
    </HashRouter>
  );
}
export default App;
