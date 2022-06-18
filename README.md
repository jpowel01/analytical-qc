# Summary

The Analytical QC application consists of four components:

- Database
- Static content
- Backend
- Frontend

## Static content (content-server)

Static files (experimental spectra) are served by an nginx server.

## Backend (server)

A database API is served by a Spring Boot web service.

### Environment variables

- DB_URL, DB_USER, DB_PASS, DB_SCHEMA: Standard database connection parameters
- DB_DRIVER, DB_DIALECT: Presently for PostgreSQL, made variable to enable compatibility with MySQL systems if needed in the future (MySQL driver included in pom.xml)
- CLIENT_URL: URL of client deployment (see below) to enable CORS

## Frontend (client)

A frontend interface built in Vue.js, running on an nginx server.

### Environment variables

- VUE_APP_SERVER_URL: URL for backend
- VUE_APP_CONTENT_SERVER_URL: URL for static content