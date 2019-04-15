'use strict'

const router = require('express-promise-router')()

router.get('/:id', require('./getUser'))

module.exports = router
