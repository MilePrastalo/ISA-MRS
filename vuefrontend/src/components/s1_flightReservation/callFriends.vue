<template>
   <div id = "callFriends">

        <div class = "row">
            <h2>Call friends: </h2>
        </div><br>

        <div v-if= "calledFriends.length != 0" class = "row" id = "infoArea">
            <div>
                <h3> You called: (<span id="counter"> {{calledFriends.length}} </span>):</h3>
                <ul  id = "selectedSeats">
                    <li  v-for="friend in calledFriends" :key ="friend.id"> {{friend.firstName}} {{friend.lastName}} </li>
                </ul>
            </div>
        </div>

        <br>
        <div class = "row">

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
                    <td><Button @click="search">Search</Button></td>
                </tr> 
             </table>

            <table class="table">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Username</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in friends" :key="user.id">
                    <td>{{user.user.firstName}}</td>
                    <td>{{user.user.lastName}}</td>
                    <td>{{user.user.username}}</td>
                    <td v-if="isFriendCalled(user.user) == false"> <button @click="callFriend(user.user)">Call</button> </td>
                    <td v-else > Called </td>
                    </tr>
                </tbody>
            </table> 
        </div>         
    </div>
</template>

<script>
export default {

    name: 'callFriends',
    components: {},
    data: function () {
        return {

            friends: [],
            calledFriends: [],
            firstName: "",
            lastName: "",
        }
    },

    mounted(){

        var flightID = 6;
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/getMyFriends",{firstName : this.firstName, lastName: this.lastName})
        .then(response => {
            this.friends = response.data;
        });;
    
    }, 
    methods: {
        
        isFriendCalled: function(friend){

            var idx;
            for (idx in this.calledFriends){
                if (this.calledFriends[idx].id == friend.id){
                    return true;
                }
            }
            return false;
        },
        
        callFriend: function(friend) {

            var selected = localStorage.getItem("selected_seats");
            if (this.calledFriends.length == selected.length){
                alert("You didn't reserve enough seats!");
            }
            else{

                axios.post("http://localhost:8080/api/sendReservationRequest", {firstName : friend.firstName, lastName: friend.lastName, id: friend.id, email: friend.email})
                .then(response => {
                    if (response.data == "success"){
                        this.calledFriends.push(friend);
                        alert("Friend is called!");
                        localStorage.setItem("called_friends", this.calledFriends );
                    }
                    else{
                        alert("Something wrong happend!");
                    }
                });
                
            }
        },

        search: function() {

            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/getMyFriends", {firstName : this.firstName, lastName: this.lastName})
            .then(response => {
                this.friends = response.data
            });
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
  margin: 5%
}
</style>
