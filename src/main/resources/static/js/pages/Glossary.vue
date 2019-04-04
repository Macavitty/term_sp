<template>
  <div id="gl" >
    <AppHeader></AppHeader>
    <b-tabs content-class="mt-3 ml-4" v-model="myIndex" @input="change()" style="height: 100%;">
      <b-tab title="Существа" >
        <div class="row" style="height: 100% !important;" >
          <div  class="col-12 col-lg-6  text-black py-5  text-center rounded font-italic" style="background: url('http://www.youloveit.ru/uploads/gallery/main/7/youloveit_ru_oboi_winx_tainiks04.jpg')" >
            <div v-for="(beast, index) in beasts" v-if="index===indexCurLocation" >
              <h4>{{beast.description}}</h4>
            </div>
          </div>
          <div class="table-responsive 11 col-lg-5 ml-1 my_table scrollbar scrollbar-near-moon bordered-pink">
            <table  class="table table-condensed thead-light" >
              <tbody>
              <tr  class="border-1 string" v-for="(beast, index) in beasts" @click="indexCurLocation=index" >
                <th scope="row" :class="{'bg-light': isCurLocation(index)}">
                  <h6> {{beast.name}}</h6>
                </th>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </b-tab>
      <b-tab title="Локации">
        <div class="row" style="height: 100% !important;" >
          <div  class="col-12 col-lg-6  text-black py-5  text-center rounded font-italic" style="background: url('http://www.youloveit.ru/uploads/gallery/main/7/youloveit_ru_oboi_winx_tainiks04.jpg')" >
            <div v-for="(location, index) in locations" v-if="index===indexCurLocation" >
              <h4>{{location.description}}</h4>
            </div>
          </div>
          <div class="table-responsive 11 col-lg-5 ml-1 my_table scrollbar scrollbar-near-moon bordered-pink">
            <table  class="table table-condensed thead-light" >
              <tbody>
              <tr  class="border-1 string" v-for="(location, index) in locations" @click="indexCurLocation=index" >
                <th scope="row" :class="{'bg-light': isCurLocation(index)}">
                  <h6> {{location.name}}</h6>
                </th>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </b-tab>
    </b-tabs>
  </div>

</template>


<script>/*eslint-disable*/
import GlossaryBody from 'js/components/GlossaryBody.vue'
export default {
  name: 'Glossary',
  components: {GlossaryBody},
  data () {
    return {
      myIndex: 0, // Current tab
      indexCurLocation: 0,
      locations: another.locs,
      beasts: another.all_creatures
    }
  },
  mounted () {
    // Get the previous tab from the url
    this.myIndex = parseInt(window.location.hash.replace('#', ''), 10);
  },
  methods: {
    change () {
      // Save the current tab in url
      window.location.hash = this.myIndex;
    },
    isCurLocation (e) {
      return e === this.indexCurLocation
    }
  }
}
</script>

<style scoped>

</style>
