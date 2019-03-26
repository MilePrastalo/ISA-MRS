var search = new Vue({
            el:"#vehicleSearch",
            data:{
                cars:[],
                producer:"",
                priceFrom:"",
                priceTo:"",
                newer:"",
                older:"",
                type:"",
                producers:["Mercedes","Audi","BMW"],
                types:["Sedan","Jeep"]
                },
            mounted(){
                axios.get("./api/getProducers")
                    .then(function (response) {
                        setProducers(response.data);
                        }); 
                axios.get("./api/getTypes")
                .then(function (response) {
                    setTypes(response.data);
                    }); 
            },
            methods:{
                pretraga: function(){
                    sendRequest();
                },
                
            }
        })
function writeResults(data){
    search.cars = data
}
function sendRequest(){
    axios.post("./api/vehicleSearch",{producer : search.producer, priceFrom: search.priceFrom, priceTo:search.priceTo, newer:search.newer, older:search.older,type:search.type})
        .then(function (response) {
            writeResults(response.data);
            }); 
}
function setProducers(data){
    search.producers = data
}
function setTypes(data){
    search.types = data
}