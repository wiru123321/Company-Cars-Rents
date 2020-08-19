import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  Grid,
  Paper,
  Typography,
  Button,
  Dialog,
  Input,
} from "@material-ui/core";
import {
  fetchPendingEndRents,
  acceptPendingRent,
  selectAll,
} from "../../../features/rents/pendingRents";
import { useAlert } from "react-alert";
import ReservationInfo from "../activeRents/cards/ReservationInfo";
import NotFoundMessage from "../messages/NotFoundMessage";
import AddIssuesForm from "../manageCars/issues/addIssues/AddIssuesForm";
import { addFault } from "../../../features/cars-manager/carsManagerSlice";

const PendingRents = () => {
  const alert = useAlert();
  const dispatch = useDispatch();
  const { rents } = useSelector(selectAll);

  const handleAcceptPendingRent = (id) => {
    dispatch(acceptPendingRent(id, alert));
  };

  useEffect(() => {
    dispatch(fetchPendingEndRents());
  }, []);

  return (
    <Grid
      style={{ minHeight: "80vh", minWidth: "60vw" }}
      container
      direction="column"
      justify="center"
      alignItems="center"
    >
      {rents.length > 0 ? (
        rents.map((rent, index) => (
          <PendingRentItem
            key={rent.id}
            handleAccept={handleAcceptPendingRent}
            rent={rent}
          />
        ))
      ) : (
        <NotFoundMessage>There are no pending rents.</NotFoundMessage>
      )}
    </Grid>
  );
};

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

      <Paper>
        <Typography color="secondary">Reported issues</Typography>
        <Typography color="secondary">{rent.faultMessage}</Typography>
        <Button onClick={handleOpen}>Add issue</Button>
      </Paper>

      <Button onClick={onAccept} variant="contained" color="primary">
        Accept
      </Button>
      <Dialog
        onClose={handleClose}
        aria-labelledby="simple-dialog-title"
        open={open}
      >
        <AddFault licensePlate={rent.carDTO.licensePlate} />
      </Dialog>
    </Paper>
  );
};

const AddFault = ({ licensePlate }) => {
  const alert = useAlert();
  const [description, setDescription] = useState("");
  const [setCarInactive, changeCarInactive] = useState(false);
  const dispatch = useDispatch();

  const handleReset = () => {
    setDescription("");
    changeCarInactive(false);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleCarActivityChange = (event) => {
    changeCarInactive(event.target.checked);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let fault = {
      description: description,
      setCarInactive: setCarInactive,
      carLicensePlate: licensePlate,
    };

    dispatch(addFault(fault, alert));
    handleReset();
  };

  return (
    <Grid container style={{ padding: "8px" }}>
      <AddIssuesForm
        description={description}
        setCarInactive={setCarInactive}
        handleDescriptionChange={handleDescriptionChange}
        handleCarInactiveChange={handleCarActivityChange}
        handleSubmit={handleSubmit}
      />
    </Grid>
  );
};

export default PendingRents;
