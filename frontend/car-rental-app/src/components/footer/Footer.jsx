import React from "react";
import { Typography, Grid } from "@material-ui/core";
import styled from "styled-components";
import FacebookIcon from "@material-ui/icons/Facebook";
import YouTubeIcon from "@material-ui/icons/YouTube";
import MailIcon from "@material-ui/icons/Mail";

const FooterContainer = styled.div`
  text-align: center;
  width: 100%;
  height: 15vh;
  background: #3f51b5;
`;

const Footer = () => {
  return (
    <FooterContainer>
      <Grid container direction="column">
        <Grid container item xs={12} spacing={1} justify="center">
          <Grid item xs={1}>
            <a href="https://www.facebook.com/">
              <FacebookIcon style={{ fontSize: "6vh" }} />
            </a>
          </Grid>
          <Grid item xs={1}>
            <a href="https://www.youtube.com/">
              <YouTubeIcon style={{ fontSize: "6vh" }} />
            </a>
          </Grid>
          <Grid item xs={1}>
            <a href="#">
              <MailIcon style={{ fontSize: "6vh" }} />
            </a>
          </Grid>
        </Grid>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <p style={{ fontSize: "2.5vh" }}>
              2020 &copy; All rights reserved.
            </p>
          </Grid>
        </Grid>
      </Grid>
    </FooterContainer>
  );
};

export default Footer;
