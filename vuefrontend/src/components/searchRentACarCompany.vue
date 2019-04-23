<template>
  <div id="searchRentACarCompany">
    <div>
        <table>
            <tr>
                <td>Rent a company name:</td>
                <td><input v-model="name" type="text"></td>
                <td>Location</td>
                <td><input v-model="location" type="text"></td>
            </tr>
            <tr>
                <td>Date from</td>
                <td><input v-model="datefrom" type="date"></td>
                <td>Date to</td>
                <td><input v-model="dateTo" type="date"></td>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>
        </table>
    </div>
    <div>
        <table >
            <tr v-for="company in companies" :key="company.name">  
                <td>{{company.name}}</td>
                <td><ul>
                    <li v-for="office in company.offices" :key="office">{{office}}</li>
                    </ul></td>
                <td>{{company.rating}}</td>
            </tr>
        </table>
    </div>
  </div>
</template>

<script>

export default {
  name: 'v',
  components: {
  },
  data: function () {
  return {
    companies:[],
    name:"",
    location:"",
    datefrom:"",
    dateTo:""
  }
},
mounted(){
    
    },
    methods:{
           search:function(){
               var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
                axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
               axios.post("http://localhost:8080/api/getRentACarCompanies",{name : this.name, location: this.location, datefrom:this.datefrom, dateTo:this.dateTo})
                .then(response => {
                    this.companies = response.data
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
}
</style>
