FROM python:3.7

RUN apt-get update
RUN apt-get install -y supervisor
RUN pip install uwsgi

RUN mkdir -p /deploy/ossp
RUN mkdir -p /deploy/log/uwsgi

ADD ossp /deploy/ossp/
WORKDIR /deploy/ossp

RUN pip install -r requirements.txt

COPY conf/uwsgi.ini /deploy/uwsgi.ini 
COPY conf/supervisor.conf /etc/supervisor/conf.d/

CMD ["supervisord", "-n"]
