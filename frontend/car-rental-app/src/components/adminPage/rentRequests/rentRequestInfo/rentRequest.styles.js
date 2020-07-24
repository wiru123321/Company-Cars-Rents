import { makeStyles } from "@material-ui/core/styles";
import styled from "styled-components";
export const rentRequestStyles = makeStyles((theme) => ({
  paper: {
    padding: 8,
    marginTop: "2%",
  },
  buttons: {
    marginTop: "2%",
  },
  control: {
    marginTop: "2%",
  },
}));

export const TextArea = styled.textarea`
  width: 100%;
  height: 10vh;
  resize: none;
  padding: 8px;
`;
