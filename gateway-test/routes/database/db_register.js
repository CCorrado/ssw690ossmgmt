"use strict";

var express = require("express");
var router = express.Router();
var request = require("request");

router.param(['username', 'pwd'], (req, res, next, value) => {
    console.log('Param Value: ', value);
    next();
});

router.get('/db/login/', (req, res, next) {
    request("http://microservices.com/register?redirect=", async function (err, response, body) {
        if (!err && response.statusCode == 200) {
            var data = JSON.parse(body);
            if (data.username != null)
                res.render("/microservice/login", data);
            /*
             * render /microservice/login so that microservice can read its data
             */
        }
    });
});

router.post('/db/register', function (req, res, next) {
    
});
