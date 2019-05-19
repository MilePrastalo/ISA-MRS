<template>
    <div id="navbar">
        <nav class="navbar navbar-expand-lg bg-dark navbar-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="http://placehold.it/150x50?text=Logo" alt="">
                    </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                    <a class="nav-link" href="#">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="#">Invites</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="./userProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                    <a @click="logout" class="nav-link">Logout</a>
                    </li>
                </ul>
                </div>
            </div>
        </nav>
    </div>
</template>
<script>

export default {
  name: 'navbar',
  methods:{
      logout:function(){
      window.location="./";
    },
  }
}
</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#navbar{
    margin-bottom: 2%;
}

</style>
