import React, { useState, useEffect } from "react";
import { Container, Button, Box } from "@material-ui/core";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import { useSelector, useDispatch } from "react-redux";
import EmployerInfo from "./removeEmployerComponent/RemoveEmployerComponent";
import {
  selectAllUsers,
  fetchAllUsers,
} from "../../../features/add-employees/addEmployeeSlice";

const json = [
  {
    name: "Wojciech",
    surname: "Waleszczyk",
    number: "123-456-789",
    email: "Wojciech@name.com",
  },
  {
    name: "Kamil",
    surname: "Susek",
    number: "123-456-789",
    email: "Kamil@name.com",
  },
  {
    name: "Bartosz",
    surname: "Czapiewski",
    number: "123-456-789",
    email: "Bartosz@name.com",
  },
  {
    name: "Rafał",
    surname: "Paprota",
    number: "123-456-789",
    email: "Rafał@name.com",
  },
];

const RemoveEmployer = () => {
  //const [employers, setEmployers] = useState(json);
  const dispatch = useDispatch();
  const employers = useSelector(selectAllUsers);

  useEffect(() => {
    dispatch(fetchAllUsers());
  }, []);

  return (
    <Container flex style={{ width: "100vw" }}>
      {employers.map((employer, index) => {
        return (
          <Container key={index}>
            <Box
              display="flex"
              justifyContent="center"
              m={1}
              p={1}
              style={{ width: "80vw" }}
            >
              <Box style={{ width: "40vw" }}>
                <EmployerInfo employer={employer} />
              </Box>
              <Box
                style={{ marginTop: "4vh", marginLeft: "5vw", width: "40vw" }}
              >
                <Button
                  variant="contained"
                  color="primary"
                  startIcon={<EditIcon />}
                >
                  Edit
                </Button>
                <Button
                  variant="contained"
                  color="secondary"
                  startIcon={<DeleteIcon />}
                  style={{ marginLeft: "2vw" }}
                >
                  Remove
                </Button>
              </Box>
            </Box>
          </Container>
        );
      })}
    </Container>
  );
};

export default RemoveEmployer;
