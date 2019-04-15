'use strict'

const router = require('express-promise-router')()

const checkAccessToken = require('../middleware/checkAccessToken')

router.use('/currentSession', checkAccessToken, require('./currentSession'))
router.use('/oauth', require('./oauth'))
router.use('/users/getUser', checkAccessToken, require('./users/getUser'))
router.use('/users/register', require('./users/createUser'))

module.exports = router
