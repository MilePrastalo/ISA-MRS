<template>
    <div id="rentACarSearchPage">
        <navbar />
        <div class="container">
            <rentACarReservation/>
        </div>            
    </div>
</template>
<script>
import rentACarReservation from "./rentACarReservation.vue";
import axios from "axios";
import navbar from "./navbar.vue";

export default {
    name: "rentACarSearchPage",
    components: {
      rentACarReservation,navbar
    },  
}
</script>
<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
