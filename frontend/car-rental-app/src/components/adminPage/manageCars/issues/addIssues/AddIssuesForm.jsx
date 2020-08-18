import React from "react";
import { ValidatorForm } from "react-material-ui-form-validator";
import {
  Grid,
  Paper,
  Button,
  Checkbox,
  TextareaAutosize,
  Typography,
  makeStyles,
} from "@material-ui/core";
import ReportIcon from "@material-ui/icons/Report";

const useStyles = makeStyles({
  box: {
    minHeight: "40vh",
  },
  textInput: {
    width: "60ch",
  },
  textArea: {
    width: "40vw",
    padding: "4px",
    minHeight: "10vh",
    maxHeight: "30vh",
  },
  paper: {
    padding: "8px",
  },
  dateInput: {
    width: "20ch",
  },
  button: {
    width: "24vw",
  },
});

const AddIssuesForm = ({
  description,
  setCarInactive,
  handleDescriptionChange,
  handleCarInactiveChange,
  handleSubmit,
}) => {
  const classes = useStyles();
  return (
    <ValidatorForm onSubmit={handleSubmit}>
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="space-evenly"
        alignItems="center"
      >
        <Paper className={classes.paper}>
          <TextareaAutosize
            onChange={handleDescriptionChange}
            value={description}
            required
            placeholder="Issue description..."
            className={classes.textArea}
          />
        </Paper>
        <Paper className={classes.paper}>
          <Typography>
            Should suspend a car?
            <Checkbox
              checked={setCarInactive}
              onChange={handleCarInactiveChange}
            />
          </Typography>
        </Paper>
        <Button
          className={classes.button}
          type="submit"
          variant="contained"
          color="secondary"
          startIcon={<ReportIcon />}
        >
          Report
        </Button>
      </Grid>
    </ValidatorForm>
  );
};

export default AddIssuesForm;
