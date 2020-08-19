import React, { useState } from "react";
import { Grid, Paper, Button, Dialog } from "@material-ui/core";
import ReservationInfo from "../activeRents/cards/ReservationInfo";
import AddFaultDialog from "./AddFaultDialog";
import FaultMessage from "./FaultMessage";

const PendingRentItem = ({ rent, handleAccept }) => {
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
    <Paper style={{ minWidth: "30vw" }}>
      <ReservationInfo
        user={rent.userRentInfo}
        dateFrom={rent.dateFrom}
        dateTo={rent.dateTo}
        parkingFrom={rent.parkingFrom}
        parkingTo={rent.parkingTo}
        car={rent.carDTO}
      />
      <FaultMessage faultMessage={rent.faultMessage} />
      <Paper style={{ padding: "8px" }}>
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
