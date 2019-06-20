<template>
   <div id = "myFriends">
        <div>
            <table class = "searchForm"  style="text-align: left">
                <tr>
                    <td>First name:</td>
                    <td><input type="text" v-model="firstName"></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><input type="text" v-model="lastName"></td>
                </tr>
                <tr>
                    <td><Button class= "btn btn-primary" @click="search">Search</Button></td>
                </tr>  
            </table>
            <div>
                <br><br>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Username</th>
                        <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="user in friends" :key="user.id">
                        <td>{{user.user.firstName}}</td>
                        <td>{{user.user.lastName}}</td>
                        <td>{{user.user.username}}</td>
                        <td v-if="user.status == 'Remove'" > <button class="btn btn-outline-danger" @click="removeFriend(user.user)"> {{user.status}} </button> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
    </div>
</template>

<script>
import axios from "axios";

export default {

    name: "myFriends",
    components: {},
    data: function () {
    return {
        friends: [],
        firstName: "",
        lastName: ""
    }
},

mounted(){

    var getJwtToken = function() {
        return localStorage.getItem("jwtToken");
    };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.post("/api/getMyFriends", {firstName : this.firstName, lastName: this.lastName})
        .then(response => {
            this.friends = response.data
        });
}, 

methods: {

    removeFriend: function(friend) {

        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/removeFriend/"+ friend.id)
        .then(response => {
            if (response.data == false){
                    alert("Something went wrong!");
                }
            else{
                alert("Successfuly removed!");
                axios.post("/api/getMyFriends", {firstName : this.firstName, lastName: this.lastName})
                .then(response => {
                    this.friends = response.data
                });
            }
        });
    },
    
    search: function() {

        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.post("/api/getMyFriends", {firstName : this.firstName, lastName: this.lastName})
        .then(response => {
            this.friends = response.data
        });
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
</style>
