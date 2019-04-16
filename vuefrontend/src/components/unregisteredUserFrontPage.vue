<template>
    <div id="unregisteredUserFrontPage">
        <table>
            <tr>
                <td><a href="./loginPage">LogIn</a></td>
            </tr>
            <tr>
                <td><a href="./registerPage">Register</a></td>
            </tr>
        </table>
    <SearchFlights />
    </div>
    
</template>

<script>
import SearchFlights from './searchFlights';

export default {
  name: 'unregisteredUserFrontPage',
  components: {
      SearchFlights
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