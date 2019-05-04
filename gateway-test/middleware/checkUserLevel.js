"use strict";

const OAuthError = require('../errors/OAuthError');

module.exports = function(req, res, next) {
    if (req.body.role === "User") {
        throw OAuthError.makeAccessDeniedError()
    }

    next();
}