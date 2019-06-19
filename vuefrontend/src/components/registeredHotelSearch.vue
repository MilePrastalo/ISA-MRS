<template>
   <div id = "registeredHotelSearch">
        <navbar />
       <div class="container">
          
                <search-hotels class="container"></search-hotels>
        </div>
        </div>
</template>

<script>

import searchHotels from "./searchHotels.vue"
import navbar from "./navbar.vue";

export default {
  name: "registeredHotelSearch",
  components: {
      searchHotels: searchHotels,
      navbar
  },
  data: function () {
  return {
  }
},
mounted(){
    },
    methods:{
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
</style>
