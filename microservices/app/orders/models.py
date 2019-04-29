from datetime import datetime


class Order:
    __slots__ = "order_id", "user_id", "item_id", "business_id"
    created_at = datetime.now
    updated_at = datetime.now

    def __init__(self, order_id, user_id, item_id, business_id):
        self.order_id = order_id
        self.user_id = user_id
        self.item_id = item_id
        self.business_id = business_id

    def __repr__(self):
        return '<Order %s>' % self.order_id
