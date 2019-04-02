<template>
  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="height: 100px; background-color: #1d2124">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <slot name="data"></slot>
      <div class="carousel-item active text-center">
        <h1>Terct1</h1>
      </div>
      <div class="carousel-item text-center">
        <h1>Terct2</h1>
      </div>
      <div class="carousel-item text-center">
        <h1>Terct3</h1>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</template>

<script>
  export default {
    name: 'MyCarousel'
  }
</script>

<style scoped>

</style>
