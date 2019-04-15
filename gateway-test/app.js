'use strict'

const express = require('express')
const bodyParser = require('body-parser')

const app = express()
const expressSwagger = require('express-swagger-generator')(app);
const port = process.env.HTTP_PORT || 3000

// Parse JSON request bodies
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}));

// Set up routes
app.use(require('./routes'))

// Set up error handler
app.use(require('./middleware/errorHandler'))

const options = {
  swaggerDefinition: {
    info: {
      description: 'This is the API Documentation for the OSSP Project',
      title: 'Swagger',
      version: '1.0.0',
    },
    host: 'localhost:3000',
    basePath: '/',
    produces: [
      "application/json",
      "application/xml"
    ],
    schemes: ['http', 'https'],
    securityDefinitions: {
      JWT: {
        type: 'apiKey',
        in: 'header',
        name: 'Authorization',
        description: "",
      }
    }
  },
  basedir: __dirname, //app absolute path
  files: ['./routes/**/*.js'] //Path to the API handle folder
};

expressSwagger(options)

app.listen(port, () => {
  console.log(`Running on port ${port}`)
})
