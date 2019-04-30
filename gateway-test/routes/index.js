'use strict'

const router = require('express-promise-router')()

const checkAccessToken = require('../middleware/checkAccessToken')

router.use('/users/getUser', checkAccessToken, require('./users/getUser'))
router.use('/users/register', require('./users/createUser'))
router.use('/users/login', require('./users/loginUser'))
router.use('/business/products', checkAccessToken, require('./business/viewProduct'))
router.use('/business/create', checkAccessToken, require('./business/createBusiness'))

module.exports = router
