# https://hub.docker.com/r/library/python/tags/
FROM python:3.7

WORKDIR /microservices

VOLUME /tmp

# Coping source in current directory into the image
COPY . /microservices

# Commands in a list
RUN ["pip", "install", "-r", "requirements.txt"]

CMD ["python", "manager.py"]
