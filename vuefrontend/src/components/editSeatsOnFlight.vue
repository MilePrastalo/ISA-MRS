<template>
   <div id = "flightForAdmin">
          
        <div class='my-legend'>
            <div class='legend-title'> You can make seat available or unavailable:</div>
            <div class='legend-scale'>
                <ul class='legend-labels'>
                    <li><span style='background:orange;'></span>Unavailable</li>
                    <li><span style='background:salmon;'></span>Booked</li>
                     <li><span style='background:yellow;'></span>Quick reservation</li>
                </ul>
            </div>
        </div>
        <br><br>
        <div class = "row">
            <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Economic class</caption>
                    <tr v-for="r in seatsE.rows" :key ="r"> 
                        <td  v-for="c in seatsE.columns" :key ="c" v-bind:class = "{unavailable: seatsE.seats[(c-1)+(r-1)*seatsE.columns].unavailable, booked: seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken, quick: seatsE.seats[(c-1)+(r-1)*seatsE.columns].quick}" @click="modify(seatsE.seats[(c-1)+(r-1)*seatsE.columns])">
                            ( {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}} , {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )	
                        </td> 
                    </tr>
                </table>
            <table class = "seats">
                <caption style="caption-side: top; font-weight: bold">Business class</caption>
                <tr v-for="r in seatsB.rows" :key ="r"> 
                    <td  v-for="c in seatsB.columns" :key ="c" v-bind:class = "{unavailable: seatsB.seats[(c-1)+(r-1)*seatsB.columns].unavailable, booked: seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken, quick: seatsB.seats[(c-1)+(r-1)*seatsB.columns].quick}" @click="modify(seatsB.seats[(c-1)+(r-1)*seatsB.columns])">
                        ( {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}} , {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )	
                    </td>
                </tr>
            </table>
            <table class = "seats">
                <caption style="caption-side: top; font-weight: bold">First class</caption>
                <tr v-for="r in seatsF.rows" :key ="r"> 
                    <td  v-for="c in seatsF.columns" :key ="c" v-bind:class = "{unavailable: seatsF.seats[(c-1)+(r-1)*seatsF.columns].unavailable, booked: seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken, quick: seatsF.seats[(c-1)+(r-1)*seatsF.columns].quick}" @click="modify(seatsF.seats[(c-1)+(r-1)*seatsF.columns])">
                        ( {{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}} ,{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )	
                    </td>
                </tr>
            </table>       
        </div> 
         
    </div>
</template>

<script>

export default {
  name: 'flightForAdmin',
  components: {},
  props: ["iid"],
  data: function () {
    return {
        flightID: this.iid,
        seatsE: {},
        seatsB: {},
        seatsF: {},
        }
    },
    mounted(){
    
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  this.iid + "/economic")
        .then(response => {
            this.seatsE = response.data;
        });; 
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  this.iid + "/business" )
        .then(response => {
            this.seatsB = response.data;
        });
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  this.iid + "/first class" )
        .then(response => {
            this.seatsF = response.data;
        });
    },

    methods: {
    
        modify: function(seat){
            
            if (seat.booked == false && seat.quick == false){
                var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
                axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                axios.get("http://localhost:8080/api/editSeat/" + seat.id)
                .then(response => {
                    if (response.data != null){
                        if (seat.unavailable == true){
                            seat.unavailable = false;
                        }
                        else{
                            seat.unavailable = true;
                        }
                    }
                });     
            } 
        }
    }     
    
}

</script>

<style>
.seats{
    border-collapse: separate;
    border-spacing: 5px 1rem;
    margin-right: 5%;
}
.seats td {
    border: 2px solid gray;
    border-radius: 20%;    
}

.seats td:visited {
    border: 2px solid gray;
}

.seats td:hover {
    cursor: default;
    background-color: gray;
}

.seats .unavailable {
    background-color: orange;
    border-radius: 5px;
}

.seats .booked {
    background-color: salmon;
}
.seats .quick {
    background-color: yellow;
    border-radius: 5px;
}

 .my-legend .legend-title {
    text-align: left;
    margin-bottom: 5px;
    font-weight: bold;
    font-size: 90%;
    }
  .my-legend .legend-scale ul {
    margin: 0;
    margin-bottom: 5px;
    padding: 0;
    float: left;
    list-style: none;
    }
  .my-legend .legend-scale ul li {
    font-size: 80%;
    list-style: none;
    margin-left: 0;
    line-height: 18px;
    margin-bottom: 2px;
    }
  .my-legend ul.legend-labels li span {
    display: block;
    float: left;
    height: 16px;
    width: 30px;
    margin-right: 5px;
    margin-left: 0;
    border: 1px solid #999;
    }
  .my-legend .legend-source {
    font-size: 70%;
    color: #999;
    clear: both;
    }
  .my-legend a {
    color: #777;
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
