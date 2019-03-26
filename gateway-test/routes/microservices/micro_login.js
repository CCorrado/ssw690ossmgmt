"use strict";
var express = require('express');
var router = express.Router();
var request = require('request');
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
router.post('/micro/login', async function (req, res, next) {
    var data = JSON.parse(req.body),
    ServerCookie = req.headers.cookie,
    nres = res;

    if (true)
    {
        /*
        * Requiring head/body to identify
        * E.G. head.something == requiring data from database
        * 
        */
        request("http://database.com/login", function (err, response, body) {
            
        });
    }

    request("http://frontend.com/login", function (err, response, body) {
            if (!err && response.statusCode == 200)
            {
                var data = JSON.parse(body);
                if (data.username != null)
                    res.render("micro/login", data);
            }
    });
    next();
});


module.exports = router;
