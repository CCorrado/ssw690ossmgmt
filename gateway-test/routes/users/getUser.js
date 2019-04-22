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
 * @route GET /users/getUser
 * @group users - Get a user by id
 * @param {int} id.query.required - The ID of the user desired
 * @returns {UserSession.model} 200 - A User object
 * @returns {ErrorResponse.model}  default - HttpError - User not found
 * @security JWT
 */
module.exports = function (req, res) {
  return axios.get('http://osspmgmt-spring-boot:8080/users/' + req.query.id)
    .then(function (response) {
      return res.status(200).send(response.data)
    })
    .catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
