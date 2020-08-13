import React from "react";
import AlertMessage from "../../messages/AlertMessage";
import { useSelector, useDispatch } from "react-redux";
import {
  selectAll,
  resetUpdate,
} from "../../../../features/employees-management/employeesManagerSlice";

const EmployeeAddedAlert = () => {
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
      errorMessage="Failed to update user."
      resetUpdateState={resetUpdateState}
    />
  );
};

export default EmployeeAddedAlert;
