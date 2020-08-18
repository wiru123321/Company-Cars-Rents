import React, { useState } from "react";
import { Grid, Paper, Button, makeStyles } from "@material-ui/core";
import ChangeCar from "./ChangeCar";

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    minWidth: "60vw",
  },
});

const ActiveRentsMenu = ({ exitMenu, currentRent }) => {
  const classes = useStyles();
  const [info, toggleInfo] = useState(true);
  return (
    <Grid container justify="center">
      <Paper className={classes.paper}>
        <Grid container justify="center" alignItems="center">
          <Grid>
            <Button onClick={exitMenu}>Back</Button>
          </Grid>
          <Grid>
            <Button onClick={() => toggleInfo(true)}>Info</Button>
          </Grid>
          <Grid>
            <Button onClick={() => toggleInfo(false)}>Change car</Button>
          </Grid>
        </Grid>
        <Grid container direction="column" justify="center" alignItems="center">
          {info ? (
            <div>info</div>
          ) : (
            <ChangeCar
              rent={currentRent}
              car={currentRent.carDTO}
              dateFrom={currentRent.dateFrom}
              dateTo={currentRent.dateTo}
            />
          )}
        </Grid>
      </Paper>
    </Grid>
  );
};

export default ActiveRentsMenu;
