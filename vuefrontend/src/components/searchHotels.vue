<template>
   <div id = "searchHotels">
       <h2>Hotel Search</h2>
       <br>
        <br>
       <div>
           <table>
                <tr>
                    <td> Enter Hotel Name: </td>
                    <td>  <input type="text" v-model="searchHotel.hotelName" > </td>
                </tr>
                <tr>
                    <td> Enter City Name: </td>
                    <td>  <input type="text" v-model="searchHotel.cityName" > </td>
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
           <br>
           <div> 

                <table class="table" >
                    <thead class="thead-dark">
                        <tr>
                        <th>Hotel Name</th>
                        <th>City</th>
                        <th>Adress</th>
                        <th>Description</th>
                        <th>Options</th>
                        </tr>
                    </thead>
                    <tr v-for="h in hotels" :key="h.id">  
                        <td>{{h.name}}</td>
                        <td>{{h.cityName}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)" class="btn-primary">Details</button> </td>
                    </tr>
                </table>
            </div>
        </div>    
   </div>
</template>

<script>

export default {
  name: 'searchHotels',
  components: {
  },
  data: function () {
  return {
     hotels: [],
     searchHotel: {},
     hotelName: "",
     destination: "",
     nob: "",

  }
},
mounted(){
    },
    methods:{
        searchHotels: function(){
            if(this.searchHotel.hotelName == null) {
                this.searchHotel.hotelName = "";
            }
            if(this.searchHotel.cityName == null) {
                this.searchHotel.cityName = "";
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

            axios.post("http://localhost:8080/api/searchHotels",this.searchHotel)
            .then(response => {
                this.hotels = response.data;
            })  
        },
        showDetails: function(chosenHotel) {
            window.location ="./unregisteredHotelProfile/"+ chosenHotel;
        },
        clearSearch: function() {
            this.hotels = [];
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
