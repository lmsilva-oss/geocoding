Simple CRUD for Addresses, with fallback latitude and longitude from Google's Geocoding API

Requirements:
* MongoDB: add the hostname and port (optionally database name) to src/main/resources/application.properties
* Google Geocode API Key: add it to src/main/resources/application.properties

Notes:
* The GET request mapping is a bit weird, it could be done differently: 
    * add a new mapping for /addresses to fetch all addresses currently stored
    * add path parameter for the address ID, i.e. /address/{id}
    * add query parameters for further filtering, i.e. /addresses?city=New%20York

These are only suggestions, since the spec was iron-clad on the required fields 
