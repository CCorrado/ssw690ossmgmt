'use strict'

class HttpError extends Error {
  constructor (statusCode = 500, body = null) {
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

  static makeServiceError (message) {
    return new HttpError(
      400
    )
  }
}

module.exports = HttpError
