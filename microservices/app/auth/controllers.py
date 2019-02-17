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
    # TODO query API/Db for user
    requested_user = request.data  # This is the User from the request, just username + password
    stored_user = User(1, "name", "name", "email", "pw")
    if stored_user and check_password_hash(stored_user.password, requested_user.password):
        session['username'] = stored_user.username
        session['email'] = stored_user.email
        login_user(stored_user)
        return
    print('Wrong email or password', 'error-message')


@mod.route('/signout', methods=['GET'])
@login_required
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
    return logout_user()


@mod.route('/signup', methods=['POST'])
@swag_from(signup_spec)
def sign_up():
    """
       parameters:
         - name: Register
           required: username, password, name
           description: Registration route to authenticate new users
       responses:
         400: Register failed
           description: Bad User schema
         200:
           description: Register Success
           result schema:
             User object
           """
    # TODO store user and session in Db
    return request.data


@mod.route('/<user_id>', methods=['GET'])
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
    # TODO get user from database/API
    return User(id=user_id, username="", name="", email="", password="")
