"use strict";

const axios = requrie("axios");

/*
 * View a list of products for a given business
 */

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef BusinessRequest
 * @property {{SuperAwesome Business Name}} name - business name
 * @property {{1 Candycane Lane North Pole, AE}} location - business address
 * @property {{3}} userId - business owner userId
 */

/**
 * @typedef Business
 * @property {{SuperAwesome Business Name}} name - business name
 * @property {{uniqueID}} id - business ID
 * @property {{1 Candycane Lane North Pole, AE}} location - business address
 * @property {{3}} userId - business owner userId
 */

/**
 * @route POST /business/create
 * @group business - create a new business
 * @param {BusinessRequest.model} BusinessRequest.body.required - the new business request
 * @returns {Business.model} 200 - return the new business
 * @returns {ErrorResponse.model} default - HTTPErr - Product (list) not found
 * @security JWT
 */

module.exports = function (req, res) {
  const businessRequest = {
    'name': req.body.name,
    'location': req.body.location,
    'userId': req.body.userId
  }

  createBusiness(businessRequest, res)
}

function createBusiness (business, res) {
  return axios.post("http://osspmgmt-spring-boot:8080/business/create", business)
    .then(function (response) {
      return res.status(200).send(response.data)
    }).catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
