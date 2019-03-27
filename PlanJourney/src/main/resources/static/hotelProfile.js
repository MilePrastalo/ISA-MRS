var hotelProfile = new Vue({
    el:"#hotelProfile",
    data:{
        name:"",
        address:"",
        description:""
        },
    methods:{
        update: function(){
            sendRequest();
        },
        
        load: function() {
        	getHotel();
        },
        add: function() {
        	addHotel();
        },
        remove: function() {
        	removeHotel();
        }
        
    }
});
function getHotel(){
	axios.get("api/getHotel/"+hotelProfile.name).then(function(response){fillData(response.data);});
}
function sendRequest(){
    axios.put("api/updateHotel",{name : hotelProfile.name, address: hotelProfile.address, description:hotelProfile.description}).then(alert("Profile updated!")); 
}
function addHotel(){
	axios.put("api/addHotel",{name : hotelProfile.name, address: hotelProfile.address, description:hotelProfile.description}).then(alert("Profile added!")); 
}
function removeHotel() {
	axios.delete("api/removeHotel/"+hotelProfile.name).then(alert("Profile removed!"));
}
function fillData(data){
    hotelProfile.name = data.name;
    hotelProfile.address = data.address;
    hotelProfile.description = data.description;
}