import React, { useRef, useEffect, useState } from "react";
import { Nav } from "react-bootstrap";
import {
  Popover,
  Typography,
  Button,
  Paper,
  Container,
} from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";

import ContentItem from "./ContentItem";
import {
  selectAll,
  fetchPendingRents,
} from "../../../features/rents/rentsSlice";
import { makeStyles } from "@material-ui/core";

const useStyles = makeStyles({
  paper: {
    maxHeight: "15vh",
    width: "30vw",
    padding: "8px",
  },
});

const RequestsPopover = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const { pendingRents } = useSelector(selectAll);
  const [anchorEl, setAnchorEl] = useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const id = open ? "simple-popover" : undefined;

  return (
    <>
      <Nav.Link>
        <Button variant="contained" size="sm" onClick={handleClick}>
          {pendingRents.length}
        </Button>
      </Nav.Link>
      <Popover
        id={id}
        open={open}
        anchorEl={anchorEl}
        onClose={handleClose}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "center",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "center",
        }}
      >
        <Container className={classes.paper}>
          {pendingRents.map((rent, index) => (
            <ContentItem key={index} rent={rent} index={index} />
          ))}
        </Container>
      </Popover>
    </>
  );
};

export default RequestsPopover;
