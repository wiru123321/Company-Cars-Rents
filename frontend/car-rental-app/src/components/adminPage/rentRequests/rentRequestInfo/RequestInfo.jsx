import React from "react";
import {
  List,
  ListItem,
  ListItemText,
  Typography,
  Divider,
  Grid,
} from "@material-ui/core";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import EmailIcon from "@material-ui/icons/Email";
import PhoneIcon from "@material-ui/icons/Phone";
const RequestInfo = ({ rent }) => {
  const { userRentInfo, dateFrom, dateTo, comment } = rent;
  return (
    <List>
      <UserDetails
        name={userRentInfo.name}
        surname={userRentInfo.surname}
        email={userRentInfo.email}
        phoneNumber={userRentInfo.phoneNumber}
      />
      <Divider />
      <ReservationDetails
        dateFrom={dateFrom}
        dateTo={dateTo}
        comment={comment}
      />
      <Divider />
    </List>
  );
};

const UserDetails = ({ name, surname, email, phoneNumber }) => (
  <ListItem>
    <Grid container justify="space-evenly">
      <Typography>
        <AccountCircleIcon /> {name} {surname}
      </Typography>
      <Typography>
        <EmailIcon />
        {email}
      </Typography>
      <Typography>
        <PhoneIcon />
        {phoneNumber}
      </Typography>
    </Grid>
  </ListItem>
);

const ReservationDetails = ({ dateFrom, dateTo, comment }) => (
  <Grid container direction="row" justify="space-evenly" alignItems="center">
    <Grid item xs={3}>
      <li>
        <Typography>Reservation start</Typography>
      </li>
      <ListItem>
        <ListItemText
          primary={`Date: ${dateFrom.slice(0, 10)}`}
          secondary={`Hour: ${dateFrom.slice(11, 19)}`}
        />
      </ListItem>
    </Grid>
    <Divider orientation="vertical" flexItem />
    <Grid item xs={3}>
      <li>
        <Typography>Reservation end</Typography>
      </li>
      <ListItem>
        <ListItemText
          primary={`Date: ${dateTo.slice(0, 10)}`}
          secondary={`Hour: ${dateTo.slice(11, 19)}`}
        />
      </ListItem>
    </Grid>
    <Divider orientation="vertical" flexItem />
    <Grid item xs={3}>
      <li>
        <Typography>Description: </Typography>
      </li>
      <ListItem>
        <ListItemText primary={comment} />
      </ListItem>
    </Grid>
  </Grid>
);

export default RequestInfo;
