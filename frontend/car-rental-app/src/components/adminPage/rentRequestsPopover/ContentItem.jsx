import React from "react";
import { PopoverContent } from "./popover.style";
import { Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { chooseRequest } from "../../../features/rent-requests/rentRequestsSlice";
const ContentItem = ({
  request: { firstname, lastname, beginDate, beginHour, endDate, endHour },
  index,
}) => {
  const dispatch = useDispatch();
  return (
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
      <Button
        variant="outline-primary"
        size="sm"
        onClick={(event) => {
          dispatch(chooseRequest(index));
        }}
        href="#/adminPage/rentRequest"
      >
        check
      </Button>
    </PopoverContent>
  );
};

export default ContentItem;
