import React from "react";
import { Container, List, ListItem } from "@material-ui/core";
const CarInfo = ({ car }) => {
  const {
    capacityOfPeople,
    licensePlate,
    mileage,
    modelDTO,
    typeDTO,
    enginePower,
    productionYear,
  } = car;
  const { markDTO, name } = modelDTO;

  return (
    <Container>
      <h1 style={{ fontSize: "1.3rem" }}>
        {markDTO.name} {name}
      </h1>
      <h2>{licensePlate}</h2>
      <List>
        <ListItem>
          <label>Year of production: {productionYear}</label>
        </ListItem>
        <ListItem>
          <label>Mileage: {mileage}</label>
        </ListItem>
        <ListItem>
          <label>Hp: {enginePower}</label>
        </ListItem>
        <ListItem>
          <label>Capacity: {capacityOfPeople}</label>
        </ListItem>
        <ListItem>
          <label>Type: {typeDTO.name}</label>
        </ListItem>
      </List>
    </Container>
  );
};

export default CarInfo;
