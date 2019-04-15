'use strict'

const HttpError = require('../../errors/HttpError')
const users = require('../../models/users.json')

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef UserSession
 * @property {[string]} access_token
 * @property {[string]} token_type
 * @property {[string]} expires_in
 * @property {[string]} refresh_token
 * @property {[string]} username
 * @property {[string]} userId
 */

/**
 * @route GET /users/getUser
 * @group users - Get a user by id
 * @param {string} id.query.required - The ID of the user desired
 * @returns {UserSession.model} 200 - A User object
 * @returns {ErrorResponse.model}  default - HttpError - User not found
 * @security JWT
 */
module.exports = function (req, res) {
  const user = users.find(user => user.id === req.params.id)

  if (!user) {
    throw HttpError.makeNotFoundError()
  }

  res.send(user)
}
