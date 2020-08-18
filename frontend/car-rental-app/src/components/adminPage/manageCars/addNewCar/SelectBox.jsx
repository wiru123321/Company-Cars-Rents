import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));

export const SelectBox = ({
  SelectHandler,
  NameHandler,
  handleChange,
  handlerValue,
}) => {
  const classes = useStyles();

  return (
    <div>
      <FormControl className={classes.formControl}>
        <InputLabel id="demo-simple-select-label">{NameHandler}</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={handlerValue}
          onChange={handleChange}
        >
          {SelectHandler.map((select, index) => (
            <MenuItem value={select.name}>{select.name}</MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
};
