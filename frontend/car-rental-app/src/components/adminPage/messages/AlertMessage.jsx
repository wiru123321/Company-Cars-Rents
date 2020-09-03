import React from "react";
import { Alert, AlertTitle } from "@material-ui/lab";
import { makeStyles } from "@material-ui/core/styles";
import { Paper, Dialog } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  modal: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    boxShadow: theme.shadows[1],
    padding: "8px",
  },
}));

const AlertMessage = ({
  isActive,
  isOk,
  successMessage,
  errorMessage,
  resetUpdateState,
}) => {
  const classes = useStyles();

  if (isActive) {
    if (isOk) {
      return (
        <Dialog
          className={classes.modal}
          open={isActive}
          onClose={resetUpdateState}
        >
          <Paper className={classes.paper}>
            <Alert severity="success">
              <AlertTitle>{successMessage}</AlertTitle>
            </Alert>
          </Paper>
        </Dialog>
      );
    } else {
      return (
        <Dialog
          className={classes.modal}
          open={isActive}
          onClose={resetUpdateState}
        >
          <Paper className={classes.paper}>
            <Alert severity="error">
              <AlertTitle>{errorMessage}</AlertTitle>
            </Alert>
          </Paper>
        </Dialog>
      );
    }
  } else {
    return <></>;
  }
};

export default AlertMessage;
