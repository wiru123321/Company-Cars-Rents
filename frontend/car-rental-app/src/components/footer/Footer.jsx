import React from "react";
import { Typography, Grid } from "@material-ui/core";
import FacebookIcon from "@material-ui/icons/Facebook";
import YouTubeIcon from "@material-ui/icons/YouTube";
import MailIcon from "@material-ui/icons/Mail";

const Footer = () => {
  return (
    <Grid
      container
      style={{
        textAlign: "center",
        height: "15vh",
        backgroundColor: "#3f51b5",
      }}
      direction="column"
      justify="space-evenly"
      alignItems="center"
    >
      <Grid style={{ width: "30vw" }} container justify="space-evenly">
        <Grid item xs={2}>
          <a href="https://www.facebook.com/">
            <FacebookIcon style={{ fontSize: "6vh" }} />
          </a>
        </Grid>
        <Grid item xs={2}>
          <a href="https://www.youtube.com/">
            <YouTubeIcon style={{ fontSize: "6vh" }} />
          </a>
        </Grid>
        <Grid item xs={2}>
          <a href="#">
            <MailIcon style={{ fontSize: "6vh" }} />
          </a>
        </Grid>
      </Grid>
      <Grid item>
        <p style={{ fontSize: "2.5vh", color: "#f3f169" }}>
          2020 &copy; All rights reserved.
        </p>
      </Grid>
    </Grid>
  );
};

export default Footer;
