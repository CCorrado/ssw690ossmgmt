'use strict'

const router = require('express-promise-router')()

const checkAccessToken = require('../middleware/checkAccessToken')

//User Routes
router.use('/users/getUser', checkAccessToken, require('./users/getUser'))
router.use('/users/register', require('./users/createUser'))
router.use('/users/login', require('./users/loginUser'))

//Business Routes
router.use('/business/products', checkAccessToken, require('./business/viewProduct'))
router.use('/business/products/create', checkAccessToken, require('./business/createProduct'))
router.use('/business/create', checkAccessToken, require('./business/createBusiness'))
router.use('/business/getAll', checkAccessToken, require('./business/getBusinesses'))

module.exports = router
