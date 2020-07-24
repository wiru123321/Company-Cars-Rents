import React from "react";
import {
  Grid,
  Paper,
  List,
  ListItem,
  ListItemText,
  Typography,
  Divider,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
  },
  paper: {
    padding: 8,
    marginTop: "2%",
    // height: 140,
    // width: 100,
  },
  control: {
    padding: theme.spacing(2),
  },
}));
const RequestInfo = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
}) => {
  const classes = useStyles();
  return (
    <List className={classes.root}>
      <ListItem>
        <ListItemText primary={`${firstname} ${lastname}`} />
      </ListItem>
      <Divider component="li" />
      <li>
        <Typography>Reservation start</Typography>
      </li>
      <ListItem>
        <ListItemText
          primary={`Date: ${beginDate}`}
          secondary={`Hour: ${beginHour}`}
        />
      </ListItem>
      <Divider component="li" />
      <li>
        <Typography>Reservation end</Typography>
      </li>
      <ListItem>
        <ListItemText
          primary={`Date: ${endDate}`}
          secondary={`Hour: ${endHour}`}
        />
      </ListItem>
    </List>
  );
};

export default RequestInfo;
