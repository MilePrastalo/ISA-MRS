<template>
   <div id = "searchUsers">


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
                <tr style="text-align: center">
                        <td><div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="eco" @click="checkedFilter(1)" checked name="inlineDefaultRadiosExample">
                            <label class="custom-control-label" for="eco">All users</label>
                            </div>
                        </td>
                        <td><div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="bus" @click="checkedFilter(2)"  name="inlineDefaultRadiosExample">
                            <label class="custom-control-label" for="bus">Just my friends</label>
                            </div>
                        </td>
                </tr>
                <tr>
                    <td><Button @click="search">Search</Button></td>
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
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="user in users" :key="user.id">
                        <td>{{user.user.firstName}}</td>
                        <td>{{user.user.lastName}}</td>
                        <td>{{user.user.username}}</td>
                        <td v-if="user.status == 'Add'" > <button class="btn btn-outline-primary" @click="addFriend(user.user.id)"> {{user.status}} </button> </td>
                        <td v-if="user.status == 'Remove'" > <button class="btn btn-outline-danger" @click="removeFriend(user.user.id)"> {{user.status}} </button> </td>
                        <td v-if="user.status == 'Accept'" > <button class="btn btn-outline-success" @click="acceptRequest(user.user.id)"> {{user.status}} </button> </td>
                        <td v-if="user.status == 'Request sent'" >  {{user.status}} </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
    </div>
</template>

<script>
export default {

    name: 'searchUsers',
    components: {},
    data: function () {
    return {
        users: [],
        check: 1,
        firstName: "",
        lastName: ""
    }
},

mounted(){

    var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    var userBean = {firstName : this.firstName, lastName: this.lastName};
    axios.post("http://localhost:8080/api/getRegUsers", userBean)
        .then(response => {
            this.users = response.data
        });
}, 

methods: {

    addFriend: function(friendId) {

        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/addFriend/" + friendId)
        .then(response => {
            if (response.data == null){
                    alert("Something went wrong!");
                }
            else{
                alert("Request is successfuly sent!");
                window.location = "/friends";
            }
        });
    },
    removeFriend: function(friendId) {

        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/removeFriend/"+ friendId)
        .then(response => {
            if (response.data == false){
                    alert("Something went wrong!");
                }
            else{
                alert("Successfuly removed!");
            }
        });
    },
    acceptRequest: function(userId){

        var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/acceptRequestFromSearch/" + userId)
            .then(response => {
                if (response.data == false){
                    alert("Something went wrong!");
                }
                else{
                    alert("Successfuly accepted!");
                    window.location = "/friends";
                }
            });

    },

    checkedFilter: function(option){

        this.check = option;
    },

    search: function() {

        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        if (this.check == 1){

            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/getRegUsers", {firstName : this.firstName, lastName: this.lastName})
            .then(response => {
                this.users = response.data
            });
        }
        else{

            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/getMyFriends", {firstName : this.firstName, lastName: this.lastName})
            .then(response => {
                this.users = response.data
            });
        }
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
  margin: 5%;
}
</style>
