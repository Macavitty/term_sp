import Vue from 'vue'
import Router from 'vue-router'
import Login from 'js/pages/Login.vue'
import Main from 'js/pages/Main.vue'
import Map from 'js/pages/Map.vue'
import Account from 'js/pages/Account.vue'
import CraftPage from 'js/pages/CraftPage.vue'
import Glossary from 'js/pages/Glossary.vue'
import Fight from 'js/pages/Fight.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/map',
      name: 'Map',
      component: Map
    },
    {
      path: '/account',
      name: 'Account',
      component: Account
    },
    {
      path: '/craft',
      name: 'CraftPage',
      component: CraftPage
    },
    {
      path: '/gl',
      name: 'Glossary',
      component: Glossary
    },
    {
      path: '/fight',
      name: 'Fight',
      component: Fight
    }
  ],
  mode: 'history'
})
