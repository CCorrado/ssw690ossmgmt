'use strict';

const OAuthError = require('../errors/OAuthError')
const jwt = require('jsonwebtoken')

module.exports = function (req, res, next) {
  if (req.headers.hasOwnProperty('authorization') === false) {
    throw OAuthError.makeAccessDeniedError()
  }

  const [type, token] = req.headers.authorization.split(' ')

  if (type !== 'Bearer') {
    throw OAuthError.makeAccessDeniedError()
  }

  let decodedToken

  try {
    decodedToken = jwt.verify(token, 'ossp')
  } catch (err) {
    throw OAuthError.makeAccessDeniedError()
  }

  if (req.hasOwnProperty('ossp') === false) {
    req.ossp = {}
  }

  req.ossp.userId = decodedToken.sub

  next()
}
