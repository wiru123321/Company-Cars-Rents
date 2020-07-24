import React, { useRef, useState } from "react";

import { Popover, Button, Overlay, Badge } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import {
  selectRequests,
  chooseRequest,
} from "../../../features/rent-requests/rentRequestsSlice";
import ContentItem from "./ContentItem";

const RentRequestsPopover = () => {
  const requests = useSelector(selectRequests);
  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);
  const dispatch = useDispatch();
  const ref = useRef(null);
  const handleClick = (event) => {
    setShow(!show);
    setTarget(event.target);
  };
  return (
    <div ref={ref}>
      <Button
        onClick={(event) => {
          dispatch(chooseRequest(""));
        }}
        href="#/adminPage/rentRequest"
        size="sm"
      >
        Rent requests
      </Button>
      <Badge variant="light">
        <Button variant="outline-primary" size="sm" onClick={handleClick}>
          {requests.length}
        </Button>
      </Badge>

      <span className="sr-only">unread messages</span>
      <Overlay
        show={show}
        target={target}
        placement="bottom"
        container={ref.current}
      >
        <Popover id="popover-contained">
          <Popover.Title as="h3">Requests</Popover.Title>
          <Popover.Content>
            {requests.map((request, index) => (
              <ContentItem key={index} request={request} index={index} />
            ))}
          </Popover.Content>
        </Popover>
      </Overlay>
    </div>
  );
};

export default RentRequestsPopover;
