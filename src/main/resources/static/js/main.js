$(document).ready(function () {

    var app = new Vue({
        el: '#app',
        data: {
            results: null
        },
        methods: {
            loadModules: function () {
                axios.get("/api/all")
                    .then(function (response) {
                        console.log("GET /api/all");
                        app.results = response.data
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            },

            loadListBoxes: function () {
                axios.get("/api/subjects")
                    .then(function (response) {
                        console.log("GET /api/subjects");
                        response.data.forEach(x => {
                            $("#selectSubject").append(`<option>${x}</option>`);
                        })
                    })
                    .catch(function (error) {
                        console.log(error)
                    })

                axios.get("/api/levels")
                    .then(function (response) {
                        console.log("GET /api/levels");
                        response.data.forEach(x => {
                            $("#selectLevel").append(`<option>${x}</option>`);
                        })
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            },

            add: function () {
                var subject = $("#addSubject").val();
                var level = $("#addLevel").val();
                var name = $("#addName").val();
                var status = $("#addStatus").is(':checked');

                axios.post(`/api/${subject}/${level}/${name}?discontinued=${status}`)
                    .then(function (response) {
                        console.log(`POST /api/${subject}/${level}/${name}?discontinued=${status}`)

                        if (response.data == true) {
                            app.results.push({subject: subject, level: level, name: name, discontinued: status})
                        }
                    })
            },

            search: function () {
                var subject = $("#selectSubject").val();
                var level = $("#selectLevel").val();
                var status = $("#selectStatus").is(':checked');

                var path = "/api";

                if (subject === "Any") {
                    path += "/all"
                } else {
                    path += `/${subject}`
                }
                if (level !== "Any"){ path += `/${level}`}

                path += `?discontinued=${status}`;

                axios.get(path)
                    .then(function (response) {
                        console.log(`GET ${path}`);
                        app.results = response.data;

                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            },

            remove: function (event) {
                var elementId = event.srcElement.parentNode.parentNode.id;

                axios.delete(`/api/${elementId}`)
                    .then(function (response) {
                        console.log(`DELETE /api/${elementId}`)
                        app.results = app.results.filter(x => x.name != elementId)
                    })
            },

            changeStatus: function (event) {
                var moduleName = event.srcElement.parentNode.parentNode.id;
                var current = false;
                app.results.forEach(x => {if(x.name === moduleName) {
                    current = x.discontinued;
                    x.discontinued = !current;
                }});
                axios.patch(`/api/${moduleName}?discontinued=${!current}`)
                    .then(function (response) {
                        console.log(`PATCH /api/${moduleName}?discontinued=${!current}`)
                    });
            }
        }
    });

    app.loadModules();
    app.loadListBoxes()
});
