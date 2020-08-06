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
} from "../../../features/add-car-info/carsInfoSlice";

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
    </div>
  );
};

export default BoxPanel;
