from flasgger import swag_from
from flask import request, abort, Blueprint

from app.auth.models import User
from .apis import *

mod_auth = Blueprint('auth', __name__, url_prefix='/auth')


@mod_auth.route('/signin', methods=['POST'])
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
    requested_user = request.get_json()
    user = User(
        username=requested_user['username'],
        password=requested_user['password'],
        accessToken=requested_user['accessToken'],
        tokenType=requested_user['tokenType'],
        expiresIn=requested_user['expiresIn'],
        refreshToken=requested_user['refreshToken'],
        name='',
        role=''
    )
    if user.check_password(requested_user['pwAttempted']):
        return user.toJSON()
    else:
        abort(400)


@mod_auth.route('/signout', methods=['GET'])
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
    abort(200)


@mod_auth.route('/signup', methods=['POST'])
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
    requested_user = request.get_json()
    user = User(
        username=requested_user['username'],
        password=requested_user['password'],
        name=requested_user['name'],
        role=requested_user['role'],
        accessToken=requested_user['accessToken'],
        tokenType=requested_user['tokenType'],
        expiresIn=requested_user['expiresIn'],
        refreshToken=requested_user['refreshToken']
    )
    user.set_password(requested_user['password'])
    return user.toJSON()
