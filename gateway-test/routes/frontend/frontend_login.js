"use strict"

let express = require("express");
let router = express.Router();
let request = require("request");

router.get('/login', (req, res, next) => {
  request("http://127.0.0.1:8080", async (err, response, databody) => {
    if (!err && response.statusCode === 200) {
      request({
        url: "http://127.0.0.1:5000/microservice_login",
        method: "POST",
        json: true,
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(databody),
      }, async (micro_err, micro_res, micro_body) => {
        if (!micro_err && micro_res.statusCode === 200) {
          res.send(micro_body);
        } else if (micro_res.statusCode === 404) {
          res.end(404);
        }
      });

    } else if (response.statusCode === 404) {
      res.end(404);
    }
  })
})

module.exports = router;
