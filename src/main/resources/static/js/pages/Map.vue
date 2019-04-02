<template>
  <div>
    <AppHeader></AppHeader>
    <div class="topnav-right">
      <AppNavigation></AppNavigation>
    </div>
    <br/>
    <div id="canvas_area" align="center">
      <canvas id="map-canvas" @click="eventCanvas">
        <!-- window.innerWidth -->
        Sorry, canvas not supported
      </canvas>
    </div>
    <br/>
  </div>
</template>

<script> /* eslint-disable*/
  export default {
    name: 'Map',
    mounted: function () {
      this.draw_map();
      // window.onresize = () => {
      //   this.draw_map();
      // }
    },
    methods: {
      eventCanvas: function (e) {
        let location = this.what_location(e);
        if (location === 4) window.open();
      },
      what_location: function(e){
        let l = 0;
        let canvas = document.getElementById('map-canvas');
        let ctx = canvas.getContext('2d');
        let w = canvas.width
        let h = canvas.height
        let p1 = this.get_location_shape_param(1);
        let p2 = this.get_location_shape_param(2);
        let p3 = this.get_location_shape_param(3);
        let p4 = this.get_location_shape_param(4);
        let p5 = this.get_location_shape_param(5);
        let p6 = this.get_location_shape_param(6);
        let foo = this.getMousePos(e);
        let x =  foo.x;
        let y = foo.y;
        // first
        if ((p1.c_x - x)*(p1.c_x - x) + (p1.c_y - y)*(p1.c_y - y) <= p1.r*p1.r) l = 1;
        else if ((p2.c_x - x)*(p2.c_x - x) + (p2.c_y - y)*(p2.c_y - y) <= p2.r*p2.r) l = 2;
        else if ((p4.c_x - x)*(p4.c_x - x) + (p4.c_y - y)*(p4.c_y - y) <= p4.r*p4.r) l = 4;
        else if ((p6.c_x - x)*(p6.c_x - x) + (p6.c_y - y)*(p6.c_y - y) <= p6.r*p6.r) l = 6;

        else if ((p1.c_x - x)*(p1.c_x - x) + (p1.c_y - y)*(p1.c_y - y) <= p1.r*p1.r) l = 1;
        else if ((p1.c_x - x)*(p1.c_x - x) + (p1.c_y - y)*(p1.c_y - y) <= p1.r*p1.r) l = 1;

        return l;
      },
      showLocationName: function (e) {

      },
      draw_map: function (e) {
        let canvas = document.getElementById('map-canvas');
        if (window.innerWidth > window.innerHeight) {
          canvas.width = window.innerWidth / 2.2;
          canvas.height = window.innerHeight / 1.5;
        } else {
          canvas.width = window.innerWidth / 1.5;
          canvas.height = window.innerHeight / 2.2;
        }
        this.draw_locations(e);
      },

      draw_locations: function (e) {
        let canvas = document.getElementById("map-canvas");
        let ctx = canvas.getContext("2d");

        let p1 = this.get_location_shape_param(1);
        let p2 = this.get_location_shape_param(2);
        let p3 = this.get_location_shape_param(3);
        let p4 = this.get_location_shape_param(4);
        let p5 = this.get_location_shape_param(5);
        let p6 = this.get_location_shape_param(6);

        let w = canvas.width;
        let h = canvas.height;
        let minparam = w > h ? h : w;

        var background = new Image();
        // background.src = "https://image.freepik.com/vector-gratis/fondo-mar-azul_1110-591.jpg";
        background.src = 'https://i.pinimg.com/736x/1f/90/30/1f9030338b5c277b10d3e13a834a3f60--wave-design-print-design.jpg'
        background.onload = function () {
          ctx.drawImage(background, 0, 0, canvas.width, canvas.height);
          ctx.fillStyle = 'rgba(30,65,254,0.49)';
          ctx.fillRect(0, 0, w, h);
          ctx.fillStyle = 'white';
          ctx.fillRect(0, h - h / 16, w, h);
          // text
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 150% Georgia'; //  Выбери землю, воин, дабы послать свой отряд в бой во имя закрытия сессии
          ctx.fillText('ᛠ\tᛡ\tᛢ\tᛣ\tᛤ\tᛥ\tᛦᛃ\tᛄ\tᛅ\tᛆᚰ\tᚱ\tᚲ\tᚳ\tᚴ\tᚵ\tᚶ\tᚷ\tᚸ\tᚹ\tᚺ\tᚻ\tᚼ\tᚽ\tᚾ\tᚿ', 0 + w / 4, h - h / 16 + 1 + h / 32);
          ctx.fill();
          ctx.closePath();
          /* ==  drawing locations == */

          /*
          * first location */
          ctx.fillStyle = "#35d40d";
          ctx.strokeStyle = 'white';// "rgba(176,223,43,0.58)";
          ctx.beginPath();
          ctx.arc(p1.c_x, p1.c_y, p1.r, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 7;
          ctx.beginPath();
          ctx.arc(p1.c_x, p1.c_y, p1.r, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 200% Georgia';
          ctx.fillText('ᚱᚣ', p1.c_x, p1.c_y);
          ctx.fill();

          /*
           * second location */
          ctx.fillStyle = "#f44639";
          ctx.strokeStyle = 'white';// "rgba(223,64,52,0.58)";
          ctx.beginPath();
          ctx.arc(p2.c_x, p2.c_y, p2.r, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 5;
          ctx.beginPath();
          ctx.arc(p2.c_x, p2.c_y, p2.r, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 200% Georgia';
          ctx.fillText('ᛈᚸ', p2.c_x, p2.c_y);
          ctx.fill();

          /*
           * 3 location */
          ctx.fillStyle = "#f6650c";
          ctx.strokeStyle = 'white';// "rgba(223,92,11,0.53)";
          ctx.beginPath();
          ctx.ellipse(p3.c_x, p3.c_y, p3.r_x, p3.r_y, -Math.PI / 2.8, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 2;
          ctx.beginPath();
          ctx.ellipse(p3.c_x, p3.c_y, p3.r_x, p3.r_y, -Math.PI / 2.8, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 200% Georgia';
          ctx.fillText('ᚥ', p3.c_x, p3.c_y);
          ctx.fill();

          /*
           * 4 location */
          ctx.fillStyle = "#6e7298";
          ctx.strokeStyle = "rgba(75,78,104,0.56)";
          ctx.beginPath();
          ctx.arc(w / 2 + w / 10, h / 2 + h / 4, minparam / 18, 0, 2 * Math.PI);
          ctx.fill();
          ctx.closePath();
          ctx.fillStyle = "#f8fbf1";
          ctx.beginPath();
          ctx.arc(p4.c_x, p4.c_y, minparam / 30, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 5;
          ctx.beginPath();
          ctx.arc(p4.c_x, p4.c_y, minparam / 18, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.arc(p4.c_x, p4.c_y, minparam / 30, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 150% Georgia';
          ctx.fillText('ᛤ', p4.c_x, p4.c_y);
          ctx.fill();

          /* 5 location */
          ctx.fillStyle = "#f6df15";
          ctx.strokeStyle = 'white';// "rgba(246,223,21,0.56)";
          ctx.beginPath();
          ctx.ellipse(p5.c_x, p5.c_y, p5.r_x, p5.r_y, -Math.PI / 8, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 3;
          ctx.beginPath();
          ctx.ellipse(p5.c_x, p5.c_y, p5.r_x, p5.r_y, -Math.PI / 8, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 200% Georgia';
          ctx.fillText('ᚱᛦᛔᛓ', p5.c_x, p5.c_y);
          ctx.fill();

          /* 6 location */
          ctx.fillStyle = "#f121ea";
          ctx.strokeStyle = 'white';// "rgba(241,33,234,0.55)";
          ctx.beginPath();
          ctx.arc(p6.c_x, p6.c_y, p6.r, 0, 2 * Math.PI);
          ctx.fill();
          ctx.lineWidth = 2;
          ctx.beginPath();
          ctx.arc(p6.c_x, p6.c_y, p6.r, 0, 2 * Math.PI);
          ctx.stroke();
          ctx.closePath();
          ctx.closePath();
          ctx.beginPath();
          ctx.fillStyle = "#261f41";
          ctx.font = 'bold 200% Georgia';
          ctx.fillText('ᛃ', p6.c_x, p6.c_y);
          ctx.fill();
        }
      },
      get_pos_on_canvas: function (coo, par) {
        return (6 + coo) * par / 12;
      },
      getMouseCoords: function (e) {
        let canvas = document.getElementById('map-canvas');
        let rect = canvas.getBoundingClientRect();
        let w = canvas.width;
        let h = canvas.height;
        return {
          x: 6 - (w - (e.clientX - rect.left)) * 12 / w,
          y: (6 - (h - (e.clientY - rect.top)) * 12 / h) * (-1)
        };
      },
      getMousePos: function (e) {
        let canvas = document.getElementById('map-canvas');
        let rect = canvas.getBoundingClientRect();
        return {
          x: e.clientX - rect.left,
          y: e.clientY - rect.top
        };
      },

      get_location_shape_param: function (location) {
        let canvas = document.getElementById('map-canvas');
        let w = canvas.width;
        let h = canvas.height;
        let minparam = w > h ? h : w;
        if (location === 1) {
          return {
            c_x: w / 2,
            c_y: h / 2,
            r: minparam / 9
          };
        }
        if (location === 2) {
          return {
            c_x: w / 2 + w / 6,
            c_y: h / 2 + h / 9,
            r: minparam / 15
          };
        }
        if (location === 3) {
          return {
            c_x: w / 2 + w / 5,
            c_y: h / 2 - h / 6,
            r_x: minparam / 6,
            r_y: minparam / 5
          };
        }
        if (location === 4) {
          return {
            c_x: w / 2 + w / 10,
            c_y: h / 2 + h / 4,
            r: minparam / 18
          };
        }
        if (location === 5) {
          return {
            c_x: w / 2 - w / 6.5,
            c_y: h / 2 - h / 4,
            r_x: minparam / 4,
            r_y: minparam / 5.5
          };
        } else {
          return {
            c_x: w / 2,
            c_y: h / 2 + h / 3,
            r: minparam / 30
          };
        }
      }
    }
  }
</script>

<style scoped>

</style>
