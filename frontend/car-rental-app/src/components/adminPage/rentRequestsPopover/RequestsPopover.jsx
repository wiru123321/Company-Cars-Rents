import React, { useRef, useState } from "react";
import { Popover, Button, Overlay, Badge, Nav } from "react-bootstrap";
import { useSelector } from "react-redux";
import { selectRequests } from "../../../features/rent-requests/rentRequestsSlice";
import ContentItem from "./ContentItem";

const RequestsPopover = () => {
  const requests = useSelector(selectRequests);
  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);

  const ref = useRef(null);
  const handleClick = (event) => {
    setShow(!show);
    setTarget(event.target);
  };
  return (
    <div ref={ref}>
      <Nav.Link>
        <Badge variant="light">
          <Button variant="outline-primary" size="sm" onClick={handleClick}>
            {requests.length}
          </Button>
        </Badge>
      </Nav.Link>
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

export default RequestsPopover;
