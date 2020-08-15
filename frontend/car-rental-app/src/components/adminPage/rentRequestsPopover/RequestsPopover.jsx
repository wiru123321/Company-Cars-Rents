import React, { useRef, useEffect, useState } from "react";
import { Nav } from "react-bootstrap";
import { Popover, Button, Container } from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import { selectAll } from "../../../features/rents/rentsSlice";
import { makeStyles, Badge } from "@material-ui/core";
import ContentItem from "./ContentItem";
import DriveEtaIcon from "@material-ui/icons/DriveEta";

const useStyles = makeStyles({
  paper: {
    maxHeight: "30vh",
    width: "30vw",
    padding: "8px",
  },
  button: {
    borderRadius: "50%",
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
        <Button
          className={classes.button}
          // variant="contained"
          onClick={handleClick}
        >
          {pendingRents.length > 0 ? (
            <Badge badgeContent={pendingRents.length} color="secondary">
              <DriveEtaIcon color="secondary" />
            </Badge>
          ) : (
            <DriveEtaIcon color="primary" />
          )}
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
