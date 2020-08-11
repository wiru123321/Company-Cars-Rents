import React from "react";
import { useSelector } from "react-redux";
import { selectAll } from "../../../../features/add-employees/addEmployeeSlice";
import { Grid, Button } from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import useStyles from "./useStyles";

const FormControlPanel = ({ success, showSuccess }) => {
  const classes = useStyles();

  const { password, rePassword, didSubmit } = useSelector(selectAll);
  return (
    <Grid
      container
      direction="column"
      justify="center"
      alignItems="center"
      className={classes.buttonArea}
    >
      <Button variant="contained" type="submit">
        Create account
      </Button>
      {didSubmit && password !== rePassword && (
        <Alert severity="error">Passwords are not matching</Alert>
      )}
      {showSuccess && success && (
        <Alert severity="success">User was succesfully registered!</Alert>
      )}
      {showSuccess && !success && (
        <Alert severity="error">Failed to register!</Alert>
      )}
    </Grid>
  );
};
export default FormControlPanel;
