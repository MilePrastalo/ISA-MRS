<template>
   <div id = "saHotels" class="container">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">Hotels</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add Hotel</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Hotel Admin</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1 && showHotels == true">
                <br>
                <br>
                <table class="table" >
                    <thead class="thead-dark">
                    <tr>
                        <th>Hotel Name</th>
                        <th>City Name</th>
                        <th>Adress</th>
                        <th>Description</th>
                        <th>Rating</th>
                        <th>Options</th>
                        <th> </th>
                    </tr>
                    </thead>
            <tr v-for="h in this.hotels" :key="h.id">  
                <td>{{h.name}}</td>
                <td>{{h.cityName}}</td>
                <td>{{h.address}}</td>
                <td>{{h.description}}</td>
                <td>{{h.rating}}</td>
                <td><button v-on:click="addHotelAdminDiv(h.name)" class="btn-primary" >Add Admin</button></td>
                <td><button v-on:click="removeHotel(h.name)" class="btn-primary" style="background-color:red">Remove</button></td>
            </tr>
            </table>
            </div>
            <div v-if="currentTab == 2">
                            <table class="table">
                                <th>
                                    Hotel Info: 
                                </th>
                <tr>
                    <td><b> Name: </b></td>
                    <td>  <input type="text" v-model="newHotel.name" > </td>
                </tr>
                <tr>
                    <td><b> Address: </b></td>
                    <td>  <input type="text" v-model="newHotel.address" > </td>
                </tr>
                <tr>
                    <td><b> City Name: </b></td>
                    <td>  <input type="text" v-model="newHotel.cityName" > </td>
                </tr>
                <tr>
                    <td><b> Latitude: </b></td>
                    <td>  <input type="number"  v-model="newHotel.latitude" > </td>
                </tr>
                <tr>
                    <td><b> Longitude: </b></td>
                    <td>  <input type="number"  v-model="newHotel.longitude" > </td>
                </tr>
                <tr>
                    <td><b>Description: </b></td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="newHotel.description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addHotel()" class="btn-primary">Add Hotel</button> </td>      
                </tr>
            </table>   
                 
            
            </div> 

            <div  v-if="addingHotelAdmin == true">
                 <table>
                     <tr>
                    <td><b> Hotel Name: {{hotelName}} </b></td>  
                </tr>
                <tr><td> </td></tr>
                <tr>
                    <td><b> Username: </b></td>
                    <td>  <input type="text" v-model="admin.username" > </td>
                </tr>
                <tr>
                    <td><b> Password: </b></td>
                    <td>  <input type="text" v-model="admin.password" > </td>
                </tr>
                <tr>
                    <td><b> First Name: </b></td>
                    <td> <input type="text" v-model="admin.firstName" > </td>        
                </tr>
                <tr>
                    <td><b> Last Name: </b></td>
                    <td> <input type="text" v-model="admin.lastName" > </td>        
                </tr>
                <tr>
                    <td><b> Email: </b></td>
                    <td> <input type="text" v-model="admin.email" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addHotelAdmin()" class="btn-primary">Add Hotel Admin</button> </td>
                    <td><button v-on:click="cancelHotelAdmin()" class="btn-primary">Cancel</button> </td>           
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 3"> 
                <br>
                <br>
                <table class="table" >
                    <thead class="thead-dark">
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Hotel Name</th>
                        <th>Options</th>
                    </tr>
                    </thead>
            <tr v-for="a in this.hotelAdmins" :key="a.id">  
                <td>{{a.username}}</td>
                <td>{{a.firstName}}</td>
                <td>{{a.lastName}}</td>
                <td>{{a.hotelName}}</td>
                <td><button v-on:click="removeAdmin(a.username)" class="btn-primary" style="background-color:red">Remove</button></td>
            </tr>
            </table>
        </div>
   </div>
</template>

<script>
import axios from "axios";

export default {
  name: "saHotels",
  components: {
  },
  data: function () {
  return {
    hotels: [],
    hotelAdmins: [],
    admin: {},
    newHotel: {},
    name: "",
    hotelName: "",
    addingHotelAdmin: false,
    showHotels: true,
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getAllHotels")
            .then(response => {
                this.hotels = response.data;
            });

        axios.get("/api/getAllHotelAdmins")
            .then(response => {
                this.hotelAdmins = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addHotel: function() {
            if(this.newHotel.name == null || this.newHotel.name == "") {
                alert("Please enter hotel name.");
                return;
            }
            if(this.newHotel.address == null || this.newHotel.address == "") {
                alert("Please enter hotel address.");
                return;
            }
            if(this.newHotel.longitude == null) {
                alert("Please enter hotel longitude.");
                return;
            }
            if(this.newHotel.latitude == null) {
                alert("Please enter hotel latitude.");
                return;
            }
            if(this.newHotel.cityName == null || this.newHotel.cityName == "") {
                alert("Please enter name of city.");
                return;
            }

            axios.post("/api/addHotel",this.newHotel).
            then(response =>{
                if(response.data == true) {
                    alert("Hotel has been successfully added.");
                    this.hotels.push(this.newHotel);
                    this.newHotel = {};
                } else {
                    alert("Input data was not correct.")
                }
                
            })
        },
        removeHotel: function(name) {
            axios.delete("/api/removeHotel/"+ name)
            .then(response => {
                var index;
                for(let h in this.hotels) {
                    if(this.hotels[h].name == name) {
                        index = h;
                        break;
                    }
                }
                this.hotels.splice(index,1);
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addHotelAdminDiv: function(name) {
            this.showHotels = false;
            this.hotelName = name;
            this.addingHotelAdmin = true;
        },
        cancelHotelAdmin: function() {
          this.admin = {};
          this.addingHotelAdmin = false;
          this.showHotels = true;  
        },
        addHotelAdmin: function() {
            if(this.admin.username == null || this.admin.username == "") {
                alert("Please enter hotel admin username.");
                return;
            }
            if(this.admin.password == null || this.admin.password == "") {
                alert("Please enter hotel admin password.");
                return;
            }
            if(this.admin.firstName == null || this.admin.firstName == "") {
                alert("Please enter hotel admin first name.");
                return;
            }
            if(this.admin.lastName == null || this.admin.lastName == "") {
                alert("Please enter hotel admin last name.");
                return;
            }
            if(this.admin.email == null || this.admin.email == "") {
                alert("Please enter hotel admin email.");
                return;
            }
            if(this.hotelName == null || this.hotelName == "") {
                alert("Please enter hotel admin hotel name.");
                return;
            }

            axios.post("/api/addHotelAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,companyName:this.hotelName}).
            then(response =>{
                if(response.data === true) {
                    alert("Hotel admin has been successfully added.");
                    this.admin = {};
                } else {
                    alert("Username is taken.");
                }
            })
        },
        removeAdmin: function(name) {
            axios.delete("/api/removeHotelAdmin/"+ name)
            .then(response => {
                var index;
                for(let a in this.hotelAdmins) {
                    if(this.hotelAdmins[a].name == name) {
                        index = a;
                        break;
                    }
                }
                this.hotelAdmins.splice(index,1);
                alert(response.data.username + " has been successfully removed.");

            })
        }    
    }
}

</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: 5%;
}
.SeperateTable {
  border-spacing: 70px;
  border-collapse: separate;
}
</style>
