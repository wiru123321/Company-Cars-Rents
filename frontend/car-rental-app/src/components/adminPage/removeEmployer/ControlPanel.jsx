import React from "react";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import { Container, Button, Divider } from "@material-ui/core";

const ControlPanel = ({ setEdit, handleDelete, edit }) => {
  return (
    <Container>
      <Button
        variant="contained"
        color="primary"
        startIcon={<EditIcon />}
        onClick={(event) => setEdit(!edit)}
      >
        Edit
      </Button>
      <Button
        variant="contained"
        color="secondary"
        startIcon={<DeleteIcon />}
        style={{ marginLeft: "2vw" }}
        onClick={handleDelete}
      >
        Remove
      </Button>
    </Container>
  );
};

export default ControlPanel;
