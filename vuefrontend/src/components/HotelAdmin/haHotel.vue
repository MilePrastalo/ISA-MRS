<template>
   <div id = "haHotel" v-if="hotel">
       <div class="container">
            <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)" >Hotel Profile</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)" >Rooms</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)" >Add Room</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(4)">Quick Reservations </a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(5)" >Add Quick Reservation</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(6)" >Reports</a>
                    </li>

                </ul>
            </div>
            
            <div v-if="currentTab == 1 && editing == false" class="centered">
            <br>
            <table class="centered">
                    <tr>
                        <td><b> Name: </b></td>
                        <td>  {{hotel.name}} </td>
                    </tr>
                    <tr>
                        <td><b> City: </b></td>
                        <td>  {{hotel.cityName}} </td>
                    </tr>
                    <tr>
                        <td><b> Description: </b></td>
                        <td> {{hotel.description}}</td>        
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateHotelProfileDiv()" class="btn-primary">Edit</button> </td>        
                    </tr>
            </table>
            </div>
            <div v-if="currentTab == 1 && editing == true" class="centered">
            <table class="centered">
                <tr>
                        <td><b> Name: </b></td>
                        <td><input type="text" v-model="hotel.name" ></td>
                    </tr>
                    <tr>
                        <td><b> Description: </b></td>
                        <td><input type="text" v-model="hotel.description" ></td>   
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateHotelProfile()" class="btn-primary">Save</button> </td> 
                        <td><button v-on:click="cancel()" class="btn-primary">Cancel</button> </td>         
                    </tr>
            </table>
            </div> 

            <div  v-if="currentTab == 2" class="centered"> 
                <br>
                    <table  class="table centered">
                        <thead class="thead-dark">
                            <tr>
                            <th>Room Number</th>
                            <th>Number Of Beds</th>
                            <th>Price Per Day</th>
                        </tr>
                        </thead>
                        
                    <tr v-for="r in hotel.rooms" :key="r.id">  
                        <td>{{r.roomNumber}}</td>
                        <td>{{r.numberOfBeds}}</td>
                        <td>{{r.pricePerDay}}</td>
                    </tr>
                </table>
            </div>
            <div  v-if="currentTab == 3" class="centered"> 
                <br>
                   <table class="centered">
                <tr>
                    <td> Input room number: </td>
                    <td>  <input type="int" room.roomNumber="room.roomNumber" v-model="room.roomNumber" > </td>
                </tr>
                <tr>
                    <td> Input number of beds: </td>
                    <td>  <input type="int" room.numberOfBeds="room.numberOfBeds" v-model="room.numberOfBeds" > </td>
                </tr>
                <tr>
                    <td> Input price per day: </td>
                    <td>  <input type="float" room.pricePerDay="room.pricePerDay" v-model="room.pricePerDay" > </td>
                </tr>
                <br>
                <br>
                <tr>
                    <td> Input name of additional charge: </td>
                    <td>  <input type="text" ac.name="ac.name" v-model="ac.name" > </td>
                </tr>
                <tr>
                    <td> Input price of additional charge per day: </td>
                    <td>  <input type="float" ac.pricePerDay="ac.pricePerDay" v-model="ac.pricePerDay" > </td>
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addAC()" class="btn-primary">Add additional charges</button> </td>   
                </tr>
                <br>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addRoom()" class="btn-primary">Add room</button> </td>   
                </tr>
            </table>      
            </div>
            <div v-if="currentTab == 4">
                 <br>
          <table class="table centered">
              <thead class="thead-dark">
                  <tr>
        <th>Room Number</th>
        <th>Number Of Beds</th>
        <th>First Day</th>
        <th>Last Day</th>
        <th>Original Price</th>
        <th>Discount</th>
        <th>Discounted Price</th>
        <th>Options</th>
      </tr>
              </thead>
      
      <tr v-for="r in quickReservations" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.fDay +"."+r.fMonth +"."+r.fYear+"."}}</td>
        <td>{{r.lDay +"."+r.lMonth +"."+r.lYear+"."}}</td>
        <td>{{r.paidPrice}}</td>
        <td>{{r.discount}}</td>
        <td>{{parseFloat(r.paidPrice) - parseFloat(r.paidPrice) * (parseFloat(r.discount) / 100)}}</td>
        <td>
          <button @click="cancelQuickReservation(r)" class="btn-primary">Cancel</button>
        </td>
      </tr>
    </table>
            </div>
            <div v-if="currentTab == 5">
                <ha-quick-reservation :hotel="hotel"></ha-quick-reservation>
            </div>
             <div v-if="currentTab == 6">
                <ha-reports :hotel="hotel"></ha-reports>
            </div>
   </div>
   </div>
