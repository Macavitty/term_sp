<template>
    <div id="app">
        <router-view/>

    </div>
</template>


<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    import BootstrapVue from 'bootstrap-vue'
    // import VueWebsocket from "vue-websocket";
    // Vue.use(VueWebsocket);

    Vue.use(BootstrapVue)
    Vue.use(VueAxios, axios)

    export default {
        name: 'App'
    }
</script>

<style>
    #app {
        height: 100%;
    }
</style>