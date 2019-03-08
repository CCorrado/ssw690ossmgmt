# -*- coding:utf-8 -*-
# Run a test server.
import os
import unittest

from werkzeug.serving import run_simple
from werkzeug.wsgi import DispatcherMiddleware

from app import create_app

if os.path.exists('.env'):
    print('Importing environment from .env file')
    for line in open('.env'):
        var = line.strip().split('=')
        if len(var) == 2:
            os.environ[var[0]] = var[1]

if os.environ.get('ossp') == 'production':
    app = DispatcherMiddleware(create_app(os.environ.get("ossp")), {
        '/api': create_app('api')
    })
else:
    app = create_app(os.environ.get("ossp") or 'development')


@app.cli.command()
def hello():
    """say hello for testing"""
    print('hello')


@app.cli.command()
def test():
    """Run unit tests."""
    tests = unittest.TestLoader().discover('app.tests', pattern='*.py')
    unittest.TextTestRunner(verbosity=1).run(tests)


@app.cli.command()
def dev():
    # TODO add TestingConfig
    pass


if __name__ == '__main__':
    if os.environ.get('ossp') == 'production':
        run_simple('0.0.0.0', 5000, app, use_reloader=False, use_debugger=False)
