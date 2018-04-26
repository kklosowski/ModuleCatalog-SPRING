window.onload = function() {
    init();
};

function init(){

    $("#add").click(function(){
        axios.get("/api/add")
        .then(function (response){
            console.log(response.data)
        })
    })

    var app = new Vue({
        el: '#app',
        data: {
          results: [{subject:"", level:0, name:""}]
        }
      })

    axios.get("/api/all")
    .then(function (response){
        console.log(response.data)
        app.results = response.data
    })
    .catch(function (error){
        console.log(error)
    })
}