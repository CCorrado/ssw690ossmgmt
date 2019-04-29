create_order = {
    "parameters": [
        {
            "name": "userId",
            "in": "path",
            "type": "int",
            "required": True,
            "default": 1
        },
        {
            "name": "itemId",
            "in": "path",
            "type": "int",
            "required": True,
            "default": 3
        },
        {
            "name": "businessId",
            "in": "path",
            "type": "int",
            "required": True,
            "default": 10
        }
    ],
    "definitions": {
        "Order": {
            "type": "object",
            "properties": {
                "orderId": {
                    "type": "String",
                },
                "userId": {
                    "type": "String",
                },
                "itemId": {
                    "type": "String",
                },
                "businessId": {
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
                "message": "Order Creation Failed"
            }
        },
        "201": {
            "description": "Order Successfully Created",
            "schema": {
                "$ref": "#/definitions/Order"
            },
            "examples": {
                "Order": {
                    "orderId": "some-unique-uuid-string",
                    "userId": 1,
                    "businessId": 10,
                    "itemId": 3
                }
            }
        }
    }
}
