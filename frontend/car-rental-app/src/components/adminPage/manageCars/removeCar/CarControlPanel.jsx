import React from "react";
import { Grid, Button } from "@material-ui/core";
import DeleteIcon from "@material-ui/icons/Delete";
import EditIcon from "@material-ui/icons/Edit";

const CarControlPanel = ({ index, onDelete, toggleEdit, isActive }) => {
  return (
    <Grid container direction="row" justify="space-evenly" alignItems="center">
      {!isActive && (
        <Button
          onClick={toggleEdit}
          variant="contained"
          color="primary"
          startIcon={<EditIcon />}
        >
          Reset
        </Button>
      )}
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
