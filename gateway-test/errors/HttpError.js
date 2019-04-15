'use strict'

class HttpError extends Error {
  constructor (statusCode = 500, body = undefined) {
    super()

    this.statusCode = statusCode
    this.body = body
  }

  getStatusCode () {
    return this.statusCode
  }

  getBody () {
    return this.body
  }

  static makeNotFoundError () {
    return new HttpError(404)
  }

  static makeServiceError (body) {
    return new HttpError(
      400,
      body
    )
  }
}

module.exports = HttpError
