import React from "react";
import { useSelector, useDispatch } from "react-redux";
import AlertMessage from "../messages/AlertMessage";
import { selectAll, resetUpdate } from "../../../features/rents/rentsSlice";

const RentAlert = () => {
  const dispatch = useDispatch();
  const { didUpdate, didUpdateSuccess } = useSelector(selectAll);

  const resetUpdateState = () => {
    dispatch(resetUpdate());
  };

  return (
    <AlertMessage
      isActive={didUpdate}
      isOk={didUpdateSuccess}
      successMessage="Successfull."
      errorMessage="Failed."
      resetUpdateState={resetUpdateState}
    />
  );
};

export default RentAlert;
