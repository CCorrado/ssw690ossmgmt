from datetime import datetime

from flask import jsonify
from werkzeug.security import check_password_hash, generate_password_hash


class User:
    __slots__ = "username", "password", "name", "role", "accessToken", "tokenType", "expiresIn", "refreshToken"
    created_at = datetime.now
    updated_at = datetime.now

    def __init__(self, username, password, name, role, accessToken, tokenType, expiresIn, refreshToken):
        self.username = username
        self.name = name
        self.password = password
        self.role = role
        self.accessToken = accessToken
        self.expiresIn = expiresIn
        self.tokenType = tokenType
        self.refreshToken = refreshToken

    def __repr__(self):
        return '<User %s>' % self.username

    def set_password(self, password):
        self.password = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password, password)

    def toJSON(self):
        return jsonify(
            username=self.username,
            password=self.password,
            name=self.name,
            role=self.role,
            accessToken=self.accessToken,
            tokenType=self.tokenType,
            expiresIn=self.expiresIn,
            refreshToken=self.refreshToken
        )
