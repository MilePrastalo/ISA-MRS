<template>
   <div id = "addHotelRooms">
            <table>
                <tr>
                    <td> Input hotel name: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="getHotel()">Get Hotel</button> </td>   
                </tr>
            </table> 
            <table v-if="this.hotel.name != null">
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
</template>

<script>

export default {
  name: 'addHotelRooms',
  components: {
  },
  data: function () {
  return {
    name: "",
    address: "",
    description: "",
    hotel: [],
    room: [],
    ac: [],
    acList: []
  }
},
    methods:{
   
        getHotel: function() {
            axios.get("http://localhost:8080/api/getHotel/"+ this.name)
            .then(response => {
                this.hotel = response.data
            })
        },
        addAC: function() {
            this.acList.push({name:this.ac.name, pricePerDay: this.ac.pricePerDay});
            this.ac.name = "";
            this.ac.pricePerDay = "";
        },
        addRoom: function() {
            this.hotel.rooms.push({roomNumber: this.room.roomNumber,numberOfBeds:this.room.numberOfBeds,pricePerDay:this.room.pricePerDay,additionalCharges:this.acList});
            this.room = [];
        },
        save: function() {
            axios.post("http://localhost:8080/api/updateHotel/",this.hotel)
            .then(response => {
                this.hotel = response.data
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
}
</style>
