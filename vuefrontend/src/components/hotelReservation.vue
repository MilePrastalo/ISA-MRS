<template>
   <div class="Search">
       <div class="hrSearch">
        <br>
       <h1>Hotel Search</h1>
       <br>
        <br>
       <div>
           <table>
                <tr>
                    <td> Enter Hotel Name: </td>
                    <td>  <input type="text" name="hotelName" v-model="hotelName" > </td>
                </tr>
                <tr>
                    <td><button v-on:click="searchHotels('name',hotelName)">Search</button> </td>
                </tr>
           </table>
           <br>
           <div v-if="currentPart === 1"> 

                <table class="table" >
                    <tr>
                        <td><b>Hotel name</b></td>
                        <td><b>Destination</b></td>
                        <td><b>Adress</b></td>
                        <td><b>Description</b></td>
                        <td><b>Options</b></td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id"> 
                        <template v-if="h.destination.name === location"> 
                        <td>{{h.name}}</td>
                        <td>{{h.destination.name}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)">Details</button> </td>
                        </template>
                    </tr>
                </table>
            </div>
            
            <div v-if="currentPart === 2">
                <hotelProfile :hotelName = "iHotelName" v-on:rr="hotelReserved"/>

            </div>
        </div>
        </div>
    </div>    
</template>

<script>
import hotelProfile from './hotelProfile.vue';
export default {
  name: 'hotelReservation',
  props: ["location","passangers"],
  components: {
      hotelProfile
  },
  data: function () {
  return {
     hotels: [],
     hotelName: "",
     nob: "",
    currentTab: 1,
    currentPart: 0,
    iHotelName: "",
    hotelID: 0

  }
},
mounted(){
    },
    methods:{
        selectTab: function(tabId){
            this.currentPart = tabId;
        },
        searchHotels: function(crit,val){
            axios.get("http://localhost:8080/api/getAllHotels/")
            .then(response => {
                this.hotels = response.data;
            });
            this.currentPart = 1;  
        },
        showDetails: function(chosenHotel) {
            this.iHotelName = chosenHotel;
            this.currentPart = 2;
        },
        hotelReserved: function(id){
            this.$emit("roomReserved",id);
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
.Search {
  text-align: center;
}
.hrSearch {
  display: inline-block;
}
</style>
