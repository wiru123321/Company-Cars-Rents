import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    textAlign: "center",
  },
  paper: {
    minWidth: "40vw",
    padding: "8px",
  },
  content: {
    marginTop: "8%",
    minWidth: "30vw",
    minHeight: "60vh",
    textAlign: "center",
  },
  textArea: {
    marginTop: "1%",
    width: "60ch",
  },
  selectArea: {
    marginTop: "1%",
    width: "55ch",
  },
  buttonArea: {
    marginTop: "5%",
  },
}));

export default useStyles;
