import React from "react";
import {
  chooseRequest,
  selectChoosenRequestIndex,
  selectRequests,
  selectCurrentRequest,
} from "../../../features/rent-requests/rentRequestsSlice";
import {
  Grid,
  Paper,
  List,
  ListItem,
  ListItemText,
  Typography,
  Divider,
} from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import RequestInfo from "./rentRequestInfo/RequestInfo";
import RentRequestControlPanel from "./rentRequestInfo/RentRequestControlPanel";
import { Container } from "@material-ui/core";
import { rentRequestStyles } from "./rentRequestInfo/rentRequest.styles";
const SingleRequest = () => {
  const request = useSelector(selectCurrentRequest);
  const classes = rentRequestStyles();
  return (
    <Container>
      <Paper className={classes.paper}>
        <RequestInfo request={request} />
        <RentRequestControlPanel />
      </Paper>
    </Container>
  );
};

export default SingleRequest;
