import React from "react";
import { Grid, Button } from "@material-ui/core";
import { rentRequestStyles, TextArea } from "./rentRequest.styles.js";

const RentRequestControlPanel = ({
  response,
  handleResponseChange,
  handleAccept,
  handleReject,
}) => {
  const classes = rentRequestStyles();

  return (
    <Grid
      container
      direction="column"
      justify="center"
      alignItems="center"
      className={classes.control}
    >
      <TextArea
        value={response}
        onChange={handleResponseChange}
        placeholder="Justification..."
        required
      />
      <Grid
        container
        direction="row"
        justify="space-evenly"
        alignItems="center"
        className={classes.buttons}
      >
        <Button onClick={handleReject} variant="contained" color="secondary">
          Decline
        </Button>
        <Button onClick={handleAccept} variant="contained" color="primary">
          Accept
        </Button>
      </Grid>
    </Grid>
  );
};

export default RentRequestControlPanel;
