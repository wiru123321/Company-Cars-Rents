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

const RequestInfo = ({
  request: {
    firstname,
    lastname,
    beginDate,
    beginHour,
    endDate,
    endHour,
    description,
  },
}) => {
  return (
    <List>
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
      <li>
        <Typography>Reservation end</Typography>
      </li>
      <ListItem>
        <ListItemText
          primary={`Date: ${endDate}`}
          secondary={`Hour: ${endHour}`}
        />
      </ListItem>
      <Divider component="li" />
      <li>
        <Typography>Description: </Typography>
      </li>
      <ListItem>
        <ListItemText primary={description} />
      </ListItem>
    </List>
  );
};

export default RequestInfo;
