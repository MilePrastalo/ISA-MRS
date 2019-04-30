<template>
   <div id = "friendRequests">

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
                    <tr v-for="reqest in requests" :key="reqest.id">
                        <td>{{reqest.firstName}}</td>
                        <td>{{reqest.lastName}}</td>
                        <td>{{reqest.username}}</td>
                        <td v-if="reqest.accepted == false" > <button class="btn btn-outline-success" @click="acceptRequest(reqest)"> Accept </button> </td>
                        <td v-if="reqest.accepted == true" > <button class="btn btn-outline-success" @click="acceptRequest(reqest)"> Accept </button> </td>
                    </tr>
                </tbody>
            </table>    
        </div>
        
    </div>
</template>

<script>
export default {

    name: 'friendRequests',
    components: {},
    data: function () {
    return {
        requests: []
    }
},

mounted(){

    var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getMyRequests")
        .then(response => {
            this.requests = response.data
        });
}, 

methods: {

    
    acceptRequest: function(request){

        var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/acceptRequest/" + request.id)
            .then(response => {
                if (response.data == false){
                    alert("Something went wrong!");
                }
                else{
                    alert("Successfuly accepted!");
                    request.accepted = false;
                     axios.get("http://localhost:8080/api/getMyRequests")
                    .then(response => {
                        this.requests = response.data
                    });
                }
            });
    },
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
