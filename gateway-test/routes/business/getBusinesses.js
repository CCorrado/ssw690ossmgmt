'use strict'

const axios = require("axios");

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef Business
 * @property {[string]} name -- The Name of the business
 * @property {[string]} id -- The ID of the business
 * @property {[array]} products -- The products for sale by the business
 */

/**
 * @route GET /business/getAll
 * @group business - Get a list of businesses available
 * @returns {Business.model} 200 - A list of business objects
 * @returns {ErrorResponse.model}  default - HttpError - User not found
 * @security JWT
 */
module.exports = function (req, res) {
  return axios.get('http://osspmgmt-spring-boot:8080/business/getAll')
    .then(function (response) {
      return res.status(200).send(response.data)
    })
    .catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
