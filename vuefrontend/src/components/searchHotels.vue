<template>
   <div id = "searchHotels">
       <h1>Hotel Search</h1>
       <br>
       <div>
        <table>
            <tr>
                <td>First day:</td>
                <td><input></td>
                <td>Last day:</td>
                <td><input></td>
            </tr>
            <tr>
                <td>Flight duration:</td>
                <td>Number of days</td>
            </tr>
            <tr>
                <td>Number of beds</td>
                <td><input></td>
            </tr>
             <tr>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>  
        </table>
        </div>
        <br>
        <div>
            <table border="1" >
            <tr>
                        <td>Hotel name</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Rating</td>
                    </tr>
            <tr v-for="h in hotels" :key="h.id">  
                <td>{{h.name}}</td>
                <td>{{h.address}}</td>
                <td>{{h.description}}</td>
                <td>{{h.rating}}</td>
            </tr>
            </table>
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
     firstDay : "",
     lastDay : "",
     minPrice : "",
     maxPrice : "",
     numberOfBeds : ""
  }
},
mounted(){
    },
    methods:{
        search: function(){
            axios.get("http://localhost:8080/api/getAllHotels")
            .then(response => {
                this.hotels = response.data;
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
