"use strict"

var express = require("express");
var router = express.Router();
var request = require("request");

router.post("/frontend/register", async function (req, res, next) {
    req = request("http://microservice.com/register?redirect=", function (err, response, body) {
        if (!err && response.statusCode == 200) {
            var data = JSON.parse(body);
            if (data.username != null)
                res.render("frontend/register", data);
        }
    });
});