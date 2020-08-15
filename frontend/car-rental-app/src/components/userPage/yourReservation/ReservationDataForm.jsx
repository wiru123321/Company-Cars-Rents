import React from "react";
import { TextField, Grid, Box } from "@material-ui/core";

export const ParkingData = ({
  handletownChange,
  handlestreetNameChange,
  handlepostalCodeChange,
  handlenumberChange,
  handlecommentChange,
  town,
  streetName,
  postalCode,
  comment,
  number,
}) => {
  return (
    <Box
      display="flex"
      justifyContent="center"
      m={1}
      p={1}
      style={{ height: "10vh" }}
    >
      <Grid container direction="row" justify="space-between">
        <TextField
          onChange={(event) => handletownChange(event.target.value)}
          value={town}
          label="Town"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={(event) => handlestreetNameChange(event.target.value)}
          value={streetName}
          label="Street Name"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={(event) => handlepostalCodeChange(event.target.value)}
          value={postalCode}
          label="Postal Code"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={(event) => handlenumberChange(event.target.value)}
          value={number}
          label="Number"
          variant="outlined"
          margin="normal"
          required
        />
        <TextField
          onChange={(event) => handlecommentChange(event.target.value)}
          value={comment}
          label="Comment"
          variant="outlined"
          margin="normal"
          required
        />
      </Grid>
    </Box>
  );
};
