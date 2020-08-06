import React from "react";
import { Grid, Typography, Button, TextField } from "@material-ui/core";
import { useDispatch } from "react-redux";

const ChangeSettingsForm = ({
  changeItem,
  type,
  onSubmit,
  onPasswordChange,
  onItemchange,
  password,
  item,
}) => {
  const dispatch = useDispatch();
  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        onSubmit();
      }}
    >
      <Grid
        style={{ height: "40vh" }}
        container
        direction="column"
        justify="center"
        alignItems="center"
      >
        <Typography>Change {changeItem}</Typography>
        <TextField
          onChange={(event) => onPasswordChange(event.target.value)}
          value={password}
          margin="normal"
          variant="outlined"
          style={{ width: "60ch" }}
          label="password"
          type="password"
          required
        />
        <TextField
          onChange={(event) => onItemchange(event.target.value)}
          value={item}
          margin="normal"
          variant="outlined"
          style={{ width: "60ch" }}
          label={`new-${changeItem}`}
          type={type}
          required
        />
        <Button type="submit">Change</Button>
      </Grid>
    </form>
  );
};

export default ChangeSettingsForm;
