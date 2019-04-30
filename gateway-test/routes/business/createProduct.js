"use strict";

const axios = require("axios");

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

function createBusiness (product, res) {
  return axios.post("http://osspmgmt-spring-boot:8080/business/products/create", product)
    .then(function (response) {
      return res.status(200).send(response.data)
    }).catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}

/**
 * @route POST /business/products/create
 * @param {ProductRequest.model} ProductRequest.body.required - the new Product request
 * @group business - get products for a listed business
 * @returns {Product.model} 200 - Returns product model
 * @returns {ErrorResponse.model} default - Product could not be created
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
