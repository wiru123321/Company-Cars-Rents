import React from "react";
import { Grid, makeStyles } from "@material-ui/core";
import FacebookIcon from "@material-ui/icons/Facebook";
import YouTubeIcon from "@material-ui/icons/YouTube";
import MailIcon from "@material-ui/icons/Mail";

const useStyles = makeStyles({
  root: {
    marginTop: "1%",
    textAlign: "center",
    height: "15vh",
    backgroundColor: "#0e153a",
  },
  content: {
    width: "30vw",
  },
  icon: {
    fontSize: "6vh",
  },
  textArea: {
    fontSize: "2.5vh",
    color: "#f3f169",
  },
});

const Footer = () => {
  const classes = useStyles();
  return (
    <Grid
      container
      className={classes.root}
      direction="column"
      justify="space-evenly"
      alignItems="center"
    >
      <Grid className={classes.content} container justify="space-evenly">
        <Grid item xs={2}>
          <a href="https://www.facebook.com/">
            <FacebookIcon className={classes.icon} />
          </a>
        </Grid>
        <Grid item xs={2}>
          <a href="https://www.youtube.com/">
            <YouTubeIcon className={classes.icon} />
          </a>
        </Grid>
        <Grid item xs={2}>
          <a href="#">
            <MailIcon className={classes.icon} />
          </a>
        </Grid>
      </Grid>
      <Grid item>
        <p className={classes.textArea}>2020 &copy; All rights reserved.</p>
      </Grid>
    </Grid>
  );
};

export default Footer;
