import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  firstnameChange,
  lastnameChange,
  loginChange,
  passwordChange,
  rePasswordChange,
  selectAll,
} from "../../../features/car-reservation/addEmployeeSlice";
import { TextField, Container, Grid, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "60ch",
    },
  },
}));
const AddEmployee = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { firstname, lastname, login, password, rePassword } = useSelector(
    selectAll
  );
  return (
    <form>
      <Grid
        className={classes.root}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <TextField
          onChange={(event) => dispatch(firstnameChange(event.target.value))}
          label="firstname"
          variant="filled"
          required
        />
        <TextField
          onChange={(event) => dispatch(lastnameChange(event.target.value))}
          label="lastname"
          variant="filled"
          required
        />
        <TextField
          onChange={(event) => dispatch(loginChange(event.target.value))}
          label="login"
          variant="filled"
          required
        />
        <TextField
          onChange={(event) => dispatch(passwordChange(event.target.value))}
          label="password"
          variant="filled"
          type="password"
          required
        />
        <TextField
          onChange={(event) => dispatch(rePasswordChange(event.target.value))}
          label="repeat password"
          variant="filled"
          type="password"
          required
        />
        <Button variant="contained" type="submit">
          Create account
        </Button>
      </Grid>
    </form>
  );
};

export default AddEmployee;
