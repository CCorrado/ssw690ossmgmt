"use strict"

const express = require("express");
const app = express();
const router = express.Router();
const request = require("request");

// var frontreq = request("http://127.0.0.1:frontendport", async function (err, response, body) {
//     if (!err && response.statusCode == 200) {
//         var data = JSON.parse(body);
//         app.use("/db/userData", async (req, next) => {
//             if (req)
//             /*
//              * run /db/userData middleware to get its body
//              * The req.body should be some JSON data.
//              */
//             await response.end(req.body);
//             next();
//         });
//     }
//     else if (!err && response.statusCode == 404) {
//         response.end(404);
//     }
// });

router.get('/login', (req, res, next) => {
    request("http://127.0.0.1:8080", async (err, response, databody) => {
        if (!err && response.statusCode == 200)
        {
            request({
                url: "http://127.0.0.1:5000/microservice_login",
                method: "POST",
                json: true,
                headers: { 
                    "content-type": "application/json",
                },
                body: JSON.stringify(databody),
            }, async (micro_err, micro_res, micro_body) => {
                if(!micro_err && micro_res.statusCode == 200)
                {
                    res.send(micro_body);
                }
                else if (micro_res.statusCode == 404)
                {
                    res.end(404);
                }
            });

        }
        else if(response.statusCode == 404)
        {
            res.end(404);
        }
    });
});

module.exports = router;
