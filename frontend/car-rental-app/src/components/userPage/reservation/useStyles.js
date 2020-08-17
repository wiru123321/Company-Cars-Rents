import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    marginTop: "2%",
    height: "800px",
    padding: "8px",
  },
  btnPanel: {
    marginTop: "2%",
    justifyContent: "space-evenly",
  },
  borderedBox: {
    padding: "12px",
    marginTop: "2%",
  },
  listRender: {
    minHeight: "20vh",
    padding: "8px",
  },
}));
export default useStyles;
