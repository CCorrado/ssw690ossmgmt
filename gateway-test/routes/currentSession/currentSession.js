'use strict'

const HttpError = require('../../errors/HttpError')
const users = require('../../models/users')

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
 * Get a user's current profile
 * @route GET /currentSession
 * @group users - Get the current user's profile
 * @param {string} userId.query.required - username or email
 * @returns {UserSession.model} 200 - An Object containing the User
 * @returns {ErrorResponse.model}  default - HttpError - User not found
 * @headers {string} 200.X-Expires-After - 	date in UTC when token expires
 * @security JWT
 */
module.exports = function (req, res) {
  const id = req.ossp.userId

  const user = users.find(user => user.id === id)

  if (!user) {
    throw HttpError.makeNotFoundError()
  }

  res.send(user)
}
