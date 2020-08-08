import React from "react";
import { Box } from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import { useDispatch } from "react-redux";
import {
  townChange,
  postalCodeChange,
  streetNameChange,
  numberChange,
  commentChange,
} from "../../../features/add-car-info/carsInfoSlice";

const ParkingForm = ({ comment, number, streetName, postalCode, town }) => {
  const dispatch = useDispatch();
  return (
    <div>
      <Box display="flex" justifyContent="center">
        <h3
          style={{
            fontWeight: "bold",
            fontSize: "20px",
            textTransform: "uppercase",
          }}
        >
          Parking Information
        </h3>
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <Box>
          <TextValidator
            label="Town"
            onChange={(event) => dispatch(townChange(event.target.value))}
            value={town}
            validators={["required", "isString"]}
            errorMessages={["this field is required", "Number prohibited"]}
          />
        </Box>
        <Box>
          <TextValidator
            label="Postal Code"
            onChange={(event) => dispatch(postalCodeChange(event.target.value))}
            style={{ marginLeft: "10%" }}
            value={postalCode}
            validators={[
              "required",
              "isNumber",
              "minStringLength:5",
              "maxStringLength:5",
            ]}
            errorMessages={[
              "this field is required",
              "Must be a number",
              "Example : 00000",
            ]}
          />
        </Box>
        <Box>
          <TextValidator
            label="Street Name"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(streetNameChange(event.target.value))}
            value={streetName}
            validators={["required", "isString", "minStringLength:1"]}
            errorMessages={[
              "this field is required",
              "Number prohibited",
              "Length is wrong",
            ]}
          />
        </Box>
        <Box>
          <TextValidator
            label="Number"
            style={{ marginLeft: "10%" }}
            onChange={(event) => dispatch(numberChange(event.target.value))}
            value={number}
            validators={["required", "minStringLength:1"]}
            errorMessages={["this field is required", "Length is wrong"]}
          />
        </Box>
      </Box>
      <Box display="flex" justifyContent="center" style={{ height: "10vh" }}>
        <TextValidator
          label="Additional Information"
          style={{ marginLeft: "10%" }}
          onChange={(event) => dispatch(commentChange(event.target.value))}
          value={comment}
        />
      </Box>
    </div>
  );
};

export default ParkingForm;
