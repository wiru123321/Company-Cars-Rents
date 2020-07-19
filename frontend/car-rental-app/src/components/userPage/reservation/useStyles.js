import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    marginTop: "2%",
    height: "800px",
    padding: "8px",
  },
  leftColumn: {
    width: "40%",
  },
  rightColumn: {
    width: "60%",
  },
  btnPanel: {
    marginTop: "2%",
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-evenly",
  },
  borderedBox: {
    padding: "12px",
    marginTop: "2%",
  },
}));
export default useStyles;
