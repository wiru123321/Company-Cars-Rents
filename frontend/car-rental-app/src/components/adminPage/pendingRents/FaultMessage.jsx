import React from "react";
import { Grid, Paper, Typography } from "@material-ui/core";

const FaultMessage = ({ faultMessage }) => {
  if (faultMessage) {
    return (
      <Grid style={{ padding: "8px", marginTop: "1%" }}>
        <Typography
          style={{ fontSize: "1.4rem", textAlign: "center" }}
          color="secondary"
        >
          Reported issues
        </Typography>
        <Paper style={{ padding: "4px" }}>
          <Typography color="secondary">{faultMessage}</Typography>
        </Paper>
      </Grid>
    );
  } else {
    return <></>;
  }
};

export default FaultMessage;
