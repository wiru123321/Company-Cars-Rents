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
  fetchMarks,
} from "../../../features/add-car-info/carsInfoSlice";
import { useDispatch, useSelector } from "react-redux";

const AddNewCar = () => {
  const dispatch = useDispatch();
  const fetch = useSelector(fetchMarks);
  return (
    <Cointainer fixed>
      <Typography
        component="div"
        style={{ backgroundColor: "#cfe8fc", height: "94.6vh", width: "100%" }}
      >
        <form style={{ width: "50vw", margin: "auto" }}>
          <div style={{ height: "5vh" }}></div>
          <AddCarForm
            handleBrandChange={(event) =>
              dispatch(brandChange(event.target.value))
            }
            handletypeChange={(event) =>
              dispatch(typeChange(event.target.value))
            }
            handlelicencePlateChange={(event) =>
              dispatch(licencePlateChange(event.target.value))
            }
            handlefuelTypeChange={(event) =>
              dispatch(fuelTypeChange(event.target.value))
            }
            handleyearChange={(event) =>
              dispatch(yearChange(event.target.value))
            }
            handlemilageChange={(event) =>
              dispatch(milageChange(event.target.value))
            }
            handlehpChange={(event) => dispatch(hpChange(event.target.value))}
            handlepeopleCapacityChange={(event) =>
              dispatch(peopleCapacityChange(event.target.value))
            }
            handledoorsNumberChange={(event) =>
              dispatch(doorsNumberChange(event.target.value))
            }
            handlecolorChange={(event) =>
              dispatch(colorChange(event.target.value))
            }
            handlegearboxTypeChange={(event) =>
              dispatch(gearboxTypeChange(event.target.value))
            }
            handletrunkCapacityChange={(event) =>
              dispatch(trunkCapacityChange(event.target.value))
            }
          />
          <AddCarButton
            handleimageUrlChange={(event) =>
              dispatch(imageUrlChange(event.target.value))
            }
          />
        </form>
      </Typography>
    </Cointainer>
  );
};

export default AddNewCar;
