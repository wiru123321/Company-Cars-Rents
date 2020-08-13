import React from "react";
import { ListItem, Grid, Paper, Button, Typography } from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import { useDispatch } from "react-redux";
import { deleteFault } from "../../../../../features/car-management/carManagerSlice";
import axios from "axios";
const API_URL = "http://localhost:8080";

const Issue = ({ fault }) => {
  const dispatch = useDispatch();

  const fetch = () => {
    axios(API_URL + "/a/fault", {
      headers: { Authorization: "Bearer " + localStorage.getItem("token") },
      method: "DELETE",
      data: { faultDTO: fault },
    })
      .then((response) => console.log(response.data))
      .catch((error) => console.log(error));
  };
  return (
    <Grid container alignItems="center" justify="space-between">
      <Typography color="secondary">{fault.description}</Typography>
      <Button onClick={() => fetch()} color="primary">
        fixed <CheckIcon />
      </Button>
    </Grid>
  );
};

export default Issue;
