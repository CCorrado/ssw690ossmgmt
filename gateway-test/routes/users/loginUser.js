'use strict'
const axios = require("axios");
const jwt = require("jsonwebtoken");

/**
 * @typedef ErrorResponse
 * @property {[integer]} statusCode
 * @property {[string]} message
 */

/**
 * @typedef LoginRequest
 * @property {{tester1@test.com}} username - username (email)
 * @property {{Test1234}} password - user's password
 */

/**
 * @route POST /users/login
 * @group users - Login existing user
 * @param {LoginRequest.model} LoginRequest.body.required - the new login request
 * @returns {LoginResponse.model} 200 - The User's information and session
 * @returns {ErrorResponse.model}  default - HttpError - User not found
 */
module.exports = function (req, res) {
    const options = {}
    const userRequest = {
        'username': req.body.username,
        'password': req.body.password
    }

    const userToken = {
        "access_token": jwt.sign({}, 'ossp', Object.assign(options, {expiresIn: '2 hours'})),
        "token_type": "bearer",
        "expires_in": 60 * 60 * 24,
        "refresh_token": jwt.sign({}, 'ossp', Object.assign(options, {expiresIn: '2 days'}))
    }

    const newRequest = {
        "username": userRequest.username,
        "password": userRequest.password,
        "accessToken": userToken.access_token,
        "tokenType": userToken.token_type,
        "expiresIn": userToken.expires_in,
        "refreshToken": userToken.refresh_token
    }

    getUserObjectIfExists(res, newRequest)
}

function getUserObjectIfExists(res, user) {
    return axios.get('http://osspmgmt-spring-boot:8080/users?username=' + user.username)
        .then(function (response) {
            return hashUserData(res, response.data, user.password, user)
        })
        .catch(function (error) {
            return res.status(error.response.status).send(error.response.data)
        })
}

function hashUserData(res, user, pwAttempted, userToken) {
    user.pwAttempted = pwAttempted
    return axios.post('http://ossp-microservices:5000/auth/signin', user)
        .then(function (response) {
            response.data.accessToken = userToken.accessToken
            response.data.tokenType = userToken.tokenType
            response.data.expiresIn = userToken.expiresIn
            response.data.refreshToken = userToken.refreshToken
            return sendLoginUser(res, response.data)
        })
        .catch(function (error) {
            return res.status(error.response.status).send(error.response.data)
        })
}

function sendLoginUser(res, user) {
    //Save this user to the database
    return axios.post('http://osspmgmt-spring-boot:8080/users/newSession', user)
        .then(function (response) {
            return res.status(200).send(response.data)
        })
        .catch(function (error) {
            return res.status(error.response.status).send(error.response.data)
        })
}
