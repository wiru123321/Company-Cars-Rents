import React from "react";
import { List, ListItem, ListItemText } from "@material-ui/core";

const ParkingSlotInfo = ({ parking }) => {
  return (
    <>
      <List>
        <ListItem>
          <ListItemText primary={`Town: ${parking.town}`} />
        </ListItem>
        <ListItem>
          <ListItemText primary={`Postal code: ${parking.postalCode}`} />
        </ListItem>
        <ListItem>
          <ListItemText primary={`Street: ${parking.streetName}`} />
        </ListItem>
      </List>
    </>
  );
};

export default ParkingSlotInfo;
