import React, { useRef, useState } from "react";
import { Popover, Button, Overlay, Badge } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import { selectRequests } from "../../features/rent-requests/rentRequestsSlice";

const ContentItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
}) => (
  <div>
    <div style={{ width: "80%" }}>
      {firstname} {lastname}
      <div>
        {beginDate} - {beginHour}
      </div>
      <div>
        {endDate} - {endHour}
      </div>
    </div>
    <div style={{ width: "30%" }}>
      <Button size="sm">check</Button>
    </div>
  </div>
);

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
      <Button onClick={handleClick}>
        Rent requests <Badge variant="light">{requests.length}</Badge>
        <span className="sr-only">unread messages</span>
      </Button>

      <Overlay
        show={show}
        target={target}
        placement="bottom"
        container={ref.current}
        containerPadding={20}
      >
        <Popover id="popover-contained">
          <Popover.Title as="h3">Popover bottom</Popover.Title>
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
