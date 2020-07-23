import React from "react";
import { PopoverContent } from "./popover.style";
import { Button } from "react-bootstrap";
const ContentItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
}) => (
  <PopoverContent>
    <p>
      Name: {firstname} {lastname}
    </p>
    <p>
      Begin: {beginDate} - {beginHour}
    </p>
    <p>
      End: {endDate} - {endHour}
    </p>
    <Button variant="outline-primary" size="sm">
      check
    </Button>
  </PopoverContent>
);

export default ContentItem;
