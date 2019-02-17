from datetime import datetime

from werkzeug.security import check_password_hash, generate_password_hash

from app import login_manager


@login_manager.user_loader
def load_user(user_id):
    # TODO get user from db/API for their ID
    return User(int(user_id), "username", "name", "email@email.com", "Test1234")


class User:
    __slots__ = "id", "username", "name", "email", "password", "level", "active", "address"
    created_at = datetime.now
    updated_at = datetime.now

    def __init__(self, id, username, name, email, password):
        self.id = id
        self.username = username
        self.name = name
        self.email = email
        self.set_password(password)

    def __repr__(self):
        return '<User %s>' % self.username

    def set_password(self, password):
        self.password = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password, password)
