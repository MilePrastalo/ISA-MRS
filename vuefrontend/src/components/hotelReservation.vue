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
                    <td>  <input type="text" v-model="searchHotels.hotelName" > </td>
                </tr>
                <tr>
                    <td> Enter Number Of Beds: </td>
                    <td>  <input type="number" v-model="searchHotel.numberOfBeds" > </td>
                </tr>
                <tr>
                    <td> Enter Min Price: </td>
                    <td>  <input type="number" v-model="searchHotel.minPrice" > </td>
                </tr>
                <tr>
                    <td> Enter Max Price: </td>
                    <td>  <input type="number" v-model="searchHotel.maxPrice" > </td>
                </tr>
                <tr>
                    <td> </td>
                    <td><button v-on:click="searchHotels()" class="btn-primary">Search</button> </td>
                    <td><button v-on:click="clearSearch()" class="btn-primary">Clear</button> </td>
                </tr>
           </table>
           <br>
           <div v-if="currentPart === 1"> 

                <table class="table" >
                    <tr>
                        <td><b>Hotel Name: </b></td>
                        <td><b>City: </b></td>
                        <td><b>Adress: </b></td>
                        <td><b>Description: </b></td>
                        <td><b>Options: </b></td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id"> 
                        <template> 
                        <td>{{h.name}}</td>
                        <td>{{h.cityName}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)" class="btn-primary">Details</button> </td>
                        </template>
                    </tr>
                </table>
            </div>
            
            <div v-if="currentPart === 2">
                <hotelProfile :hotelName = "iHotelName" :firstDay = "firstDay" v-on:rr="hotelReserved"/>

            </div>
        </div>
        </div>
    </div>    
</template>

<script>
import hotelProfile from "./hotelProfile.vue";
export default {
  name: "hotelReservation",
  props: ["location","passangers","firstDay"],
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
    hotelID: 0,
    searchHotel: {}

  }
},
mounted(){
    },
    methods:{
        selectTab: function(tabId){
            this.currentPart = tabId;
        },
        searchHotels: function(){

            if(this.searchHotel.hotelName == null) {
                this.searchHotel.hotelName = "";
            }
            if(this.searchHotel.numberOfBeds == null || this.searchHotel.numberOfBeds <= 0) {
                this.searchHotel.numberOfBeds = -1;
            }
            if(this.searchHotel.minPrice == null || this.searchHotel.minPrice <= 0) {
                this.searchHotel.minPrice = -1;
            }
            if(this.searchHotel.maxPrice == null || this.searchHotel.maxPrice <= 0) {
                this.searchHotel.maxPrice = -1;
            }
            this.searchHotel.cityName = this.location;
            this.searchHotel.search = 1;

            axios.post("/api/searchHotels",this.searchHotel)
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
        },
        clearSearch: function() {
            this.searchHotel = {};
            this.hotels = [];
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
}
.Search {
  text-align: center;
}
.hrSearch {
  display: inline-block;
}
</style>
