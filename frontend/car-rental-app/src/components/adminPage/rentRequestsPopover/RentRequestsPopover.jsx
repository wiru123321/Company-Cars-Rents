import React, { useRef, useState } from "react";

import { Popover, Button, Overlay, Badge } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import { selectRequests } from "../../../features/rent-requests/rentRequestsSlice";
import ContentItem from "./ContentItem";

const RentRequestsPopover = () => {
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
      <Button variant="outline-secondary" onClick={handleClick}>
        Rent requests <Badge variant="light">{requests.length}</Badge>
        <span className="sr-only">unread messages</span>
      </Button>

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
              <ContentItem request={request} />
            ))}
          </Popover.Content>
        </Popover>
      </Overlay>
    </div>
  );
};

export default RentRequestsPopover;
