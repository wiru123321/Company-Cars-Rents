import React, { useState, useEffect } from "react";
import CarInfo from "../../../carsListing/CarInfo";
import CarImage from "../../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import UpdateCars from "../updateCars/UpdateCars";
import { ListItem, Grid } from "@material-ui/core";
import axios from "axios";

const Car = ({ car, index, onDelete }) => {
  const [edit, setEdit] = useState(false);
  const [pic, setPic] = useState("");
  const toggleEdit = () => {
    setEdit(!edit);
  };
  let resp = "";
  const getPicture = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/ea/car/download-car-image/WN101",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      let objJsonStr = JSON.stringify(response.data);
      let objJsonB64 = Buffer.from(objJsonStr).toString("base64");
      setPic("data:image/jpeg;base64," + objJsonB64);
      resp = response;
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    getPicture();
  }, []);
  return (
    <ListItem>
      <Grid container justify="center" alignItems="center">
        {edit ? (
          <Grid item xs={8}>
            <UpdateCars car={car} />
          </Grid>
        ) : (
          <>
            <Grid item xs={4}>
              <CarImage
                src={
                  "http://localhost:8080/u/car/download-car-image/" +
                  car.licensePlate
                }
              />
            </Grid>
            <Grid item xs={4}>
              <CarInfo car={car} />
            </Grid>
          </>
        )}

        <Grid item xs={4}>
          <CarControlPanel
            index={index}
            onDelete={onDelete}
            toggleEdit={toggleEdit}
          />
        </Grid>
      </Grid>
    </ListItem>
  );
};

export default Car;
