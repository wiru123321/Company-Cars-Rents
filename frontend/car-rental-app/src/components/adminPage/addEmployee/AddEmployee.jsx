import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  toggleDidSubmit,
  selectAll,
  reset,
} from "../../../features/car-reservation/addEmployeeSlice";
import { Grid } from "@material-ui/core";
import FormControlPanel from "./FormControlPanel";
import UsersLogin from "./UsersLogin";
import UsersPassword from "./UsersPassword";
import UsersPersonalData from "./UsersPersonalData";
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
  const [showSuccess, toggleShowSuccess] = useState(false);
  const [success, isSuccess] = useState(false);
  const { password, rePassword } = useSelector(selectAll);

  useEffect(() => {
    const timer = setTimeout(() => {
      toggleShowSuccess(false);
      isSuccess(false);
    }, 2000);
    return () => {
      if (showSuccess == true) return clearTimeout(timer);
    };
  }, [success, showSuccess]);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();

        if (password === rePassword) {
          dispatch(reset());
          isSuccess(true);
          toggleShowSuccess(true);
        }
        dispatch(toggleDidSubmit(true));
      }}
    >
      <Grid
        className={classes.root}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <UsersPersonalData />
        <UsersLogin />
        <UsersPassword />
        <FormControlPanel success={success} showSuccess={showSuccess} />
      </Grid>
    </form>
  );
};

export default AddEmployee;
