"use strict";

let express = require("express");
let router = express.Router();
let request = require("request");

router.param(['username', 'pwd'], (req, res, next, value) => {
  console.log('Param Value: ', value);
  next()
})

router.get('/db/login/', (req, res, next) => {
  request("http://127.0.0.1:5000/register?redirect=", async function (err, response, body) {
    if (!err && response.statusCode === 200) {
      let data = JSON.parse(body);
      if (data.username != null)
        res.render("/microservice/login", data);
      /*
       * render /microservice/login so that microservice can read its data
       */
    }
  })
})

router.post('/db/register', function (req, res, next) {

})
