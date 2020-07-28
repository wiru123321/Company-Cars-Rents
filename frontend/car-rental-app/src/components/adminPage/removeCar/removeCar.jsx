import React, { useState } from "react";
import CarInfo from "../../carsListing/CarInfo";
import CarImage from "../../carsListing/CarImage";
import CarControlPanel from "./CarControlPanel";
import { Container, List, ListItem } from "@material-ui/core";

// Temporary json, has to be removed when connected with Api.
const json = [
  {
    mark: "Audi",
    model: "RS7 Sportback",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://ocs-pl.oktawave.com/v1/AUTH_2887234e-384a-4873-8bc5-405211db13a2/autoblog/2019/09/Audi-RS7-3.jpg",
  },
  {
    mark: "Fiat",
    model: "Panda",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.autocentrum.pl/ac-file/gallery-photo/5dd3eae5583a0f08331eca84/fiat-panda.jpg",
  },
  {
    mark: "Mercedes",
    model: "AMG",
    licensePlate: "SL-1337",
    capacity: "5",
    mileage: "165000",
    year: "1995",
    hp: "245",
    src:
      "https://www.mercedes-benz.pl/passengercars/mercedes-benz-cars/models/a-class/hatchback-w177/amg/comparison-slider/_jcr_content/comparisonslider/par/comparisonslide_576434814/exteriorImage.MQ6.12.20191001221334.jpeg",
  },
];

const RemoveCar = () => {
  const [cars, setCars] = useState(json);

  return (
    <Container>
      <List>
        {cars.map((car, index) => {
          return (
            <ListItem key={index}>
              <CarImage src={car.src} />
              <CarInfo car={car} />
              <CarControlPanel />
            </ListItem>
          );
        })}
      </List>
    </Container>
  );
};

export default RemoveCar;