</template>

<script>

import haQuickReservation from "./haQuickReservation.vue"
import haReports from "./haReports.vue"
import { constants } from 'crypto';

export default {
  name: 'haHotel',
  props: {
      hotel: Object
  },
  components: {
      haQuickReservation: haQuickReservation,
      haReports: haReports
  },
  data: function () {
  return {
    room: {},
    ac: [],
    acList: [],
    quickReservations: [],
    currentTab: 1,
    editing: false,
    oldHotel: {}
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
            if(tabId === 4) {
                this.findQuickReservations();
            }
        },
        addAC: function() {
            this.acList.push({name:this.ac.name, pricePerDay: this.ac.pricePerDay});
            this.ac.name = "";
            this.ac.pricePerDay = "";
        },
        addRoom: function() {
            this.room.additionalCharges = this.acList;
            console.log(this.room)
            axios.post("http://localhost:8080/api/addHotelRoom/",this.room)
            .then(response => {
                alert(response.data);
            })
            this.hotel.rooms.push({roomNumber: this.room.roomNumber,numberOfBeds:this.room.numberOfBeds,pricePerDay:this.room.pricePerDay,additionalCharges:this.acList,rating:0});
            this.room = {};
        },
        findQuickReservations: function() {
            this.quickReservations = [];
            this.hotel.reservations.forEach(element => {
            if(element.username === null) {
                this.quickReservations.push(element);
            }
            
        });
        },
        cancelQuickReservation: function(r) {
            axios.post("http://localhost:8080/api/cancelQuickHotelReservation",r)
            .then(response => {
                if(response.data == true) {
                    alert("Quick reservation has been successfully canceled.");
                    this.quickReservations.forEach(element => {
                        if(element.id == r.id) {
                            this.quickReservations.splice(r,1);
                        }
                    });
                    this.hotel.reservations.forEach(element => {
                            if(element.id == r.id) {
                                this.hotel.reservations.splice(r,1)
                            }
                    });
                } else {
                    alert("There was a problem with canceling quick reservation.");
                }
            });
        },
        updateHotelProfileDiv: function() {
            this.oldHotel.name = this.hotel.name;
            this.oldHotel.description = this.hotel.description;
            this.editing = true;
        },
        cancel: function() {
            this.hotel.name = this.oldHotel.name;
            this.hotel.description = this.oldHotel.description;
            this.editing = false;
        },
        updateHotelProfile: function() {
            if(this.hotel.name == "") {
                alert("Hotel name must not be empty.")
            }
            axios.post("http://localhost:8080/api/updateHotelProfile",{oldName:this.oldHotel.name,newName:this.hotel.name,cityName:this.hotel.cityName,newDescription:this.hotel.description})
            .then(response => {
                if(response.data == true) {
                    alert("Hotel profile update was successful.");
                    this.editing = false;
                } else {
                    this.hotel.name = this.oldHotel.name;
                    this.hotel.description = this.oldHotel.description;
                    alert("Hotel profile update failed.")
                }
            });
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
}
.centered{
    margin-left: auto;
    margin-right: auto;
}
</style>
