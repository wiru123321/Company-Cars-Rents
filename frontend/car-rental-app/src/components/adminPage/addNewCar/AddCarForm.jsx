import React from "react";
import {
  TextField,
  Box,
  Button,
  IconButton,
  makeStyles,
} from "@material-ui/core";
import { Save, Delete } from "@material-ui/icons";
import { SelectBox } from "./SelectBox";
import { useSelector } from "react-redux";
import {
  selectMarks,
  selectTypes,
  selectFuelType,
  selectColor,
  selectGearboxType,
} from "../../../features/starting-car-parameter/startingCarParameterSlice";

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

export const AddCarForm = ({
  handleBrandChange,
  handletypeChange,
  handlelicencePlateChange,
  handlefuelTypeChange,
  handleyearChange,
  handlemilageChange,
  handlehpChange,
  handlepeopleCapacityChange,
  handledoorsNumberChange,
  handlecolorChange,
  handlegearboxTypeChange,
  handletrunkCapacityChange,
}) => {
  const marks = useSelector(selectMarks);
  const types = useSelector(selectTypes);
  const FuelTypes = useSelector(selectFuelType);
  const Color = useSelector(selectColor);
  const GearboxType = useSelector(selectGearboxType);
  return (
    <div>
      <Box display="flex" justifyContent="center">
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
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        {/* <Box>
          <TextField label="Brand" onChange={handleBrandChange} />
        </Box> */}
        <SelectBox SelectHandler={marks} NameHandler="Mark" />
        <SelectBox SelectHandler={types} NameHandler="Type" />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <SelectBox SelectHandler={FuelTypes} NameHandler="Fuel Type" />
        <SelectBox SelectHandler={Color} NameHandler="Color" />
        <SelectBox SelectHandler={GearboxType} NameHandler="Gearbox Type" />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextField
            label="Licence Plate"
            onChange={handlelicencePlateChange}
          />
        </Box>
        <Box>
          <TextField
            label="Year"
            onChange={handleyearChange}
            style={{ marginLeft: "10%" }}
          />
        </Box>
        <Box>
          <TextField
            label="Mileage"
            style={{ marginLeft: "10%" }}
            onChange={handleyearChange}
          />
        </Box>
        <Box>
          <TextField
            label="HP"
            style={{ marginLeft: "10%" }}
            onChange={handlehpChange}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center">
        <Box>
          <TextField label="Doors Number" onChange={handledoorsNumberChange} />
        </Box>
        <Box>
          <TextField
            label="People Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handlepeopleCapacityChange}
          />
        </Box>

        <Box>
          <TextField
            label="Trunk Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handletrunkCapacityChange}
          />
        </Box>
      </Box>
    </div>
  );
};

export const AddCarButton = ({ handleimageUrlChange }) => {
  const classes = useStyles();
  return (
    <div>
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
          onChange={handleimageUrlChange}
        />
        <label htmlFor="contained-button-file">
          <Button variant="contained" color="primary" component="span">
            Upload
          </Button>
        </label>
        <label htmlFor="icon-button-file">
          <IconButton
            color="primary"
            aria-label="upload picture"
            component="span"
          ></IconButton>
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
    </div>
  );
};
