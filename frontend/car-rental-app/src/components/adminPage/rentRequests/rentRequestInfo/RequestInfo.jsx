import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Typography,
  Divider,
  Grid,
  makeStyles,
  Paper,
} from "@material-ui/core";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import EmailIcon from "@material-ui/icons/Email";
import PhoneIcon from "@material-ui/icons/Phone";

const useStyles = makeStyles({
  paper: {
    backgroundColor: "grey",
    color: "white",
    padding: "8px",
  },
  title: {
    fontSize: "2rem",
  },
  subTitle: {
    fontSize: "1.3rem",
  },
});

const RequestInfo = ({ rent }) => {
  const classes = useStyles();
  const { userRentInfo, dateFrom, dateTo, comment } = rent;
  return (
    <>
      <Grid container justify="center">
        <Grid item xs={4}>
          <UserDetails
            name={userRentInfo.name}
            surname={userRentInfo.surname}
            email={userRentInfo.email}
            phoneNumber={userRentInfo.phoneNumber}
          />
        </Grid>
        <Grid item xs={8}>
          <ReservationDetails dateFrom={dateFrom} dateTo={dateTo} />
        </Grid>
      </Grid>
      <Divider />
      <Grid>
        <Typography className={classes.subTitle}>Description: </Typography>
        <Paper className={classes.paper}>
          <Typography>{comment}</Typography>
        </Paper>
      </Grid>
      <Divider style={{ marginTop: "1%" }} />
    </>
  );
};

const UserDetails = ({ name, surname, email, phoneNumber }) => {
  const classes = useStyles();
  return (
    <Grid container direction="column" justify="space-evenly">
      <Typography className={classes.title}>
        <AccountCircleIcon /> {name} {surname}
      </Typography>
      <Typography className={classes.subTitle}>
        <EmailIcon />
        {email}
      </Typography>
      <Typography className={classes.subTitle}>
        <PhoneIcon />
        {phoneNumber}
      </Typography>
    </Grid>
  );
};

const ReservationDetails = ({ dateFrom, dateTo, comment }) => (
  <Grid container direction="row" justify="space-evenly" alignItems="center">
    <Divider orientation="vertical" flexItem />
    <Grid item xs={3}>
      <Typography>Reservation start</Typography>
      <ListItem>
        <ListItemText
          primary={`Date: ${dateFrom.slice(0, 10)}`}
          secondary={`Hour: ${dateFrom.slice(11, 19)}`}
        />
      </ListItem>
    </Grid>
    <Divider orientation="vertical" flexItem />
    <Grid item xs={3}>
      <Typography>Reservation end</Typography>
      <ListItem>
        <ListItemText
          primary={`Date: ${dateTo.slice(0, 10)}`}
          secondary={`Hour: ${dateTo.slice(11, 19)}`}
        />
      </ListItem>
    </Grid>
  </Grid>
);

export default RequestInfo;
