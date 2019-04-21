'use strict'

const axios = require("axios");

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef UserSession
 * @property {[string]} accessToken
 * @property {[string]} tokenType
 * @property {[string]} expiresIn
 * @property {[string]} refreshToken
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
 * @headers {string} 200.X-Expires-After -  date in UTC when token expires
 * @security JWT
 */
module.exports = function (req, res) {
  const id = req.params.userId

  return axios.get('http://osspmgmt-spring-boot:8080/users/' + id)
    .then(function (response) {
      return res.status(200).send(response.data)
    })
    .catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
