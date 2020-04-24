# Citer

![This project is not actively maintained!](https://img.shields.io/badge/Development-inactive-red?style=for-the-badge)

## REST API

| URI                      | Method | Description
---------------------------|--------|-----------------------------------
/api/cite                  | GET    | List of URIs of all cites.
/api/cite                  | PUT    | Creates new cite.
/api/cite/{id}             | PUT    | Updates a cite.
/api/cite/{id}             | GET    | Returns a cite.
/api/cite/{id}             | DELETE | Deletes a cite.
/api/cite/{id}/originator  | GET    | Returns the originator of a cite.
/api/cite/random           | GET    | Gets a random cite.
/api/originator/           | GET    | List of URIs of all originators.
/api/originator/           | PUT    | Create new originator.
/api/originator/{id}       | PUT    | Update an originator.
/api/originator/{id}       | GET    | Return an originator.
/api/originator/{id}       | DELETE | Deletes an originator.
/api/originator/{id}/cites | GET    | Returns an originators cites.

## JS Template Engines

- http://mustache.github.com/
- http://handlebarsjs.com/
