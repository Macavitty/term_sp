import Vue from 'vue'
// import Vuex from 'vuex'
// import VTooltip from 'v-tooltip'

// Vue.use(Vuex)
// Vue.use(VTooltip)
//
// export default new Vuex.Store({
//         state: {
//             results: []
//         },
//         mutations: {
//             addResult(state, result) {
//                 state.results.push(result)
//             },
//             newResults(state, results) {
//                 state.results = results;
//             }
//         },
//         actions: {
//             asyncAddResult({commit}, result) {
//                 Vue.http.post('http://localhost:3000/backResults', result).then(response => {
//                     commit('addResult', response.data);
//                 });
//             },
//             asyncNewResults({commit}) {
//                 Vue.http.get('http://localhost:3000/backResults').then(response => {
//                     commit('newResults', response.data);
//                 });
//             }
//         },
//         getters: {
//             results(state) {
//                 return state.results
//             }
//         }
//     }
// )
