import React, { useState } from "react";
import {
  Button,
  Grid,
  Select,
  MenuItem,
  Typography,
  TextareaAutosize,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
const useStyles = makeStyles({
  textArea: {
    padding: "8px",
    width: "30vw",
  },
  box: {
    height: "30vh",
    width: "40vw",
  },
});

const AddFault = ({ carLicensePlate, onSubmit }) => {
  const classes = useStyles();
  const [description, setDescription] = useState("");
  const [setCarInactive, changeInactive] = useState(false);

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleInactiveChange = (event) => {
    changeInactive(event.target.value);
  };

  return (
    <form
      onSubmit={() =>
        onSubmit({ description, carLicensePlate, setCarInactive })
      }
    >
      <Grid
        className={classes.box}
        container
        direction="column"
        justify="space-evenly"
        alignItems="center"
      >
        <Grid item>
          <Grid container direction="column">
            <Typography>Fault description</Typography>
            <TextareaAutosize
              className={classes.textArea}
              onChange={handleDescriptionChange}
              value={description}
              placeholder="Empty"
              required
            />
          </Grid>
        </Grid>
        <Grid item>
          <Grid container justify="flex-start" alignItems="center">
            <Typography>Should suspend car?</Typography>
            <Select onChange={handleInactiveChange} value={setCarInactive}>
              <MenuItem value={false}>No</MenuItem>
              <MenuItem value={true}>Yes</MenuItem>
            </Select>
          </Grid>
        </Grid>
        <Grid item>
          <Button type="submit">Submit</Button>
        </Grid>
      </Grid>
    </form>
  );
};

export default AddFault;
