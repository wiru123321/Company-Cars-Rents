import React from "react";
import { ListGroup, Container } from "react-bootstrap";
const CarInfo = ({ car }) => {
  const { mark, model, licensePlate, capacity, mileage, year, hp } = car;
  return (
    <Container>
      <h1 style={{ fontSize: "1.3rem" }}>
        <a href="#">
          {mark} {model}
        </a>
      </h1>
      <h2 style={{ fontSize: "1.1rem" }}>{licensePlate}</h2>
      <ListGroup variant="flush">
        <ListGroup.Item>Year of production: {year}</ListGroup.Item>
        <ListGroup.Item>Mileage: {mileage}</ListGroup.Item>
        <ListGroup.Item>Hp: {hp}</ListGroup.Item>
        <ListGroup.Item>Capacity: {capacity}</ListGroup.Item>
      </ListGroup>
    </Container>
  );
};

export default CarInfo;
