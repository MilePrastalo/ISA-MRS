<template>
   <div id = "saEditDiscounts">
       <br>
        <br>
        <div v-if="editing == false">
            <table class="centered table">
                    <tr>
                        <td><b> Minimum Hotel Reservations: </b></td>
                        <td>  {{discounts.numberOfHotelReservations}} </td>
                    </tr>
                    <tr>
                        <td><b> Hotel Discount: </b></td>
                        <td>  {{discounts.hotelDiscount}} </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td><b> Minimum Rent A Car Reservations: </b></td>
                        <td>  {{discounts.numberOfRACReservations}} </td>
                    </tr>
                    <tr>
                        <td><b> Rent A Car Discount: </b></td>
                        <td>  {{discounts.rentACarDiscount}} </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td><b> Minimum Flight Reservations: </b></td>
                        <td>  {{discounts.numberOfFlightReservations}} </td>
                    </tr>
                    <tr>
                        <td><b> Flight Discount: </b></td>
                        <td>  {{discounts.flightDiscount}} </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateDiscountsDiv()" class="btn-primary">Edit</button> </td>
                    </tr>
            </table>
        </div>

        <div v-if="editing == true">
            <table class="centered table">
                    <tr>
                        <td><b> Minimum Hotel Reservations: </b></td>
                       <td>  <input type="number"  v-model="discounts.numberOfHotelReservations" > </td>
                    </tr>
                    <tr>
                        <td><b> Hotel Discount: </b></td>
                        <td>  <input type="number" v-model="discounts.hotelDiscount" > </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td><b> Minimum Rent A Car Reservations: </b></td>
                        <td>  <input type="number" v-model="discounts.numberOfRACReservations" > </td>
                    </tr>
                    <tr>
                        <td><b> Rent A Car Discount: </b></td>
                        <td>  <input type="number" v-model="discounts.rentACarDiscount" > </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td><b> Minimum Flight Reservations: </b></td>
                        <td>  <input type="number" v-model="discounts.numberOfFlightReservations" > </td>
                    </tr>
                    <tr>
                        <td><b> Flight Discount: </b></td>
                        <td>  <input type="number" v-model="discounts.flightDiscount" > </td>
                    </tr>
            </table>
            <br>
            <table class="table">
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateDiscounts()" class="btn-primary">Save</button> </td>
                        <td><button v-on:click="cancel()" class="btn-primary">Cancel</button> </td>
                    </tr>
            </table>
        </div>

    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "saEditDiscounts",
  components: {
  },
  data: function () {
  return {
    discounts: {},
    oldDiscounts: {},
    editing: false
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getDiscounts")
            .then(response => {
                this.discounts = response.data;
            });
    },
    methods:{
        updateDiscountsDiv: function() {
            this.oldDiscounts = this.discounts;
            this.editing = true;
        },
        updateDiscounts: function() {
            if(this.discounts.numberOfHotelReservations < 0) {
                alert("Number of hotel reservations must be 0 or grater.");
                return;
            }
            if(this.discounts.hotelDiscount < 0) {
                alert("Hotel discount must be 0 or grater.");
                return;
            }
            if(this.discounts.numberOfRACReservations < 0) {
                alert("Number of rent a car reservations must be 0 or grater.");
                return;
            }
            if(this.discounts.rentACarDiscount < 0) {
                alert("Rent a car discount must be 0 or grater.");
                return;
            }
            if(this.discounts.numberOfFlightReservations < 0) {
                alert("Number of flight reservations must be 0 or grater.");
                return;
            }
            if(this.discounts.flightDiscount < 0) {
                alert("Flight discount must be 0 or grater.");
                return;
            }

            axios.post("http://localhost:8080/api/editDiscounts",this.discounts)
            .then(response => {
                if(response.data == true) {
                    alert("Discounts were successfully edited.");
                    this.editing = false;
                } else {
                    alert("There was a mistake while editing discounts.");
                }
            });
        },
        cancel: function() {
            this.discounts = this.oldDiscounts;
            this.editing = false;
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

.centered{
    margin-right: auto;
    margin-right: auto;
}
</style>
