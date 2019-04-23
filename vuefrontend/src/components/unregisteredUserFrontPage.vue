<template>
    <div id="unregisteredUserFrontPage">
        <div>
            <h1 id="planjourney">PlanJourney</h1>

            <table align="center">
            <br>
            <tr>
                <td><button v-on:click="logIn()">Login</button></td>
                <td><button v-on:click="register()">Register</button></td>
            </tr>
            <br>
            <br>
        </table>
        </div>
        
    <SearchFlights />
    </div>
    
</template>

<script>
import SearchFlights from './searchFlights';

export default {
  name: 'unregisteredUserFrontPage',
  components: {
      SearchFlights
  },
  methods: {
        logIn: function() {
            this.$router.push('/loginPage');
        },
        register: function() {
            this.$router.push('/registerPage');
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
#planjourney {
    font-size: 3.175em!important;
}
</style>