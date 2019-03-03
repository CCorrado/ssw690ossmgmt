"use strict";

const jwt = require("jsonwebtoken");
const axios = requrie("axios");

/*
 * check the role of a user
 * if s/he is businesss
 * allow s/he to view his/her products.
 * else return 401
 */

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef viewProductsReq
 * @property {{DoubleCheeseBurger}} productname - product name
 * @property {{uniqueID}} product_id - product ID
 * @property {{$10.00}} price - price
 */

 /**
  * @typedef ProductSession
  * @property {{DoubleCheeseBurger}} productname - product name
  * @property {{uniqueID}} product_id - product ID
  * @property {{$10.00}} price - price
  * @property {{2}} quantity - quantity of the product
  */

  /**
   * @route GET /business/viewProduct
   * @group business - get a product
   * @param {int} id.query.required - The ID of the product
   * @returns {ProductSession.model} 200 - return a Product object
   * @returns {ErrorResponse.model} default - HTTPErr - Product (list) not found
   * @security JWT
   */

   module.exports = function (req, res) {
       return axios.get("http://osspmgmt-spring-boot:8080/business" + req.query.id)
       .then(function (response) {
           return res.status(200).send(response.data)
       }).catch(function (error) {
           return res.status(error.response.status).send(error.response.data)
       })
   };