import React, { useState, useEffect } from "react";
import { Button, Dialog } from "@material-ui/core";
import RequestedCarInfo from "../rentRequests/rentRequestInfo/RequestedCarInfo";
import ModalContent from "./DialogContent";

const CarModal = ({ car, fetchCars }) => {
  const [open, setOpen] = useState(false);
  const [currentCar, setCurrentCar] = useState(car);
  const [cars, setCars] = useState([]);
  const [currentIndex, setActive] = useState("");
  const handleOpen = () => {
    setOpen(true);
  };

  useEffect(() => {
    fetchCars(setCars);
  }, []);

  const handleClose = () => {
    setOpen(false);
  };

  const changeCurrentCar = (car) => {
    setCurrentCar(car);
  };

  return (
    <>
      <RequestedCarInfo carDTO={currentCar} />
      <Button
        style={{ width: "100%" }}
        onClick={handleOpen}
        variant="outlined"
        color="primary"
      >
        Change car
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <ModalContent
          cars={cars}
          changeCurrentCar={changeCurrentCar}
          handleClose={handleClose}
          setActive={setActive}
          currentIndex={currentIndex}
        />
      </Dialog>
    </>
  );
};

export default CarModal;
