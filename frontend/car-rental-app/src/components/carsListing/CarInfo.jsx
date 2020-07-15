import React from "react";
import { Container, List, ListItem, Link } from "@material-ui/core";

const CarInfo = ({ car }) => {
  const { mark, model, licensePlate, capacity, mileage, year, hp } = car;
  return (
    <Container>
      <h1 style={{ fontSize: "1.3rem" }}>
        <Link href="#">
          {mark} {model}
        </Link>
      </h1>
      <h2>{licensePlate}</h2>
      <List>
        <ListItem>
          <label>Year of production: {year}</label>
        </ListItem>
        <ListItem>
          <label>Mileage: {mileage}</label>
        </ListItem>
        <ListItem>
          <label>Hp: {hp}</label>
        </ListItem>
        <ListItem>
          <label>Capacity: {capacity}</label>
        </ListItem>
      </List>
    </Container>
  );
};

export default CarInfo;
