<template>
  <div id="header" class="row bg-light">
    <div id="gameName" class="col-12 col-lg-6 text-justify text-black-50 display-1 flex-row d-flex text-center justify-content-center">
      <h1 class="display-3 text-success">The Gam</h1>
      <h1 class="display-3 text-danger">e of Beasts</h1>

    </div>


    <div id="rightPanel" :key="updated" class="col-lg-3 col-6 text-black-50 d-flex flex-column justify-content-center align-items-lg-start">
      <div>
        <h4>

          <span style="font-weight: bold">{{user.name}} </span>
          <img v-bind:src="user.icon" width="50" height="50" alt="" />

            lvl: {{user.level}} <h5>
            exp: {{user.exp}}
            money: {{user.money}}
          </h5>

        </h4>
      </div>

    </div>
    <div class="col-6 col-lg-3 d-flex flex-column flex-row">
      <AppNavigation></AppNavigation>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data: function () {
    return {
      user: {
        name: user_info.nick,
        level: user_info.level,
        exp: user_info.exp,
        money: user_info.money,
      icon: user_info.icon
      },
      updated: 0
    }
  }
}
</script>

<style scoped>

</style>
