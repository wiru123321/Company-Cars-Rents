import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid, makeStyles } from "@material-ui/core";
import {
  fetchPendingEndRents,
  acceptPendingRent,
  selectAll,
} from "../../../features/rents/pendingRents";
import { useAlert } from "react-alert";
import PendingRentItem from "./PendingRentItem";
import NotFoundMessage from "../messages/NotFoundMessage";

const useStyles = makeStyles({
  content: {
    minHeight: "80vh",
    minWidth: "60vw",
  },
});

const PendingRents = () => {
  const classes = useStyles();
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
      className={classes.content}
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

export default PendingRents;
