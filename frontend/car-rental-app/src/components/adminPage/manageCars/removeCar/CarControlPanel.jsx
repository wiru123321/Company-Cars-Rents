import React from "react";
import { Grid, Button } from "@material-ui/core";
import DeleteIcon from "@material-ui/icons/Delete";
import EditIcon from "@material-ui/icons/Edit";

const CarControlPanel = ({
  index,
  onDelete,
  toggleEdit,
  isActive,
  faults,
  toggleShowIssues,
}) => {
  return (
    <Grid container direction="row" justify="space-evenly" alignItems="center">
      <Button
        onClick={toggleShowIssues}
        variant="contained"
        color="secondary"
        disabled={faults.length > 0 ? false : true}
        startIcon={<EditIcon />}
      >
        {faults.length > 0 ? "Issues " + faults.length : "No issues"}
      </Button>
      <Button
        onClick={toggleEdit}
        variant="contained"
        color="primary"
        startIcon={<EditIcon />}
      >
        Edit
      </Button>
      <Button
        onClick={() => {
          onDelete(index);
        }}
        variant="contained"
        color="secondary"
        startIcon={<DeleteIcon />}
      >
        Remove
      </Button>
    </Grid>
  );
};

export default CarControlPanel;
