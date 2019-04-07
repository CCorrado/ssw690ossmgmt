"use strict"

let express = require("express");
let router = express.Router();
let request = require("request");

router.post("/frontend/register", async function (req, res, next) {
  req = request("http://127.0.0.1:5000/register?redirect=", function (err, response, body) {
    if (!err && response.statusCode == 200) {
      var data = JSON.parse(body);
      if (data.username != null)
        res.render("frontend/register", data);
    }
  });
});
