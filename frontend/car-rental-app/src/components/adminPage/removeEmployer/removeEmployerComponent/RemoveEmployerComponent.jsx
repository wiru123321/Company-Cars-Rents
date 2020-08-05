import React from "react";
import { Container, Card, CardContent, Typography } from "@material-ui/core";

const RemoveEmployerComponent = ({ employer }) => {
  const { name, surname, email, login, phoneNumber, roleDTO } = employer;
  return (
    <Card style={{ textAlign: "center" }}>
      <CardContent>
        <Typography gutterBottom variant="h5">
          {name} {surname}
        </Typography>
        <Typography
          variant="h5"
          component="h2"
          style={{ wordWrap: "break-word" }}
        >
          {login}
        </Typography>
        <Typography
          variant="h5"
          component="h2"
          style={{ wordWrap: "break-word" }}
        >
          {email}
        </Typography>
        <Typography
          variant="h5"
          component="h2"
          style={{ wordWrap: "break-word" }}
        >
          {phoneNumber}
        </Typography>
        <Typography
          variant="h5"
          component="h2"
          style={{ wordWrap: "break-word" }}
        >
          {roleDTO.name}
        </Typography>
      </CardContent>
    </Card>
  );
};

export default RemoveEmployerComponent;
