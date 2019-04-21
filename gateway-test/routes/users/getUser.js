'use strict'

const axios = require("axios");

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
  axios.get('http://osspmgmt-spring-boot:8080/users/' + req.params.id)
    .then(function (response) {
      console.log(response)
      return res.status(200).send(response.data)
    })
    .catch(function (error) {
      return Promise.reject(error.response);
    })
}
