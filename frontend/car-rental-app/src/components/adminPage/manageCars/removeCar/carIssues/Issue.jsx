import React from "react";
import { Grid, Button, Typography } from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";

const Issue = ({ fault, handleDeleteFault, fetchFaults }) => {
  const onDelete = () => {
    handleDeleteFault(fault);
  };
  return (
    <Grid container alignItems="center" justify="space-between">
      <Typography color="secondary">{fault.description}</Typography>
      <Button onClick={onDelete} color="primary">
        fixed <CheckIcon />
      </Button>
    </Grid>
  );
};

export default Issue;
