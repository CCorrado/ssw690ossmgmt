from flasgger import swag_from
from flask import Blueprint, request, session
from flask_login import login_required, login_user, logout_user
from werkzeug.security import check_password_hash
from flask import Blueprint, request, make_response, jsonify
from flask.views import MethodView
from project.server import bcrypt, db
from project.server.models import User, BlacklistToken

@app.route('/order/')
def showorders():
    # query db and assign to orders variable
    orders = session.query(order).order_by(order.id.asc()).all()

    # Check if user credential match data in login_session
    # Check if username data in login_session
    if 'username' not in login_session:
        # This page will redirect user to Google login page
        return jsonify(orders = orders, title = "orders")
    else:
        # return "This page will show all orders"
        return jsonify(orders)


@app.route('/order/new/', methods = ['GET', 'POST'])
# Method to create neworder
def neworder():
    # Check to see if a user is logged in.
    if 'username' not in login_session:
        return flask.jsonify(error=404, text=str(e)), 404
    # If user is logged in then access neworder page
    if request.method == 'POST':
        # Store order input from form into neworder
        neworder = order(
            name = request.form['name'].strip(),
            street = request.form['street'].strip(),
            city = request.form['city'].strip(),
            state = request.form['state'].strip(),
            zipCode = request.form['zipCode'].strip(),
            phone = request.form['phone'].strip(),
            email = request.form['email'].strip(),
            website = request.form['website'].strip(),
            cuisine = request.form['cuisine'].strip(),
            description = request.form['description'].strip(),
            delivery = request.form['delivery'].strip(),
            user_id = login_session['user_id']
        )
        # add neworder item to db stage
        session.add(neworder)        # commit neworder item to db
        session.commit()

        # flash message to indicate success
        flash("New order: " + neworder.name + " ==> Created!")
        # redirect user to updated list of orders
        return jsonify(showorder)
    else:
        return jsonify(title = "New order Input")

# Edit a order

@app.route('/order/order id/edit/', methods = ['GET', 'POST'])
def editorder(order_id):

    # query db by order_id and assign to order variable
    order = session.query(order).filter_by(id = order_id).one()

    # Check to see if a user is logged in.
    if 'username' not in login_session:
        return flask.jsonify(error=404, text=str(e)), 404

    # Check if user_id matches the user_id stored in login_session
    if order.user_id != login_session['user_id']:
        return authorizationAlert("Edit")

    # If user is logged in then access editorder page
    if request.method == 'POST':
        if request.form:
            # Store edited field data and POST to db
            order.name = request.form['name'].strip()
            order.street = request.form['street'].strip()
            order.city = request.form['city'].strip()
            order.state = request.form['state'].strip()
            order.zipCode = request.form['zipCode'].strip()
            order.phone = request.form['phone'].strip()
            order.email = request.form['email'].strip()
            order.website = request.form['website'].strip()
            order.cuisine = request.form['cuisine'].strip()
            order.description = request.form['description'].strip()
            order.delivery = request.form['delivery'].strip()
        # add editorder data to db stage
        session.add(order)
        # commit editorder data to db
        session.commit()

        # flash msg to indicate success
        flash("Edited order: " + order.name + " ==> Updated!")
        # redirect user to updated list of orders
        return jsonify(showorders)
    else:
        return flask.jsonify(error=404, text=str(e)), 404
    
# Delete an order

@app.route('/order/order id/delete/', methods = ['GET', 'POST'])
def deleteorder(order_id):

    # query db by order_id and assign to order variable
    order = session.query(order).filter_by(id=order_id).one()

    # Check to see if a user is logged in.
    if 'username' not in login_session:
        return flask.jsonify(error=404, text=str(e)), 404
  
    # Check if user_id matches the user_id stored in login_session
    if order.user_id != login_session['user_id']:
        return authorizationAlert("Delete")

    # If user is logged in then access deleteorder page
    if request.method == 'POST':
        # delete order object obtained from db query
        session.delete(order)
        # commit delete to db
        session.commit()
        flash("Deleted order: " + order.name + " ==> Deleted!")
        # redirect user to updated list of orders
        return jsonify(showorders)
    else:
        return jsonify(order)

@app.errorhandler(404)
def page_not_found(e):
    return flask.jsonify(error=404, text=str(e)), 404   

if __name__ == '__main__':
    app.run(debug=True,port=3000)
