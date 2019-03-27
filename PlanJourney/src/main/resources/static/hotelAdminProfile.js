var hotelAdminProfile = new Vue({
    el:"#hotelAdminProfile",
    data:{
        username:"",
        password:"",
        firstName:"",
        lastName:"",
        email:""
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
	axios.get("api/getHotelAdmin/"+hotelAdminProfile.username).then(function(response){fillData(response.data);});
}
function sendRequest(){
    axios.put("api/updateHotelAdmin",{username : hotelAdminProfile.username, password: hotelAdminProfile.password, firstName:hotelAdminProfile.firstName, lastName: hotelAdminProfile.lastName,email: hotelAdminProfile.email}).then(alert("Profile updated!")); 
}
function addHotel(){
	axios.put("api/addHotelAdmin",{username : hotelAdminProfile.username, password: hotelAdminProfile.password, firstName:hotelAdminProfile.firstName, lastName: hotelAdminProfile.lastName,email: hotelAdminProfile.email}).then(alert("Profile added!")); 
}
function removeHotel() {
	axios.delete("api/removeHotelAdmin/"+hotelAdminProfile.username).then(alert("Profile removed!"));
}
function fillData(data){
	hotelAdminProfile.username = data.username;
	hotelAdminProfile.password = data.password;
	hotelAdminProfile.firstName = data.firstName;
	hotelAdminProfile.lastName = data.lastName;
	hotelAdminProfile.email = data.email;
}