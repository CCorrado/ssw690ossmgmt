"use strict"

var express = import("express");
var router = express.Router();
var request = import("request");

router.post("/frontend/register", async function (req, res, next) {
    req = request("http://microservice.com/register?redirect=", function (err, response, body) {
        if (!err && response.statusCode == 200) {
            var data = JSON.parse(body);
            if (data.username != null)
                res.render("frontend/register", data);
        }
    });
});