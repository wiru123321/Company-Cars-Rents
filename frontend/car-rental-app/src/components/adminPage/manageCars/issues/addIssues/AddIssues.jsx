import React from "react";
import { Grid } from "@material-ui/core";
import AddIssuesForm from "./AddIssuesForm";

const AddIssues = () => {
  const handlesubmit = (event) => {
    event.preventDefault();
  };
  return (
    <Grid container justify="center">
      <AddIssuesForm />
    </Grid>
  );
};

export default AddIssues;
