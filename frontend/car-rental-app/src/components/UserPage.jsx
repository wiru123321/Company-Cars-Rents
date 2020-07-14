import React from "react";
import UserNavbar from "./userNavbar/UserNavbar";
import CarsList from "./carsListing/CarsList";
const UserPage = () => {
  return (
    <div>
      <UserNavbar />
      <CarsList />
    </div>
  );
};
export default UserPage;
