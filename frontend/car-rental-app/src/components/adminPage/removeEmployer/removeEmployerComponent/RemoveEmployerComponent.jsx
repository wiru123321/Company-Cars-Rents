import React from "react";
import { Container, Card, CardContent, Typography } from "@material-ui/core";

const RemoveEmployerComponent = ({ employer }) => {
  const { name, surname, number, email } = employer;
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
          {email}
        </Typography>
        <Typography color="textSecondary">{number}</Typography>
      </CardContent>
    </Card>
  );
};

export default RemoveEmployerComponent;
