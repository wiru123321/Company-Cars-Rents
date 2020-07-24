import React from "react";
import { Grid, Button } from "@material-ui/core";

import { rentRequestStyles, TextArea } from "./rentRequest.styles.js";

const RentRequestControlPanel = () => {
  const classes = rentRequestStyles();
  return (
    <Grid
      container
      direction="col"
      justify="center"
      alignItems="center"
      className={classes.control}
    >
      <TextArea placeholder="Justification..." />
      <Grid
        container
        direction="row"
        justify="space-evenly"
        alignItems="center"
        className={classes.buttons}
      >
        <Button variant="contained" color="secondary">
          Decline
        </Button>
        <Button variant="contained" color="primary">
          Accept
        </Button>
      </Grid>
    </Grid>
  );
};

export default RentRequestControlPanel;
