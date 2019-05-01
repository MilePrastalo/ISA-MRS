<template>
   <div id = "hotelProfile">
            <h1>{{hotel.name}}</h1>     

            <div>
              <table>
                <th>
                  <td>Hotel Info:</td>
                </th>
                <tr>
                  <td>City: </td>
                  <td>{{this.hotel.destination.name}}</td>
                </tr>
                <tr>
                  <td>Destination description: </td>
                  <td>{{this.hotel.destination.description}}</td>
                </tr>
                <tr>
                  <td>Address: </td>
                  <td>{{this.hotel.address}}</td>
                </tr>
                <tr>
                  <td>Hotel description: </td>
                  <td>{{this.hotel.description}}</td>
                </tr>
              </table>
              <br>
              <br>
              <table border="1" >
                        <tr>
                            <td>Room Number</td>
                            <td>Number Of Beds</td>
                            <td>Price Per Day</td>
                            <td>Options</td>
                        </tr>
                    <tr v-for="r in hotel.rooms" :key="r.id">  
                        <td>{{r.roomNumber}}</td>
                        <td>{{r.numberOfBeds}}</td>
                        <td>{{r.pricePerDay}}</td>
                        <td><button @click="showDetails(r.roomNumber)">Details</button></td>
                    </tr>
                </table>
            </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'hotelProfile',
  components: {
  },
  data: function () {
  return {
    hotel : []
  }
},
mounted(){
         axios.get("http://localhost:8080/api/getHotel/"+this.$route.params.hotelName)
        .then(response => {
            this.hotel = response.data
          });   
    },
    methods:{  
      showDetails: function(chosenRoom) {
        this.$router.push("/hotelRoom/"+ this.$route.params.hotelName+ "/" + chosenRoom);
        },
        back: function() {
        this.$router.push('/');
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
