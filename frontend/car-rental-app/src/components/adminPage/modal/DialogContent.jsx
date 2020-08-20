import React from "react";
import { Paper, Button, Grid, makeStyles } from "@material-ui/core";
import CarInfoCard from "../activeRents/resrvationUi/CarInfoCard";
import DoneIcon from "@material-ui/icons/Done";

const API_URL = "http://localhost:8080";

const useStyles = makeStyles({
  paper: {
    padding: "8px",
    minHeight: "40vh",
    width: "50vw",
    maxHeight: "60vh",
    overflow: "auto",
  },
  active: {
    margin: "1%",
    padding: "8px",
    cursor: "pointer",
  },
  item: {
    margin: "1%",
    padding: "8px",
    backgroundColor: "#FFFAFA",
    cursor: "pointer",
  },
  okButton: {
    width: "100%",
    padding: "4px",
  },
  activeIcon: {
    backgroundColor: "#00FF7F",
    minWidth: "6vw",
    textAlign: "center",
  },
});

const ModalContent = ({
  cars,
  changeCurrentCar,
  handleClose,
  setActive,
  currentIndex,
}) => {
  const classes = useStyles();

  return (
    <Grid container>
      <Grid className={classes.paper}>
        {cars.map((car, index) => {
          const handleCarChange = () => {
            changeCurrentCar(car);
            setActive(index);
          };
          return (
            <Paper className={classes.item} onClick={handleCarChange}>
              <Grid
                container
                direction="column"
                justify="center"
                alignItems="center"
              >
                <CarInfoCard car={car} />
                {currentIndex === index && (
                  <Paper className={classes.activeIcon}>
                    <DoneIcon />
                  </Paper>
                )}
              </Grid>
            </Paper>
          );
        })}
      </Grid>
      <Button onClick={handleClose} className={classes.okButton}>
        Ok
      </Button>
    </Grid>
  );
};

export default ModalContent;
