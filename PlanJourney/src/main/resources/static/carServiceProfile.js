var carProfile = new Vue({
    el:"#carProfile",
    data:{
        name:"",
        address:"",
        description:""
        },
    mounted(){
        axios.get("./api/getRentACarCompany")
            .then(function (response) {
                fillData(response.data)
                }); 
    },
    methods:{
        update: function(){
            sendRequest();
        },
        
    }
});
function sendRequest(){
    axios.post("./api/updateRentACarProfile",{name : carProfile.name, address: carProfile.address, description:carProfile.description}).then(alert("Profile updated!")); 
}
function fillData(data){
    carProfile.name = data.name;
    carProfile.address = data.address;
    carProfile.description = data.description;
}