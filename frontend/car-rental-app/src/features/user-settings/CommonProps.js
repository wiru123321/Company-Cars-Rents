export const commonInitialStateProps = {
  password: "",
  responseMessage: "",
  isResultOk: false,
  isSubmit: false,
  showResult: false,
};

export const commonReducers = {
  setPassword: (state, action) => {
    state.password = action.payload;
  },

  setResponseMessage: (state, action) => {
    state.responseMessage = action.payload;
  },

  setResultStatus: (state, action) => {
    state.isResultOk = action.payload;
  },

  toggleSubmit: (state, action) => {
    state.isSubmit = action.payload;
  },

  displayResults: (state) => {
    state.showResult = true;
  },

  stopDisplayingResults: (state) => {
    state.showResult = false;
  },
};
