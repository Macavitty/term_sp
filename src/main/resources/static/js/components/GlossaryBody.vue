<template>
  <div class="row" style="height: 100% !important;" >
    <div id="description" class="col-12 col-lg-6  text-black py-5  text-center rounded font-italic" style="background: url('http://www.youloveit.ru/uploads/gallery/main/7/youloveit_ru_oboi_winx_tainiks04.jpg')" >
      <div v-for="(location, index) in locations" v-if="index===indexCurLocation" >
        <h4>{{location.info}}</h4>
      </div>
    </div>
    <div class="table-responsive 11 col-lg-5 ml-1 my_table scrollbar scrollbar-near-moon bordered-pink">
      <table  class="table table-condensed thead-light" >
        <tbody>
        <tr  class="border-1" id="string" v-for="(location, index) in locations" @click="indexCurLocation=index" >
          <th scope="row" :class="{'bg-light': isCurLocation(index)}">
          <h6> {{location.name}}</h6>
          </th>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GlossaryBody',
  data: function () {
    return {
      indexCurLocation: 0,
      locations: [{name: 'SPB', info: 'болото'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'},
        {name: 'Вестерос', info: 'не существует'}
      ],
      beasts: [{name: 'дракон', info: '*тяжко дыхает*', isChoosed: false}]
    }
  },
  methods: {
    isCurLocation (e) {
      return e === this.indexCurLocation
    }
  }
}
</script>

<style scoped>
  .parent {
    position: relative;
  }
  .child {
    position: absolute;
    left:0;
    right:0;
    top:0;
    bottom:0;
  }
</style>
