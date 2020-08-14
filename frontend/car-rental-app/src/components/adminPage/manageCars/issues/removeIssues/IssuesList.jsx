import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Grid } from "@material-ui/core";
import { selectAll } from "../../../../../features/cars-manager/carsManagerSlice";
import {
  fetchFaultsForCar,
  deleteFault,
} from "../../../../../services/FaultsService";
import Issue from "./Issue";

import NotFoundInfo from "../../../messages/NotFoundMessage";

const IssuesList = () => {
  const dispatch = useDispatch();
  const { currentCar } = useSelector(selectAll);
  const [issues, setIssues] = useState([]);

  useEffect(() => {
    fetchFaultsForCar(currentCar.licensePlate, setIssues);
  }, []);

  const handleFaultDelete = (issue) => {
    deleteFault(issue, currentCar.licensePlate, setIssues);
  };

  return (
    <Grid style={{ marginTop: "2%" }}>
      {issues.length > 0 ? (
        issues.map((issue, index) => (
          <Issue
            key={index + issue.description}
            handleFaultDelete={handleFaultDelete}
            issue={issue}
          />
        ))
      ) : (
        <Grid container justify="center">
          <NotFoundInfo>No issues</NotFoundInfo>
        </Grid>
      )}
    </Grid>
  );
};

export default IssuesList;
