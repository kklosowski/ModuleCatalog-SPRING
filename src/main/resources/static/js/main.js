$(document).ready(function () {

    var app = new Vue({
        el: '#app',
        data: {
            results: null
        },
        methods: {
            loadModules: function (){
                axios.get("/api/all")
                    .then(function (response) {
                        console.log(response.data)
                        app.results = response.data
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

                console.log(`/api/${subject}/${level}/${name}?discontinued=${status}`);

                axios.post(`/api/${subject}/${level}/${name}?discontinued=${status}`)
                    .then(function (response) {
                        if(response.data == true){
                            app.results.push({subject: subject, level: level, name:name, discontinued:status})
                        }
                    })
            },

            remove: function (event) {
                var elementId = event.srcElement.parentNode.parentNode.id;

                axios.delete(`/api/${elementId}`)
                    .then(function (response) {
                        app.results = app.results.filter(x => x.name != elementId)
                        console.log(response.data)
                    })
            },

            changeStatus: function (event) {
                var elementId = event.srcElement.parentNode.parentNode.id;
                //TODO: FIX STATUS CHANGING
                axios.patch(`/api/${elementId}?discontinued=false`)
                    .then(function () {
                        var element = app.results.filter(x => x.name == elementId);
                        element.discontinued = false;
                    })
            }
        }
    });

    app.loadModules();
});
