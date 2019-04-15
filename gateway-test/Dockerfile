FROM node:10.15.3

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app/
COPY package-lock.json /usr/src/app/

RUN npm install

COPY . /usr/src/app

RUN groupadd -r nodejs \
    && useradd -m -r -g nodejs nodejs

RUN chown -R nodejs:nodejs /usr/src/app

USER nodejs

EXPOSE 3000

ENTRYPOINT ["npm", "start"]
