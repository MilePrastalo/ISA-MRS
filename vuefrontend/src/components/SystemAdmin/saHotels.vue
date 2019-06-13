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
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Hotel</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(4)">Add Hotel Admin</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(5)">Remove Hotel Admin</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1">
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
                    </tr>
                    </thead>
            <tr v-for="h in this.hotels" :key="h.id">  
                <td>{{h.name}}</td>
                <td>{{h.cityName}}</td>
                <td>{{h.address}}</td>
                <td>{{h.description}}</td>
                <td>{{h.rating}}</td>
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

            <div  v-if="currentTab == 3">
                <table>
                <tr>
                    <td> Enter hotel's name you want to remove: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                    <td><button v-on:click="removeHotel()" class="btn-primary">Remove Hotel</button> </td> 
                </tr>
                </table>
            </div>
            <div  v-if="currentTab == 4">
                 <table>
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
                    <td><b> Hotel Name: </b></td>
                    <td> <input type="text" v-model="hotelName" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addHotelAdmin()" class="btn-primary">Add Hotel Admin</button> </td>      
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 5"> 
                <table>
                <tr>
                    <td> Enter hotel admin's username you want to remove: </td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                    <td><button v-on:click="removeHotelAdmin()" class="btn-primary">Remove Hotel Admin</button> </td> 
                </tr>
                </table>
        </div>
   </div>
</template>

<script>

export default {
  name: 'saHotels',
  components: {
  },
  data: function () {
  return {
    hotels: [],
    admin: [],
    newHotel: {},
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getAllHotels")
            .then(response => {
                this.hotels = response.data;
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

            axios.post("http://localhost:8080/api/addHotel",this.newHotel).
            then(response =>{
                if(response.data == true) {
                    alert("Hotel has been successfully added.");
                    this.hotels.push(newHotel);
                } else {
                    alert("Input data was not correct.")
                }
                
            })
        },
        removeHotel: function() {
            axios.delete("http://localhost:8080/api/removeHotel/"+ this.name)
            .then(response => {
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addHotelAdmin: function() {
            axios.post("http://localhost:8080/api/addHotelAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,companyName:this.hotelName}).
            then(response =>{
                if(response.data === true) {
                    alert("Hotel admin has been successfully added.");
                } else {
                    alert("Username is taken.");
                }
            })
        },
        removeHotelAdmin: function() {
            axios.delete("http://localhost:8080/api/removeHotelAdmin/"+ this.admin.username)
            .then(response => {
                alert(response.data.username + " has been successfully removed.");
            })
        }    
    }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
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
