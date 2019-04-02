<template>
  <nav>
    <transition name="fade" mode="out-in">
      <i class="material-icons menu" v-if="!show" @click="show = !show" key="menu">menu </i>
      <i class="material-icons clear" v-else @click="show = !show" key="clear">clear</i>
    </transition>
    <transition name="fade">
      <ul v-if="show">
        <li> <a href = "/map">Карта</a> </li>
        <li> <a href = "/account">Герой</a> </li>
        <li> <a href = "/craft">Кузница</a> </li>
        <li> <a href = "/gl">Глоссарий</a> </li>
        <li> <a href = "/login">Выход</a> </li>
      </ul>
    </transition>
  </nav>
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
