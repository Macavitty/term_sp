// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import VTooltip from 'v-tooltip'
// import { TooltipPlugin } from "@syncfusion/ej2-vue-popups";
import App from 'App.vue'
import router from 'js/router/router'
import 'css/main.css'
import 'css/map.css'
import 'css/nav.css'
// import 'css/style.css'
import 'css/tooltip.css'
import store from './js/store'
import AppFooter from 'js/components/Footer.vue'
import AppHeader from 'js/components/Header.vue'
import AppNavigation from 'js/components/Navigation.vue'
// import ModalLogin from 'js/components/ModalLogin.vue'
// import VueResource from 'vue-resource'

// Vue.use(VTooltip)
// Vue.use(TooltipPlugin);

Vue.config.productionTip = false
/* eslint-disable*/
var myMixin = {
    components: {
        AppHeader,
        AppFooter,
        AppNavigation
        // ModalLogin
    },
    methods: {

    }
}

Vue.mixin(myMixin)
new Vue({
    // store,
    router,
    render: h => h(App)
}).$mount('#app')