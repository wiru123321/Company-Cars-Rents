FROM node:13.12.0-alpine

RUN adduser -D -u 8787 carRentalFront

WORKDIR /usr/app

COPY package.json .

# v1.3
RUN npm install && \
    npm install -g react-scripts serve && \
    npm install @material-ui/core && \
    npm install @material-ui/icons && \
    npm install react-redux && \
    npm install @reduxjs/toolkit && \
    npm install axios
    
COPY . .

RUN react-scripts build

USER carRentalFront

EXPOSE 5000

CMD ["serve","-s", "build"]

RUN rm -r build && \
    rm -r node_modules

# /bin/sh Work
