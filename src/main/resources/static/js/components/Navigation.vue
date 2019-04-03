<template>
  <!--<nav class="">-->
    <!--<transition name="fade" mode="out-in" class="p-3">-->
      <!--<i class="material-icons menu" v-if="!show" @click="show = !show" key="menu">menu </i>-->
      <!--<i class="material-icons clear" v-else @click="show = !show" key="clear">clear</i>-->
    <!--</transition>-->
    <!--<transition name="fade">-->
      <!--<ul v-if="show" class="nav-panel">-->
        <!--<li > <a href = "/map">Карта</a> </li>-->
        <!--<li> <a href = "/account">Герой</a> </li>-->
        <!--<li> <a href = "/craft">Кузница</a> </li>-->
        <!--<li> <a href = "/gl">Глоссарий</a> </li>-->
        <!--<li> <a href = "/login">Выход</a> </li>-->
      <!--</ul>-->
    <!--</transition>-->
  <!--</nav>-->
  <div class="btn-group my-auto mx-auto  ">
    <button type="button" class="btn  dropdown-toggle btn-danger w-50" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    </button>
    <div class="dropdown-menu">
      <a class="dropdown-item" href = "/map">Карта</a>
      <a class="dropdown-item" href = "/account">Герой</a>
      <!--<a class="dropdown-item" href = "/craft">Кузница</a>-->
      <a class="dropdown-item" href = "/gl">Глоссарий</a>
      <div class="dropdown-divider"></div>
      <a class="dropdown-item" href = "/logout">Выход</a>
    </div>
  </div>
</template>

<script>

  export default {
    name: 'Navigation',
    data: function(){
      return{
        show: false
      }

    }

  }
</script>

