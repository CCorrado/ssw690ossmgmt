'use strict'

const router = require('express-promise-router')()

router.post('/token', require('./createToken'))

module.exports = router
