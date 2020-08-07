import React from "react";
import { useDispatch } from "react-redux";
import { TextField, Box } from "@material-ui/core";
import {
  licencePlateChange,
  yearChange,
  milageChange,
  hpChange,
  peopleCapacityChange,
  doorsNumberChange,
  trunkCapacityChange,
  formChangeTrue,
  formChangeFalse,
  selectForm,
} from "../../../features/add-car-info/carsInfoSlice";
import { TextValidator } from "react-material-ui-form-validator";

const BoxPanel = ({
  licensePlate,
  productionYear,
  mileage,
  enginePower,
  doorsNumber,
  capacityOfPeople,
  capacityOfTrunkScale,
}) => {
  const dispatch = useDispatch();
  return (
    <div>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextValidator
            label="Licence Plate"
            onChange={(event) =>
              dispatch(licencePlateChange(event.target.value))
            }
            value={licensePlate}
            validators={["required", "minStringLength:7", "maxStringLength:7"]}
            errorMessages={["this field is required", "Length is wrong"]}
          />
        </Box>
        <Box>
          <TextValidator
            label="Year"
            onChange={(event) => dispatch(yearChange(event.target.value))}
            style={{ marginLeft: "10%" }}
            value={productionYear}
            validators={[
              "required",
              "isNumber",
              "minStringLength:4",
              "maxStringLength:4",
            ]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>
        <Box>
          <TextValidator
            label="Mileage"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(milageChange(event.target.value))}
            value={mileage}
            validators={["required", "isNumber", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>
        <Box>
          <TextValidator
            label="HP"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(hpChange(event.target.value))}
            value={enginePower}
            validators={["required", "isNumber", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center">
        <Box>
          <TextValidator
            label="Doors Number"
            onChange={(event) =>
              dispatch(doorsNumberChange(event.target.value))
            }
            value={doorsNumber}
            validators={["required", "isNumber", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>
        <Box>
          <TextValidator
            label="People Capacity"
            style={{ marginLeft: "10%" }}
            onChange={(event) =>
              dispatch(peopleCapacityChange(event.target.value))
            }
            value={capacityOfPeople}
            validators={["required", "isNumber", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>

        <Box>
          <TextValidator
            label="Trunk Capacity"
            style={{ marginLeft: "10%" }}
            onChange={(event) =>
              dispatch(trunkCapacityChange(event.target.value))
            }
            value={capacityOfTrunkScale}
            validators={["required", "isNumber", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Length is wrong",
            ]}
          />
        </Box>
      </Box>
    </div>
  );
};

export default BoxPanel;
