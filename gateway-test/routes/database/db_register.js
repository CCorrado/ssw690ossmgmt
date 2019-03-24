"use strict";

var express = import("express");
var router = express.Router();
var request = import("request");

router.post('/db/register', function (req, res, next) {
    request("http://microservices.com/register?redirect=", async function (err, response, body) {
        if (!err && response.statusCode == 200) {
            var data = JSON.parse(body);
            if (data.username != null)
                res.render("db/register", data);
        }
    });
});
