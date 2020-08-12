import React from "react";
import AlertMessage from "../../messages/AlertMessage";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAll,
  resetUpdate,
} from "../../../../features/car-management/carManagerSlice";

const CarsUpdateAlert = () => {
  const dispatch = useDispatch();
  const { didUpdate, didUpdateSuccess } = useSelector(selectAll);

  const resetUpdateState = () => {
    dispatch(resetUpdate());
  };
  return (
    <AlertMessage
      isActive={didUpdate}
      isOk={didUpdateSuccess}
      successMessage="Successfully updated."
      errorMessage="Failed to update car."
      resetUpdateState={resetUpdateState}
    />
  );
};

export default CarsUpdateAlert;
