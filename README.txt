To compile:
1. mvn spring-boot:run
2. Open browser and go to localhost:8080

(In case maven doesn't see the java directory from global PATH variable please uncomment
the plugin at the end of pom.xml file and set the path to javac.exe. 
Should any other problems arise, pre-compiled version is available to launch with java -jar target/module-catalog-1.jar)

Open JavaScript console inside the browser Ctrl+Shift+J for Chrome.
All the HTTP calls made are displayed as they happen inside the console.

The front-end client is a single page application, all the changes to the data are handled by Vue.js
and the application doesn't need to be refreshed in any situation. 
All the changes are made accordingly to the response from back-end REST service 
ie. module won't be added to the front-end if it isn't added in the back-end.

The back-end service uses hibernate and SQLite for handling the data. Due to the usage of 
hibernate and it's transactional nature, synchronisation isn't needed.

Front-end uses Axios.js for HTTP calls, Vue.js for data manipulation 
and JQuery for event handling and easy DOM manipulation

Known bugs: 
Input values for subject and name can't contain "/" or "\" due to path parameter usage.
Validation of this in javascript is outside the scope of the assignment.
Selecting modules by level alone not implemented.

