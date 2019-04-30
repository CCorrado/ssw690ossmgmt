"use strict";

const axios = requrie("axios");

/*
 * Create a new product for a given business
 */

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef ProductRequest
 * @property {{DoubleCheeseBurger}} name - product name
 * @property {{$10.00}} price - price
 * @property {{10}} quantity - total quantity of the product
 */

/**
 * @typedef Product
 * @property {{DoubleCheeseBurger}} name - The name of the product
 * @property {{uniqueID}} id - The ID of the product
 * @property {{$10.00}} price - price
 * @property {{10}} quantity - quantity of the product
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
  const productRequest = {
    'name': req.body.name,
    'price': req.body.price,
    'quantity': req.body.quantity
  }

  createBusiness(productRequest, res)
}

function createBusiness (product, res) {
  return axios.post("http://osspmgmt-spring-boot:8080/business/products/create", product)
    .then(function (response) {
      return res.status(200).send(response.data)
    }).catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
