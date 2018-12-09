This is a simple REST Single Page Application created using Spring, Hibernate, Vue.js, JQuery and Axios.js. 

To compile:
1. mvn spring-boot:run
2. Open browser and go to localhost:8080

The front-end client is a single page application, all the changes to the data are handled by Vue.js
and the application doesn't need to be refreshed in any situation. 
All the changes are made accordingly to the response from back-end REST service 
ie. The data won't be added to the front-end if it isn't added in the back-end.

The back-end service uses hibernate and SQLite for handling the data.

Front-end uses Axios.js for HTTP calls, Vue.js for data manipulation 
and JQuery for event handling and easy DOM manipulation

All the HTTP calls made are displayed as they happen inside the console.
(To open the JavaScript console use Ctrl+Shift+J)
