from flasgger import swag_from
from flask import Blueprint, request, session
from flask_login import login_required, login_user, logout_user
from werkzeug.security import check_password_hash

from app.auth.models import User
from .apis import *

mod = Blueprint('auth', __name__, url_prefix='/auth')


@mod.route('/signin', methods=['POST'])
@swag_from(signin_spec)
def sign_in():
    """
       parameters:
         - name: Login
           required: username and password
           description: Login route to authenticate existing users
       responses:
         400: Login failed
           description: Bad Username/Password
         200:
           description: Login Success
           result schema:
             User object
       """

    # get post data
    post_data = request.get_json()
    if not post_data:
        response_object = {
            'status': 'error',
            'message': 'Invalid request.'
        }
        return jsonify(response_object), 400
    email = post_data.get('email')
    password = post_data.get('password')
    try:
        # fetch the user data
        user = User.query.filter_by(email=email).first()
        if user and bcrypt.check_password_hash(user.password, password):
            auth_token = user.encode_auth_token(user.id)
            if auth_token:
                response_object = {
                    'status': 'success',
                    'message': 'Successfully logged in.',
                    'auth_token': auth_token.decode()
                }
                return jsonify(response_object), 200
        else:
            response_object = {
                'status': 'error',
                'message': 'User does not exist.'
            }
            return jsonify(response_object), 404
    except Exception as e:
        print(e)
        response_object = {
            'status': 'error',
            'message': 'Try again.'
        }
        return jsonify(response_object), 500
'''
    # TODO query API/Db for user
    requested_user = request.data  # This is the User from the request, just username + password
    stored_user = User(1, "name", "name", "email", "pw")
    if stored_user and check_password_hash(stored_user.password, requested_user.password):
        session['username'] = stored_user.username
        session['email'] = stored_user.email
        login_user(stored_user)
        return
    print('Wrong email or password', 'error-message')'''


@mod.route('/signout', methods=['GET'])
@login_required
@authenticate
def sign_out():
    """
        parameters:
         - name: Signout
           required: User ID and valid session
           description: Logout route to destroy a user session
        responses:
         400: Logout failed
           description: User session invalid
         200:
           description: Logout Success
    """
    
    response_object = {
        'status': 'success',
        'message': 'Successfully logged out.'
    }
    return jsonify(response_object), 200
    return sign_out()



@mod.route('/signup', methods=['POST'])
@swag_from(signup_spec)
"""def sign_up():
    """
       """parameters:
         - name: Register
           required: username, password, name
           description: Registration route to authenticate new users
       responses:
         400: Register failed
           description: Bad User schema
         200:
           description: Register Success
           result schema:
             User object"""
           """
    # TODO store user and session in Db
    return request.data"""
def sign_up():
    # get post data
    post_data = request.get_json()
    if not post_data:
        response_object = {
            'status': 'error',
            'message': 'Invalid payload.'
        }
        return jsonify(response_object), 400
    username = post_data.get('username')
    email = post_data.get('email')
    password = post_data.get('password')
    try:
        # check for existing user
        user = User.query.filter(
            or_(User.username == username, User.email==email)).first()
        if not user:
            # add new user to db
            new_user = User(
                username=username,
                email=email,
                password=password
            )
            db.session.add(new_user)
            db.session.commit()
            # generate auth token
            auth_token = new_user.encode_auth_token(new_user.id)
            response_object = {
                'status': 'success',
                'message': 'Successfully registered.',
                'auth_token': auth_token.decode()
            }
            return jsonify(response_object), 201
        else:
            response_object = {
                'status': 'error',
                'message': 'Sorry. That user already exists.'
            }
            return jsonify(response_object), 400
    # exceptions
    except (exc.IntegrityError, ValueError) as e:
        db.session().rollback()
        response_object = {
            'status': 'error',
            'message': 'Invalid payload.'
        }
        return jsonify(response_object), 400



@mod.route('/<user_id>', methods=['GET'])
@authenticate
def get_user(user_id):
    """
    @:param id - The user ID
       parameters:
         - name: Get User for ID
           required: userID and session token
           description: Authenticated user to get profile
       responses:
         400: GET User failed
           description: User request failed
         401: GET User failed
           description: User not authenticated
         403: GET User failed
           description: User forbidden
         200:
           description: Get User Success
           result schema:
             User object
    """
    
    user = User.query.filter_by(id=user_id).first()
    response_object = {
        'status': 'success',
        'data': {
            'id': user.id,
            'username': user.username,
            'email': user.email,
            'active': user.active,
            'created_at': user.created_at
        }
    }
    return jsonify(response_object), 200

    # TODO get user from database/API
    return User(id=user_id, username="", name="", email="", password="")