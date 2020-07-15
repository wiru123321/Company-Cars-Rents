import React, { useState } from "react";

import Cointainer from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import {
  TextField,
  Box,
  Button,
  IconButton,
  makeStyles,
} from "@material-ui/core";
import { PhotoCamera, Save, Delete } from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
  input: {
    display: "none",
  },
  button: {
    margin: theme.spacing(1),
  },
}));
const AddNewCar = () => {
  const classes = useStyles();
  const [brand, setBrand] = useState("");
  const [type, setType] = useState("");
  const [licencePlate, setLicencePlate] = useState("");
  const [fuelType, setFuelType] = useState("");
  const [year, setYear] = useState("");
  const [milage, setMilage] = useState("");
  const [hp, setHp] = useState("");
  const [peopleCapacity, setPeopleCapacity] = useState("");
  const [doorsNumber, setDoorsNumber] = useState("");
  const [color, setColor] = useState("");
  const [gearboxType, setGearboxType] = useState("");
  const [trunkCapacity, setTrunkCapacity] = useState("");
  const [imageUrl, setImageUrl] = useState("");

  return (
    <Cointainer fixed>
      <Typography
        component="div"
        style={{ backgroundColor: "#cfe8fc", height: "94.6vh", width: "100%" }}
      >
        <form style={{ width: "50vw", margin: "auto" }}>
          <div style={{ height: "5vh" }}></div>
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <h3
              style={{
                fontWeight: "bold",
                fontSize: "30px",
                textTransform: "uppercase",
              }}
            >
              Add car form.
            </h3>
          </Box>
          <Box
            display="flex"
            justifyContent="center"
            m={1}
            p={1}
            style={{ height: "10vh" }}
          >
            <Box>
              <TextField
                id="standard-basic"
                label="Brand"
                onChange={(event) => setBrand(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Type"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setType(event.target.value)}
              />
            </Box>
          </Box>
          <Box
            display="flex"
            justifyContent="center"
            m={1}
            p={1}
            style={{ height: "10vh" }}
          >
            <Box>
              <TextField
                id="standard-basic"
                label="Licence Plate"
                onChange={(event) => setLicencePlate(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Fuel Type"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setFuelType(event.target.value)}
              />
            </Box>
          </Box>
          <Box
            display="flex"
            justifyContent="center"
            m={1}
            p={1}
            style={{ height: "10vh" }}
          >
            <Box>
              <TextField
                id="standard-basic"
                label="Year"
                onChange={(event) => setYear(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Mileage"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setMilage(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="HP"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setHp(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="People Capacity"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setPeopleCapacity(event.target.value)}
              />
            </Box>
          </Box>
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box>
              <TextField
                id="standard-basic"
                label="Doors Number"
                onChange={(event) => setDoorsNumber(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Color"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setColor(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Gearbox Type"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setGearboxType(event.target.value)}
              />
            </Box>
            <Box>
              <TextField
                id="standard-basic"
                label="Trunk Capacity"
                style={{ marginLeft: "10%" }}
                onChange={(event) => setTrunkCapacity(event.target.value)}
              />
            </Box>
          </Box>
          <Box
            display="flex"
            justifyContent="center"
            m={1}
            p={1}
            className={classes.root}
          >
            <input
              accept="image/*"
              className={classes.input}
              id="contained-button-file"
              multiple
              type="file"
              onChange={(event) => setImageUrl(event.target.value)}
            />
            <label htmlFor="contained-button-file">
              <Button variant="contained" color="primary" component="span">
                Upload
              </Button>
            </label>
            <input
              accept="image/*"
              className={classes.input}
              id="icon-button-file"
              type="file"
              onChange={(event) => setImageUrl(event.target.value)}
            />
            <label htmlFor="icon-button-file">
              <IconButton
                color="primary"
                aria-label="upload picture"
                component="span"
              >
                <PhotoCamera />
              </IconButton>
            </label>
          </Box>
          <Box display="flex" justifyContent="center" m={1} p={1}>
            <Box>
              <Button
                variant="contained"
                color="primary"
                size="normal"
                className={classes.button}
                startIcon={<Save />}
              >
                Save
              </Button>
            </Box>
            <Box>
              <Button
                variant="contained"
                color="secondary"
                className={classes.button}
                startIcon={<Delete />}
              >
                Delete
              </Button>
            </Box>
          </Box>
          <Typography variant="h6" gutterBottom>
            {brand}
          </Typography>
        </form>
      </Typography>
    </Cointainer>
  );
};

export default AddNewCar;
