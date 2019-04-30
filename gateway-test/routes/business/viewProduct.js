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
 * @typedef Product
 * @property {{DoubleCheeseBurger}} productname - product name
 * @property {{uniqueID}} product_id - product ID
 * @property {{$10.00}} price - price
 * @property {{2}} quantity - quantity of the product
 */

/**
 * @route GET /business/{businessId}/product
 * @group business - get products for a listed business
 * @param {int} id.query.required - The ID of the product
 * @returns {Product.model} 200 - return a List of Products
 * @returns {ErrorResponse.model} default - HTTPErr - Product (list) not found
 * @security JWT
 */

module.exports = function (req, res) {
  return axios.get("http://osspmgmt-spring-boot:8080/business/" + req.query.id + "/products")
    .then(function (response) {
      return res.status(200).send(response.data)
    }).catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
};
