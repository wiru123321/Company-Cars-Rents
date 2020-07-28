import React from "react";

import Cointainer from "@material-ui/core/Container";
import Typography from "@material-ui/core/Typography";
import { AddCarForm, AddCarButton } from "./AddCarForm";
import {
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
import { useDispatch } from "react-redux";

const AddNewCar = () => {
  const dispatch = useDispatch();
  return (
    <Cointainer fixed>
      <Typography
        component="div"
        style={{ backgroundColor: "#cfe8fc", height: "94.6vh", width: "100%" }}
      >
        <form style={{ width: "50vw", margin: "auto" }}>
          <div style={{ height: "5vh" }}></div>
          <AddCarForm
            handleBrandChange={dispatch(brandChange())}
            handletypeChange={dispatch(typeChange())}
            handlelicencePlateChange={dispatch(licencePlateChange())}
            handlefuelTypeChange={dispatch(fuelTypeChange())}
            handleyearChange={dispatch(yearChange())}
            handlemilageChange={dispatch(milageChange())}
            handlehpChange={dispatch(hpChange())}
            handlepeopleCapacityChange={dispatch(peopleCapacityChange())}
            handledoorsNumberChange={dispatch(doorsNumberChange())}
            handlecolorChange={dispatch(colorChange())}
            handlegearboxTypeChange={dispatch(gearboxTypeChange())}
            handletrunkCapacityChange={dispatch(trunkCapacityChange())}
          />
          <AddCarButton handleimageUrlChange={dispatch(imageUrlChange())} />
        </form>
      </Typography>
    </Cointainer>
  );
};

export default AddNewCar;
