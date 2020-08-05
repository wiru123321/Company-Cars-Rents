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
  selectYear,
  selectGearboxTypeValue,
  selectColorValue,
  selectFuelTypeValue,
  selectBrandValue,
  selectDoorsNumber,
  selectHp,
  selectImageUrl,
  selectLicencePlate,
  selectMilage,
  selectPeopleCapacity,
  selectTrunkCapacity,
  selectTypeValue,
  reset,
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
}) => {
  const marks = useSelector(selectMarks);
  const types = useSelector(selectTypes);
  const FuelTypes = useSelector(selectFuelType);
  const Color = useSelector(selectColor);
  const GearboxType = useSelector(selectGearboxType);
  const Year = useSelector(selectYear);
  const DoorsNumber = useSelector(selectDoorsNumber);
  const Hp = useSelector(selectHp);
  const LicencePlate = useSelector(selectLicencePlate);
  const Milage = useSelector(selectMilage);
  const PeopleCapacity = useSelector(selectPeopleCapacity);
  const TrunkCapacity = useSelector(selectTrunkCapacity);
  const TypeValue = useSelector(selectTypeValue);
  const BrandValue = useSelector(selectBrandValue);
  const GearboxTypeValue = useSelector(selectGearboxTypeValue);
  const ColorValue = useSelector(selectColorValue);
  const FuelTypesValue = useSelector(selectFuelTypeValue);
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
          handlerValue={BrandValue}
        />
        <SelectBox
          SelectHandler={types}
          NameHandler="Type"
          handleChange={handletypeChange}
          handlerValue={TypeValue}
        />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <SelectBox
          SelectHandler={FuelTypes}
          NameHandler="Fuel Type"
          handleChange={handlefuelTypeChange}
          handlerValue={FuelTypesValue}
        />
        <SelectBox
          SelectHandler={Color}
          NameHandler="Color"
          handleChange={handlecolorChange}
          handlerValue={ColorValue}
        />
        <SelectBox
          SelectHandler={GearboxType}
          NameHandler="Gearbox Type"
          handleChange={handlegearboxTypeChange}
          handlerValue={GearboxTypeValue}
        />
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextField
            label="Licence Plate"
            onChange={handlelicencePlateChange}
            value={LicencePlate}
          />
        </Box>
        <Box>
          <TextField
            label="Year"
            onChange={handleyearChange}
            style={{ marginLeft: "10%" }}
            value={Year}
          />
        </Box>
        <Box>
          <TextField
            label="Mileage"
            style={{ marginLeft: "10%" }}
            onChange={handlemilageChange}
            value={Milage}
          />
        </Box>
        <Box>
          <TextField
            label="HP"
            style={{ marginLeft: "10%" }}
            onChange={handlehpChange}
            value={Hp}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center">
        <Box>
          <TextField
            label="Doors Number"
            onChange={handledoorsNumberChange}
            value={DoorsNumber}
          />
        </Box>
        <Box>
          <TextField
            label="People Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handlepeopleCapacityChange}
            value={PeopleCapacity}
          />
        </Box>

        <Box>
          <TextField
            label="Trunk Capacity"
            style={{ marginLeft: "10%" }}
            onChange={handletrunkCapacityChange}
            value={TrunkCapacity}
          />
        </Box>
      </Box>
    </div>
  );
};

export const AddCarButton = ({ handleimageUrlChange, deletehandler }) => {
  const ImageUrl = useSelector(selectImageUrl);
  const dispatch = useDispatch();
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
          value={ImageUrl}
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
            onClick={dispatch(reset())}
          >
            Delete
          </Button>
        </Box>
      </Box>
    </div>
  );
};
