import React, { useState } from "react";
import { Grid, Paper, Button, Dialog, makeStyles } from "@material-ui/core";
import ReservationInfo from "../activeRents/cards/ReservationInfo";
import AddFaultDialog from "./AddFaultDialog";
import FaultMessage from "./FaultMessage";

const useStyles = makeStyles({
  root: {
    marginTop: "1%",
    minWidth: "30vw",
    padding: "4px",
  },
  navPanel: {
    padding: "4px",
    backgroundColor: "#DCDCDC",
  },
});

const PendingRentItem = ({ rent, handleAccept }) => {
  const classes = useStyles();
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const onAccept = () => {
    handleAccept(rent.id);
  };

  return (
    <Paper className={classes.root}>
      <ReservationInfo
        user={rent.userRentInfo}
        dateFrom={rent.dateFrom}
        dateTo={rent.dateTo}
        parkingFrom={rent.parkingFrom}
        parkingTo={rent.parkingTo}
        car={rent.carDTO}
        adminResponseForTheRequest={rent.adminResponseForTheRequest}
      />
      <FaultMessage faultMessage={rent.faultMessage} />
      <Paper className={classes.navPanel}>
        <Grid container justify="space-evenly">
          <Button onClick={handleOpen} variant="contained" color="secondary">
            Add issue
          </Button>
          <Button onClick={onAccept} variant="contained" color="primary">
            Accept
          </Button>
        </Grid>
      </Paper>
      <Dialog
        onClose={handleClose}
        aria-labelledby="simple-dialog-title"
        open={open}
      >
        <AddFaultDialog licensePlate={rent.carDTO.licensePlate} />
      </Dialog>
    </Paper>
  );
};

export default PendingRentItem;
