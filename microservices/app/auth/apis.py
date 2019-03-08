signup_spec = {
    "parameters": [
        {
            "name": "username",
            "in": "path",
            "type": "string",
            "required": True,
            "default": "joesmith@test.com"
        },
        {
            "name": "password",
            "in": "path",
            "type": "string",
            "required": True,
            "default": "Test1234"
        },
        {
            "name": "name",
            "in": "path",
            "type": "string",
            "required": True,
            "default": "Joe Smith"
        }
    ],
    "definitions": {
        "User": {
            "type": "object",
            "properties": {
                "username": {
                    "type": "String",
                },
                "password": {
                    "type": "String",
                },
                "name": {
                    "type": "String",
                }
            }
        },
        "Bad Request": {
            "error": 400,
            "message": "Invalid Request"
        }
    },
    "responses": {
        "400": {
            "description": "Bad Request",
            "schema": {
                "$ref": "#/definitions/Bad Request"
            },
            "examples": {
                "error": 400,
                "message": "User Registration Failed"
            }
        },
        "200": {
            "description": "User Registration Successful",
            "schema": {
                "$ref": "#/definitions/User"
            },
            "examples": {
                "User": {
                    "username": "joesmith@test.com",
                    "password": "Test1234",
                    "name": "Joe Smith"
                }
            }
        }
    }
}

signin_spec = {
    "parameters": [
        {
            "name": "username",
            "in": "path",
            "type": "string",
            "required": True,
            "default": "joesmith@test.com"
        },
        {
            "name": "password",
            "in": "path",
            "type": "string",
            "required": True,
            "default": "Test1234"
        }
    ],
    "definitions": {
        "User": {
            "type": "object",
            "properties": {
                "username": {
                    "type": "String",
                },
                "password": {
                    "type": "String",
                }
            }
        },
        "Bad Request": {
            "error": 400,
            "message": "Invalid Request"
        }
    },
    "responses": {
        "400": {
            "description": "Bad Request",
            "schema": {
                "$ref": "#/definitions/Bad Request"
            },
            "examples": {
                "error": 400,
                "message": "User Login Failed"
            }
        },
        "200": {
            "description": "User Login Successful",
            "schema": {
                "$ref": "#/definitions/User"
            },
            "examples": {
                "User": {
                    "username": "joesmith@test.com",
                    "password": "Test1234",
                    "name": "Joe Smith"
                }
            }
        }
    }
}
