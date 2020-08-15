import React from "react";
import { Box, Button } from "@material-ui/core";

const PhotoForm = ({ submitImage, handlePhotoChange }) => {
  return (
    <div>
      <Box display="flex" justifyContent="center" m={1} p={1}>
        <form onSubmit={submitImage}>
          <input type="file" required onChange={handlePhotoChange} />
          <Button variant="contained" color="primary" type="submit">
            Upload Photo
          </Button>
        </form>
      </Box>
    </div>
  );
};

export default PhotoForm;
