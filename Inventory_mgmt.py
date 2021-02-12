import os
from flask import Flask, jsonify, request
app = Flask(__name__)

@app.route("/users/<string:user_id>")
def get_user(user_id):
    resp = api.get_item(
        TableName=USERS_TABLE,
        Key={
            'userId': { 'S': user_id }
        }
    )
    item = resp.get('Item')
    if not item:
        return jsonify({'error': 'User does not exist'}), 404

    return jsonify({
        'userId': item.get('userId').get('S'),
        'name': item.get('name').get('S')
    })


class Inventory:
    @staticmethod
    def list_products() -> [Product]:
        return stripe.Product.list(limit=3)

    @staticmethod
    def retrieve_product(product_id) -> Product:
        return stripe.Product.retrieve(product_id)

    @staticmethod
    def products_exist(product_list: [Product]) -> bool:
        valid_products = ['increment', 'business', 'order']
        product_list_data = product_list['data']

        return reduce(lambda acc, product: acc and len(product_list_data) == 3 and product['id'] in valid_products,
                      product_list['data'],
                      len(product_list_data) > 0)
    @staticmethod
    def list_products() -> [Product]:
        return """api.Product.list(limit=3)"""

    @staticmethod
    def retrieve_product(product_id) -> Product:
        return """api.Product.retrieve(product_id)"""

    @staticmethod
    def products_exist(product_list: [Product]) -> bool:
        valid_products = ['increment', 'shirt', 'pins']
        product_list_data = product_list['data']

        return reduce(lambda acc, product: acc and len(product_list_data) == 3 and product['id'] in valid_products,
                      product_list['data'],
                      len(product_list_data) > 0)

    @app.route('/products', methods=['GET'])
    def get_products():
        products = Inventory.list_products()
        if Inventory.products_exist(products):
            return jsonify(products)
        else:
            # Create Products for not present.
            setup.create_data()
            products = Inventory.list_products()
            return jsonify(products)


    @app.route('/products/<string:product_id>', methods=['GET'])
    def retrieve_product(product_id):
        return jsonify(Inventory.retrieve_product(product_id))

    
