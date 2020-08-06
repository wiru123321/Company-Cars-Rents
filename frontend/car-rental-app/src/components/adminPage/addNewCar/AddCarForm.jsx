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
import { useSelector, useDispatch } from "react-redux";
import {
  selectMarks,
  selectTypes,
  selectFuelType,
  selectColor,
  selectGearboxType,
} from "../../../features/starting-car-parameter/startingCarParameterSlice";
import {
  selectImageUrl,
  reset,
  selectAll,
  addCar,
} from "../../../features/add-car-info/carsInfoSlice";

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
  handleimageUrlChange,
}) => {
  const dispatch = useDispatch();
  const classes = useStyles();
  const marks = useSelector(selectMarks);
  const types = useSelector(selectTypes);
  const FuelTypes = useSelector(selectFuelType);
  const ColorTypes = useSelector(selectColor);
  const GearboxType = useSelector(selectGearboxType);
  const CarInfo = useSelector(selectAll);

  const {
    brand,
    type,
    licencePlate,
    fuelType,
    year,
    milage,
    hp,
    color,
    peopleCapacity,
    doorsNumber,
    gearboxType,
    trunkCapacity,
    imageUrl,
  } = CarInfo;

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
        <SelectBox
          SelectHandler={marks}
          NameHandler="Mark"
          handleChange={handleBrandChange}
          handlerValue={brand}
        />
        <SelectBox
          SelectHandler={types}
          NameHandler="Type"
          handleChange={handletypeChange}
          handlerValue={type}
        />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <SelectBox
          SelectHandler={FuelTypes}
          NameHandler="Fuel Type"
          handleChange={handlefuelTypeChange}
          handlerValue={fuelType}
        />
        <SelectBox
          SelectHandler={ColorTypes}
          NameHandler="Color"
          handleChange={handlecolorChange}
          handlerValue={color}
        />
        <SelectBox
          SelectHandler={GearboxType}
          NameHandler="Gearbox Type"
          handleChange={handlegearboxTypeChange}
          handlerValue={gearboxType}
        />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextField
            label="Licence Plate"
            onChange={handlelicencePlateChange}
            value={licencePlate}
          />
        </Box>
        <Box>
          <TextField
            label="Year"
            onChange={handleyearChange}
            style={{ marginLeft: "10%" }}
            value={year}
          />
        </Box>
        <Box>
          <TextField
            label="Mileage"
            style={{ marginLeft: "10%" }}
            onChange={handlemilageChange}
            value={milage}
          />
        </Box>
        <Box>
          <TextField
            label="HP"
            style={{ marginLeft: "10%" }}
            onChange={handlehpChange}
            value={hp}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center">
        <Box>
          <TextField
            label="Doors Number"
            onChange={handledoorsNumberChange}
            value={doorsNumber}
          />
        </Box>
        <Box>
          <TextField
            label="People Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handlepeopleCapacityChange}
            value={peopleCapacity}
          />
        </Box>

        <Box>
          <TextField
            label="Trunk Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handletrunkCapacityChange}
            value={trunkCapacity}
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
          onChange={handleimageUrlChange}
          value={imageUrl}
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
            type="submit"
            onClick={dispatch(addCar(CarInfo))}
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
            type="submit"
          >
            Delete
          </Button>
        </Box>
      </Box>
    </div>
  );
};
