import React, { useState } from "react";
import { Box, Button, makeStyles, Grid } from "@material-ui/core";
import { Save } from "@material-ui/icons";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import {
  selectAll,
  addCar,
  reset,
  imageFileChange,
  addImage,
} from "../../../../features/add-car-info/carsInfoSlice";
import SelectBoxForm from "./SelectBoxForm";
import BoxPanel from "./BoxPanel";
import ParkingForm from "./ParkingForm";
import PhotoForm from "./ControlPanel/PhotoForm";
import SaveForm from "./ControlPanel/SaveForm";
const API_URL = "http://localhost:8080";

const AddCarForm = () => {
  const [showAddPhotoButton, toggleshowAddPhotoButton] = useState(false);
  const [showSaveButton, toggleshowSaveButton] = useState(true);
  const [showFormError, toggleshowFormError] = useState(false);
  const [photo, setPhoto] = useState("");
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
  const CarInfo = useSelector(selectAll);
  let error1 = "";

  const {
    markDTO,
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
    imageFile,
    lastInspection,
    town,
    streetName,
    number,
    comment,
    postalCode,
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
      lastInspection: lastInspection + "T00:00:00",
      productionYear: productionYear,
      isActive: true,
      mileage: mileage,
      modelDTO: { name: markDTO, markDTO: { name: modelDTO } },
      parkingDTO: {
        town: town,
        postalCode: postalCode,
        streetName: streetName,
        number: number,
        comment: comment,
        isActive: true,
      },
      colourDTO: { name: colourDTO },
      typeDTO: { name: typeDTO },
    };
    if (
      licensePlate &&
      enginePower &&
      capacityOfTrunkScale &&
      capacityOfPeople &&
      doorsNumber &&
      gearBoxTypeDTO &&
      fuelTypeDTO &&
      lastInspection &&
      productionYear &&
      mileage &&
      town &&
      postalCode &&
      number &&
      colourDTO &&
      typeDTO
    ) {
      toggleshowAddPhotoButton(true);
      toggleshowSaveButton(false);
      toggleshowFormError(false);
      dispatch(addCar(car));
    } else {
      toggleshowFormError(true);
    }
  }

  const handlePhotoChange = (event) => {
    setPhoto(event.target.files[0]);
    console.log(event.target.files[0]);
  };

  function submitImage(event) {
    event.preventDefault();
    let formData = new FormData();
    formData.append("imageFile", photo);
    axios
      .post(API_URL + `/a/car/upload-car-image/${licensePlate}`, formData, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
          "content-type": "multipart/form-data",
        },
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => console.log(JSON.stringify(error)));
    toggleshowAddPhotoButton(false);
    toggleshowSaveButton(true);
    dispatch(reset());
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
        markDTO={markDTO}
      />
      <BoxPanel
        mileage={mileage}
        enginePower={enginePower}
        licensePlate={licensePlate}
        productionYear={productionYear}
        capacityOfPeople={capacityOfPeople}
        doorsNumber={doorsNumber}
        capacityOfTrunkScale={capacityOfTrunkScale}
      />
      <div style={{ height: "5vh" }}></div>
      <ParkingForm
        town={town}
        postalCode={postalCode}
        streetName={streetName}
        number={number}
        comment={comment}
      />
      {error1}
      {showSaveButton ? (
        <Box display="flex" justifyContent="center">
          <SaveForm submit={submit} />
          {showFormError ? (
            <Box>
              <p>Error in form! Please check form one more.</p>
            </Box>
          ) : (
            <div></div>
          )}
        </Box>
      ) : (
        <Box display="flex" justifyContent="center">
          <Box>
            <p>Car added, please add photo.</p>
          </Box>
        </Box>
      )}

      {showAddPhotoButton ? (
        <PhotoForm
          submitImage={submitImage}
          handlePhotoChange={handlePhotoChange}
        />
      ) : null}
    </div>
  );
};

export default AddCarForm;
