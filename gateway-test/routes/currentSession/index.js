'use strict'

const router = require('express-promise-router')()

router.get('/', require('./currentSession'))

module.exports = router
