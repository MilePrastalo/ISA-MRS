<template>
   <div id = "haHotel">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)" >Hotel Profile</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)" >Rooms</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)" >Add Room</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(4)" >Add Quick Reservation</a>
                    </li>

                </ul>
            </div>
            
            <div v-if="currentTab == 1">

            <table border="1" >
                <tr>
                        <td> Name </td>
                        <td>  <input type="text" name="hotel.name" v-model="hotel.name"> </td>
                    </tr>
                    <tr>
                        <td> Destination: </td>
                        <td>  <input type="text" name="hotel.destination.name" v-model="hotel.destination.name" > </td>
                    </tr>
                    <tr>
                        <td> Destination description: </td>
                        <td> <input type="text" name="hotel.destination.description"  v-model="hotel.destination.description" > </td>        
                    </tr>
                    <tr>
                        <td> Hotel description: </td>
                        <td> <input type="text" name="hotel.description"  v-model="hotel.description" > </td>        
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateSystemAdminProfile()">Edit</button> </td>        
                    </tr>
            </table>
            </div>

            <div  v-if="currentTab == 2"> 

                    <table border="1" >
                        <tr>
                            <td>Room Number</td>
                            <td>Number Of Beds</td>
                            <td>Price Per Day</td>
                        </tr>
                    <tr v-for="r in hotel.rooms" :key="r.id">  
                        <td>{{r.roomNumber}}</td>
                        <td>{{r.numberOfBeds}}</td>
                        <td>{{r.pricePerDay}}</td>
                    </tr>
                </table>
            </div>
            <div  v-if="currentTab == 3"> 
                   <table>
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
                    <td><button v-on:click="addAC()">Add additional charges</button> </td>   
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addRoom()">Add room</button> </td>   
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="save()">Save changes</button> </td>   
                </tr>
            </table>      
            </div>
            <div v-if="currentTab == 4">
                <ha-quick-reservation :hotel="hotel"></ha-quick-reservation>
            </div>
   </div>
</template>

<script>

import haQuickReservation from "./haQuickReservation.vue"

export default {
  name: 'haHotel',
  props: {
      hotel: Object
  },
  components: {
      haQuickReservation: haQuickReservation
  },
  data: function () {
  return {
    room: [],
    ac: [],
    acList: [],
    currentTab: 1
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
        },
        addAC: function() {
            this.acList.push({name:this.ac.name, pricePerDay: this.ac.pricePerDay});
            this.ac.name = "";
            this.ac.pricePerDay = "";
        },
        addRoom: function() {
            this.hotel.rooms.push({roomNumber: this.room.roomNumber,numberOfBeds:this.room.numberOfBeds,pricePerDay:this.room.pricePerDay,additionalCharges:this.acList,rating:0});
            this.room = [];
        },
        save: function() {
            axios.post("http://localhost:8080/api/updateHotel/",this.hotel)
            .then(response => {
                alert("Done.");
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
</style>
