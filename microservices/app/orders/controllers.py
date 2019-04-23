import uuid

from flasgger import swag_from
from flask import Blueprint, jsonify, request

from app.orders.apis import create_order
from app.orders.models import Order

mod_orders = Blueprint('orders', __name__, url_prefix='/orders')


@mod_orders.route('/', methods=['GET'])
def get_orders():
    """
    description: Get orders for a given user
       responses:
         400: Get Orders failed
            description: Bad Request
         200:
           description: Get Orders Success
           result schema:
             List<Order> object
    """
    # TODO query db for user's orders
    orders = []
    return jsonify(orders)


@mod_orders.route('/create', methods=['POST'])
@swag_from(create_order)
def create_order():
    """
    description: Create a new Order
    parameters:
     - userId: User ID
     - itemId: ID of the item ordered
     - businessId: ID of the business ordered from.
       required: [userId, itemId, businessId]
    responses:
     400: Order creation failed
       description: Bad Request
     201:
       description: Order Creation succeeded
       result schema:
         Order object
    """
    request_body = request.get_json()
    new_order = Order(
        order_id=uuid.uuid4(),
        user_id=request_body['userId'].strip(),
        item_id=request_body['itemId'].strip(),
        business_id=request_body['businessId'].strip()
    )
    return jsonify(new_order)
