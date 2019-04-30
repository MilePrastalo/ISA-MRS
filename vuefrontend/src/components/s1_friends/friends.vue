<template>
   <div id = "friends">

       <div class="row">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a  class="nav-link active" id = "searchUsers" href="#" @click="selectTab(1)">Search users</a>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" id = "friendRequests" href="#" @click="selectTab(2)">Friend requests</a>     
                 </li>
                <li class="nav-item">
                    <a  class="nav-link" id = "myFriends"  href="#" @click="selectTab(3)">My friends</a>
                </li>
            </ul>
        </div>

        <div v-if="currentTab == 1">
            <br><br>
            <searchUsers></searchUsers>
        </div>
        <div v-if="currentTab == 2">
            <br><br>
            <friendRequests></friendRequests>
        </div>
        <div v-if="currentTab == 3">
            <br><br>
            <myFriends></myFriends>
        </div>
    </div>
</template>

<script>
import SearchUsers from './searchUsers.vue';
import FriendRequests from './friendRequests.vue';
import MyFriends from './myFriends.vue';

export default {

    name: 'friends',
    components: {
        searchUsers: SearchUsers,
        friendRequests: FriendRequests,
        myFriends: MyFriends
    },
    data: function () {
        return {
            currentTab: 1   
        }
    },
    mounted(){
    
    }, 

    methods: {

        selectTab: function(tabId){
            if (tabId == 1){
                document.getElementById("searchUsers").className="nav-link active";
                document.getElementById("friendRequests").className="nav-link";
                document.getElementById("myFriends").className="nav-link";
            }
            else if (tabId == 2){
                document.getElementById("searchUsers").className="nav-link";
                document.getElementById("friendRequests").className="nav-link active";
                document.getElementById("myFriends").className="nav-link";
            }
            else if (tabId == 3){
                document.getElementById("searchUsers").className="nav-link";
                document.getElementById("friendRequests").className="nav-link";
                document.getElementById("myFriends").className="nav-link active";
            }
            this.currentTab = tabId;
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
