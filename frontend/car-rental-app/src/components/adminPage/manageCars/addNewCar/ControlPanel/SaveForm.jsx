import React from "react";
import { Box, Button } from "@material-ui/core";
import { Save } from "@material-ui/icons";

const SaveForm = ({ submit }) => {
  return (
    <div>
      <Box>
        <Button
          variant="contained"
          color="primary"
          size="normal"
          startIcon={<Save />}
          type="submit"
          onClick={submit}
        >
          Save
        </Button>
      </Box>
    </div>
  );
};

export default SaveForm;
