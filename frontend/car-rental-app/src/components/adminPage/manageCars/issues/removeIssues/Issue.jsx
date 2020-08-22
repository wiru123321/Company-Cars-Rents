import React from "react";
import {
  Grid,
  Paper,
  Typography,
  Button,
  makeStyles,
  Divider,
} from "@material-ui/core";
import DoneIcon from "@material-ui/icons/Done";

const useStyles = makeStyles({
  paper: {
    marginTop: "1%",
    padding: "8px",
    wordWrap: "break-word",
  },
});

const Issue = ({ issue, handleFaultDelete }) => {
  const classes = useStyles();

  const onCheck = () => {
    handleFaultDelete(issue);
  };

  return (
    <Paper className={classes.paper}>
      <Grid container justify="space-evenly" alignItems="center">
        <Grid item xs={8}>
          <Typography color="secondary">{issue.description}</Typography>
          <Divider orientation="vertical" flexItem />
        </Grid>

        <Grid item xs={2}>
          <Typography color="primary">
            {issue.faultDate.slice(0, 10)}
          </Typography>
        </Grid>
        <Grid item xs={2}>
          <Button
            onClick={onCheck}
            color="secondary"
            startIcon={<DoneIcon />}
          ></Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default Issue;
