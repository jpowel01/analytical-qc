## README

The Analytical QC application consists of four components:

- Database
- Static content
- Backend
- Frontend

### Database

The database contains substance, sample, experiment, and file information along with structured and freeform annotations. It is currently located at ccte-postgres-res.dmap-prod.aws.epa.gov, under database res_gsincl01_qsar and schema sbox_analytical_qc. 

### Static content (content-server)

Static files are served from a directory by an nginx server, by default on port 82. The directory containing all files is currently located at L:/Lab/NCCT_Richard/Antony Williams/analytical-qc_GFBS_010722/analytical-qc_content-server_static.tar.gz. The contents of this file (~10 GB) should be extracted into the content-server/static directory of this project for deployment.

### Backend (server)

A database API served by a Spring Boot web service, by default on port 9091.

### Frontend (client)

A frontend interface in Vue.js, running on an nginx server, by default on port 81.

## Important

### Adding files

The web app interface contains a panel to link files to substances. Files added this way **must be manually added to the static content** in order to display; otherwise you will see broken links. Files can be added before or after linking in the interface. To add files, copy them into the content-server/static directory of the deployment location, then run `docker-compose up -d --build --force-recreate --no-deps content-server` to recreate without affecting the server and client services.

### scp instructions

Files can be copied onto a remote server using scp (https://linuxize.com/post/how-to-use-scp-command-to-securely-transfer-files/):

1. Open cmd on your local machine.
2. Navigate (cd) to the location of the files to be added.
3. Run `scp ./*.pdf [USER]@[HOST]:[DEPLOYMENT_LOCATION]/content-server/static`.

### Moving the database/redeploying

This application is set up to deploy in Docker using docker-compose. It is now deployed on the v2626umcth819.rtord.epa.gov server. If the database is transferred to a different location or a different user account is used to access it, the app credentials will need to be altered and the services rebuilt. The current environment variable values are in a .env.local file provided to Tony Williams.

0. Clone this repository into the desired deployment location.
0.5. Extract the static files into the content-server/static directory of this repository.
1. Place the .env.local file in the root directory of this repository.
2. Change the `DB_` variables in the .env.local file as needed.
3. Run `export $(cat .env.local | xargs)` to export the variables in .env.local to your environment.
4. Run `docker-compose up --build --force-recreate -d` to build and run the services in detached mode.

### Server environment variables

- DB_URL, DB_USER, DB_PASS, DB_SCHEMA: Standard database connection parameters
- DB_DRIVER, DB_DIALECT: Presently for PostgreSQL, made variable to enable compatibility with MySQL systems if needed in the future (MySQL driver included in pom.xml)
- CLIENT_URL: URL of client deployment to enable CORS
- DASHBOARD_API_URL: URL of CCD API to acquire new substance information

### Client environment variables

- VUE_APP_SERVER_URL: URL of server deployment
- VUE_APP_CONTENT_SERVER_URL: URL of content-server deployment
