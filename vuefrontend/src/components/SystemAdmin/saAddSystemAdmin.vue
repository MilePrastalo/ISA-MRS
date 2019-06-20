<template>
   <div id = "saAddSystemAdmin" class="container">
       <br>
       <div class="row centered">
       <table>
                <tr>
                    <td><b> Username: </b></td>
                    <td>  <input type="text" v-model="newSysAdmin.username" > </td>
                </tr>
                <tr>
                    <td><b> Password: </b></td>
                    <td>  <input type="text" v-model="newSysAdmin.password" > </td>
                </tr>
                <tr>
                    <td><b> First Name: </b></td>
                    <td> <input type="text" v-model="newSysAdmin.firstName" > </td>        
                </tr>
                <tr>
                    <td><b> Last Name: </b></td>
                    <td> <input type="text" v-model="newSysAdmin.lastName" > </td>        
                </tr>
                <tr>
                    <td><b> Email: </b></td>
                    <td> <input type="text" v-model="newSysAdmin.email" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addSystemAdmin()" class="btn-primary">Add System Admin</button> </td>      
                </tr>
        </table>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "saAddSystemAdmin",
  components: {
  },
  data: function () {
  return {
    newSysAdmin: {}
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    },
    methods:{
        addSystemAdmin: function() {
            if(this.newSysAdmin.username == "") {
                alert("Username must not be empty.");
                return;
            }
            if(this.newSysAdmin.firstName == "") {
                alert("Firstname must not be empty.");
                return;
            }
            if(this.newSysAdmin.lastName == "") {
                alert("Lastname must not be empty.");
                return;
            }
            if(this.newSysAdmin.email == "") {
                alert("Email must not be empty.");
                return;
            }
            if(this.newSysAdmin.password == "") {
                alert("Password must not be empty.");
                return;
            }

            axios.post("/api/addSystemAdmin",this.newSysAdmin)
            .then(response => {
                if(response.data == false) {
                    alert("Username is taken.");
                    return;
                } else {
                    alert("System admin has been successfully added.");
                    this.newSysAdmin = {};
                    return;
                }
            })
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
  margin-left: 5%;
}
</style>
