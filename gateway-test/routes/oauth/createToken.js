'use strict'

const clients = require('../../models/clients')
const OAuthError = require('../../errors/OAuthError')
const jwt = require('jsonwebtoken')
const axios = require("axios");

/**
 * @typedef TokenRequest
 * @property {{tester1@test.com}} email - username or email
 * @property {{Test1234}} password - user's password
 * @property {{uniqueID}} client_id - user's client id
 * @property {{uniqueSecret}} client_secret - user's secret
 * @property {{password}} grant_type - Grant type that is supported (i.e. password, refresh_token)
 */

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
 */

/**
 * Create a New Token
 * @route POST /oauth/token
 * @param {TokenRequest.model} TokenRequest.body.required - the new token request
 * @group users - Create a new Session Token for a user
 * @returns {UserSession.model} 200 - An Object containing the user's data
 * @returns {ErrorResponse.model}  default - OAuthError - Variable depending on request.
 * @headers {string} 200.X-Expires-After -  date in UTC when token expires
 */
module.exports = (req, res) => {
  if (req.body.hasOwnProperty('grant_type') === false) {
    throw OAuthError.makeInvalidRequestError('grant_type')
  }

  if (req.body.grant_type === 'password') {
    return handlePasswordGrant(req, res)
  }

  if (req.body.grant_type === 'refresh_token') {
    return handleRefreshToken(req, res)
  }

  throw OAuthError.makeInvalidGrantError()
}

function handlePasswordGrant (req, res) {
  switch (true) {
    case req.body.hasOwnProperty('client_id') === false:
      throw OAuthError.makeInvalidRequestError('client_id')
    case clients.includes(client =>
      client.clientId === req.body.client_id &&
      client.clientSecret === req.body.client_secret
    ):
      throw OAuthError.makeInvalidClientError()
    case req.body.hasOwnProperty('username') === false:
      throw OAuthError.makeInvalidRequestError('username')
    case req.body.hasOwnProperty('password') === false:
      throw OAuthError.makeInvalidRequestError('password')
  }

  axios.get('http://osspmgmt-spring-boot:8080/users/' + req.body.username)
    .then(function (response) {
      const user = response.body
      if (!user) {
        throw OAuthError.makeInvalidCredentialsError()
      }
      if (user.password !== req.body.password) {
        console.log("Bad password :)")
        throw OAuthError.makeInvalidCredentialsError()
      }
      sendToken(res, user.id)
    })
    .catch(function (error) {
      throw new UserError.makeServiceError(error)
    });
}

function handleRefreshToken (req, res) {
  switch (true) {
    case req.body.hasOwnProperty('client_id') === false:
      throw OAuthError.makeInvalidRequestError('client_id')
    case req.body.client_id != 'uniqueID':
      throw OAuthError.makeInvalidClientError()
    case req.body.hasOwnProperty('client_secret') === false:
    case req.body.client_secret !== 'uniqueSecret':
      throw OAuthError.makeInvalidClientError()
    case req.body.hasOwnProperty('refresh_token') === false:
      throw OAuthError.makeInvalidRequestError('refresh_token')
  }

  let decodedToken

  try {
    decodedToken = jwt.verify(req.body.refresh_token, 'ossp')
  } catch (err) {
    throw OAuthError.makeInvalidRefreshTokenError()
  }

  sendToken(res, decodedToken.sub)
}

function sendToken (res, subject) {
  const options = {
    subject
  }

  const UserSession = {
    "userId": options.subject,
    "accessToken": jwt.sign({}, 'ossp', Object.assign(options, { expiresIn: '2 hours' })),
    "tokenType": "bearer",
    "expiresIn": 60 * 60 * 24,
    "refreshToken": jwt.sign({}, 'ossp', Object.assign(options, { expiresIn: '2 days' }))
  }

  //Save this user to the database
  return axios.post('http://osspmgmt-spring-boot:8080/users', UserSession)
    .then(function (response) {
      return res.status(201).send(response.data)
    })
    .catch(function (error) {
      return res.status(error.response.status).send(error.response.data)
    })
}
