"use strict";
const express = require('express');
const router = express.Router();
const request = require('request');
/*
 * Design a router for every request from different User(Frontend, Microservice, Database)
 * 1 router for all and judging them by their body is not sufficient.
 * e.g. /login/frontend /login/microservice /login/database
 * All port should be RESTful so that the complexity of the implementation of proxy can be reduced.
 */

/*
 * Frontend -> proxy -> Microservice; e.g. frontend sends user login data to mic
 * Microservice -> proxy -> Database; e.g. mic rises a authorification of login data from database. (OAuth)
 * Database -> proxy -> Microservice; e.g. Database feeds back Auth results.
 * Microservice -> proxy -> Frontend; e.g. Microservices informs frontend login successful.
 */
router.post('/login', (req, res, next) => {
  res.send(req.body);
  request({
    url: 'http://127.0.0.1:5000/login',
    method: POST,
    json: true,
    head: {
      "content-type": "application/json",
    },
    body: JSON.stringify(req.body),
  }, async (err, response, body) => {
    res.send(body);
  })
})

module.exports = router;
