import React from "react";
import {
  TextField,
  Box,
  Button,
  IconButton,
  makeStyles,
} from "@material-ui/core";
import { Save, Delete } from "@material-ui/icons";
import { useSelector, useDispatch } from "react-redux";
import {
  selectMarks,
  selectTypes,
  selectFuelType,
  selectColor,
  selectGearboxType,
} from "../../../features/starting-car-parameter/startingCarParameterSlice";
import {
  selectAll,
  addCar,
  brandChange,
  typeChange,
  licencePlateChange,
  fuelTypeChange,
  yearChange,
  milageChange,
  hpChange,
  peopleCapacityChange,
  doorsNumberChange,
  colorChange,
  gearboxTypeChange,
  trunkCapacityChange,
  imageUrlChange,
} from "../../../features/add-car-info/carsInfoSlice";
import SelectBoxForm from "./SelectBoxForm";

const AddCarForm = () => {
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

  const dispatch = useDispatch();
  const classes = useStyles();
  const marks = useSelector(selectMarks);
  const types = useSelector(selectTypes);
  const FuelTypes = useSelector(selectFuelType);
  const ColorTypes = useSelector(selectColor);
  const GearboxType = useSelector(selectGearboxType);
  const CarInfo = useSelector(selectAll);

  const {
    modelDTO,
    typeDTO,
    licensePlate,
    fuelTypeDTO,
    productionYear,
    mileage,
    enginePower,
    capacityOfPeople,
    doorsNumber,
    colourDTO,
    gearBoxTypeDTO,
    capacityOfTrunkScale,
    photoInFolderName,
  } = CarInfo;

  function submit(event) {
    event.preventDefault();
    let car = {
      licensePlate: licensePlate,
      enginePower: enginePower,
      capacityOfTrunkScale: capacityOfTrunkScale,
      capacityOfPeople: capacityOfPeople,
      doorsNumber: doorsNumber,
      gearBoxTypeDTO: { name: gearBoxTypeDTO },
      fuelTypeDTO: { name: fuelTypeDTO },
      lastInspection: "2000-03-25T00:00:00",
      productionYear: productionYear,
      isActive: true,
      mileage: mileage,
      modelDTO: { modelDTO, markDTO: { name: modelDTO } },
      parkingDTO: {
        town: "Katowice",
        postalCode: "40-001",
        streetName: "Norweska 3",
        number: "E-6",
        comment: "Parking przy sklepiku Avea",
        isActive: true,
      },
      colourDTO: { name: colourDTO },
      typeDTO: { name: typeDTO },
    };
    dispatch(addCar(car));
  }

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
      <SelectBoxForm
        modelDTO={modelDTO}
        typeDTO={typeDTO}
        fuelTypeDTO={fuelTypeDTO}
        colourDTO={colourDTO}
        gearBoxTypeDTO={gearBoxTypeDTO}
      />
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextField
            label="Licence Plate"
            onChange={(event) =>
              dispatch(licencePlateChange(event.target.value))
            }
            value={licensePlate}
          />
        </Box>
        <Box>
          <TextField
            label="Year"
            onChange={(event) => dispatch(yearChange(event.target.value))}
            style={{ marginLeft: "10%" }}
            value={productionYear}
          />
        </Box>
        <Box>
          <TextField
            label="Mileage"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(milageChange(event.target.value))}
            value={mileage}
          />
        </Box>
        <Box>
          <TextField
            label="HP"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(hpChange(event.target.value))}
            value={enginePower}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center">
        <Box>
          <TextField
            label="Doors Number"
            onChange={(event) =>
              dispatch(doorsNumberChange(event.target.value))
            }
            value={doorsNumber}
          />
        </Box>
        <Box>
          <TextField
            label="People Capacity"
            style={{ marginLeft: "10%" }}
            onChange={(event) =>
              dispatch(peopleCapacityChange(event.target.value))
            }
            value={capacityOfPeople}
          />
        </Box>

        <Box>
          <TextField
            label="Trunk Capacity"
            style={{ marginLeft: "10%" }}
            onChange={(event) =>
              dispatch(trunkCapacityChange(event.target.value))
            }
            value={capacityOfTrunkScale}
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
          onChange={(event) => dispatch(imageUrlChange(event.target.value))}
          value={photoInFolderName}
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
            onClick={submit}
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

export default AddCarForm;
