<template>
   <div id = "infoPassangers">

        <div class = "row">
            <h2>Fill in data: </h2>
        </div><br>

        <div class="row">
            <form   @submit="submitPassangers" id = "passangersForm" >
                <div class="row" >
                <table v-for="i in (seats_count-1)" :key="i">
                    <th colspan="2"> Seat: ( {{passangers[i].seat.seatRow}}, {{passangers[i].seat.seatColumn}} ) </th>
                    <tr>
                        <td>First name: </td>
                        <td> <input type="text" v-model="passangers[i].firstName" required> </td>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td> <input type="text" v-model="passangers[i].lastName" required></td>
                    </tr>
                    <tr>
                        <td>Passport:</td>
                        <td> <input type="text" v-model="passangers[i].passport" required> </td>
                    </tr>
                </table>
                </div> <br>
                <input type="submit" value="Finish">
            </form>
        </div>
        <br><br>
        <div class = "row">
            <h2>Call friends: </h2>
        </div><br>
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

    name: 'infoPassangers',
    components: {},
    props: ["iid"],
    data: function () {
        return {

            passangers: [],
            calledFriends: [],
            friends: [],
            seats_count: 0,
            curent_idx: 1,
            firstName: "",
            lastName: "",
        }
    },

    created:  function(){

        var selected_seats = JSON.parse(localStorage.getItem('selected_seats'));
        this.seats_count = selected_seats.length;

        var idx;
        for (idx = 0; idx < this.seats_count; idx++ ){

            var passanger = {seat: selected_seats[idx], firstName: "", lastName: "", passport: "", friend: false };
            this.passangers.push( passanger );
        }
    },

    mounted() {

         var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/getMyFriends",{firstName : this.firstName, lastName: this.lastName})
        .then(response => {
            this.friends = response.data;
        });
    }, 

    methods: {

        submitPassangers: function(e) { 

            e.preventDefault();
            localStorage.setItem('passangers' ,JSON.stringify(this.passangers) );
        },


        isFriendCalled: function(friend){

            var idx;
            for (idx in this.calledFriends){
                if (this.calledFriends[idx].id == friend.id){
                    return true;
                }
            }
            return false;
        },

        updateCurrentIdx: function(){

            var idx;
            for (idx= 1; idx < this.passangers.length; idx++){
                if (this.passangers[idx].firstName == "" && this.passangers[idx].lastName == "" && this.passangers[idx].passport == ""){
                    this.curent_idx = idx;
                    break;
                }
            }
        },
        
        callFriend: function(friend) {

            this.updateCurrentIdx();
            if (this.curent_idx == (this.seats_count)){
                alert("You didn't reserve enough seats!");
            }
            else{
                
                var request = {calledUserId: friend.id, flightId: this.iid, seatId: this.passangers[this.curent_idx].seat.id};
                axios.post("http://localhost:8080/api/sendReservationRequest", request)
                .then(response => {
                    if (response.data == "success"){
                        this.calledFriends.push(friend);
                        this.passangers[this.curent_idx].firstName = friend.firstName;
                        this.passangers[this.curent_idx].lastName = friend.lastName;
                        this.passangers[this.curent_idx].friend = true;
                        this.curent_idx++;
                        alert("Friend is called!");
                    }
                    else{
                        alert(response.data);
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

#passangersForm{

    text-align: left;
    width: 100%;
}

#passangersForm table{

    margin-right: 2%;
}

#passangersForm th{

    text-align: center;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
