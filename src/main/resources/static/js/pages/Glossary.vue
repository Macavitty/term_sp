<template>
  <div id="gl" >
    <AppHeader></AppHeader>
    <b-tabs content-class="mt-3 ml-4" v-model="myIndex" @input="change()" style="height: 100%;">
      <b-tab title="Существа" >
        <GlossaryBody ></GlossaryBody>
      </b-tab>
      <b-tab title="Локации">
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
      myIndex: 0 // Current tab
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
    }

  }
}
</script>

<style scoped>

</style>
