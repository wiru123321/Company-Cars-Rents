import React from "react";
import {
  DialogTitle,
  DialogContent,
  DialogActions,
  Dialog,
  Button,
  TextField,
} from "@material-ui/core";
import {
  selectBugopen,
  bugDescribeChane,
  bugOpenChange,
} from "../../../features/your-cars/yourCarsSlice";
import { useSelector, useDispatch } from "react-redux";

const BugReport = () => {
  const dispatch = useDispatch();
  const selectOpen = useSelector(selectBugopen);

  return (
    <div>
      <Button
        variant="contained"
        color="secondary"
        onClick={() => {
          dispatch(bugOpenChange());
        }}
      >
        Report a bug
      </Button>
      <Dialog
        disableBackdropClick
        disableEscapeKeyDown
        open={selectOpen}
        onClose={() => {
          dispatch(bugOpenChange());
        }}
      >
        <DialogTitle>Bug form</DialogTitle>
        <DialogContent>
          <TextField
            id="outlined-multiline-static"
            label="Multiline"
            multiline
            rows={4}
            label="Bug report"
            variant="outlined"
            onChange={(event) => dispatch(bugDescribeChane(event.target.value))}
          />
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              dispatch(bugOpenChange());
            }}
            color="primary"
          >
            Cancel
          </Button>
          <Button
            onClick={() => {
              dispatch(bugOpenChange());
            }}
            color="primary"
          >
            Send
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default BugReport;
