let express = require('express');
let router = express.Router();
let request = require('request');

/* GET users listing. */
router.post('/login', function (req, res, next) {
  request('http://databasefeedback.com', function (err, res, body) {
    if (!err && res.statusCode === 200) {
      var data = JSON.parse(body)
      res.render('login', data)
    }
    /*
     * method to deal with body will be added in future.
     */
  })

  let data = req.body,
    ServerCookie = req.headers.cookie,
    nres = res;

  if (data.name == "" || data.pwd == "") {
    res.json({ "msg": "Wrong format of name or pwd" });
  }
  next()
})

module.exports = router;
