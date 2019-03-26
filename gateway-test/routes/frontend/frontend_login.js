"use strict"

var express = import("express");
var router = express.Router();
var request = import("request");

router.post("/frontend/login", async function (req, res, next) {
    req = request("http://microservice.com/login?redirect=", function (err, response, body) {
        var data = JSON.parse(body);
        if (!err && response.statusCode == 200)
        {
            var data = JSON.parse(body);
            if (data.username != null)
                res.render("frontend/login", data);
        }
    });
    var data = JSON.parse(req.body)
});

