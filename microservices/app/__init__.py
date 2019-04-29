from flasgger import Swagger
from flask import Flask, jsonify

from config import config


def create_app(config_name):
    """dynamic environment"""
    app = Flask(__name__)
    app.config.from_object(config[config_name])

    api_config = {
        "headers": [
        ],
        "specs": [
            {
                "endpoint": '/',
                "route": '/apispec.json',
                "rule_filter": lambda rule: True,  # all in
                "model_filter": lambda tag: True,  # all in
            }
        ],
        "static_url_path": "/flasgger_static",
        "specs_route": "/"
    }
    Swagger(app, config=api_config)

    #  # HTTP error handling
    @app.errorhandler(404)
    def not_found(error):
        response = jsonify({'message': 'Not Found'})
        response.status_code = 404
        return response

    @app.errorhandler(400)
    def bad_request(error):
        response = jsonify({'message': 'Bad Request'})
        response.status_code = 400
        return response

    @app.errorhandler(405)
    def bad_request(error):
        response = jsonify({'message': 'Bad Method'})
        response.status_code = 405
        return response

    @app.errorhandler(500)
    def internal_server_error(error):
        response = jsonify({'message': 'Server Error'})
        response.status_code = 500
        return response

    from app.auth.controllers import mod_auth as auth_module
    from app.orders.controllers import mod_orders as orders_module

    app.register_blueprint(auth_module)
    app.register_blueprint(orders_module)

    return app
