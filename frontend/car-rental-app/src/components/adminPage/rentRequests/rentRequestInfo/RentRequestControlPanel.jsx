import React from "react";
import { Grid, Button } from "@material-ui/core";
import styled from "styled-components";
import { rentRequestStyles } from "./rentRequest.styles";
const TextArea = styled.textarea`
  width: 100%;
  height: 10vh;
  resize: none;
  padding: 8px;
`;

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
