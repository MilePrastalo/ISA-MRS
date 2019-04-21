<template>
    <div id="officeAdmin">
        <addOffice v-if="selected == 1"></addOffice>
        <editOffice v-if="selected == 2"></editOffice>
        <table>
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Destination</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
        </table>
    </div>
</template>

<script>

import addOffice from "./addOffice.vue";
import editOffice from "./editOffice.vue";

export default {
  name: 'officeAdmin',
  components: {
    addOffice,
    editOffice
  },
  data: function(){
      return {
          selected : 1
      }
  },
  methods : {
      
  }
}

</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
table{
    border: 1px solid darkgray;
}
th{
    border: 1px solid darkgray;
}
</style>