import React, { useEffect } from "react";
import PropTypes from "prop-types";
import SwipeableViews from "react-swipeable-views";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import {
  Grid,
  Button,
  Box,
  ListItem,
  List,
  Container,
  AppBar,
  Tabs,
  Tab,
  Typography,
} from "@material-ui/core";
import { useSelector, useDispatch } from "react-redux";
import {
  selectReservation,
  fetchReservation,
  fetchCarImage,
  selectImg,
  updateCar,
  fetchHistoryReservation,
  selectHistoryReservation,
  selectRequestReservation,
  fetchRequestReservation,
} from "../../../features/your-cars/yourCarsSlice";
import YourReservationForm from "./YourReservationForm";
function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    "aria-controls": `full-width-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: theme.palette.background.paper,
    width: 500,
  },
}));

// TODO Fetch users cars from api.

const YourCarsList = () => {
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);
  const active = false;

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChangeIndex = (index) => {
    setValue(index);
  };
  const dispatch = useDispatch();
  const reservations = useSelector(selectReservation);
  const historyReservations = useSelector(selectHistoryReservation);
  const requestReservation = useSelector(selectRequestReservation);
  const img = useSelector(selectImg);
  useEffect(() => {
    dispatch(fetchReservation());
    dispatch(fetchHistoryReservation());
    dispatch(fetchRequestReservation());
  }, []);
  return (
    <Container
      style={{
        minHeight: "80vh",
        height: "auto",
        height: "100%",
      }}
    >
      <AppBar position="static" color="default">
        <Tabs
          value={value}
          onChange={handleChange}
          indicatorColor="primary"
          textColor="primary"
          variant="fullWidth"
          aria-label="full width tabs example"
        >
          <Tab label="Your reservations" {...a11yProps(0)} />
          <Tab label="History" {...a11yProps(1)} />
          <Tab label="Pending reservations" {...a11yProps(2)} />
        </Tabs>
      </AppBar>
      <SwipeableViews
        axis={theme.direction === "rtl" ? "x-reverse" : "x"}
        index={value}
        onChangeIndex={handleChangeIndex}
      >
        <TabPanel value={value} index={0} dir={theme.direction}>
          <YourReservationForm reservations={reservations} isActive="true" />
        </TabPanel>
        <TabPanel value={value} index={1} dir={theme.direction}>
          <YourReservationForm
            reservations={historyReservations}
            isActive={active}
          />
        </TabPanel>
        <TabPanel value={value} index={2} dir={theme.direction}>
          <YourReservationForm
            reservations={requestReservation}
            isActive={active}
          />
        </TabPanel>
      </SwipeableViews>
    </Container>
  );
};

export default YourCarsList;
