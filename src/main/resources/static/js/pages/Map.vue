<template>
    <div>
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
        <!--        <div style="background: url('http://www.youloveit.ru/uploads/gallery/main/7/youloveit_ru_oboi_winx_tainiks06.jpg') no-repeat;background-size: 100%; height: 100vh; width: 100vw; z-index: -1; position: fixed"></div>-->
        <div style="background: url('https://www.ubackground.com/_ph/85/950344899.jpg') no-repeat;background-size: 100%; height: 100vh; width: 100vw; z-index: -1; position: fixed"></div>

        <div id="canvas_area" align="center">
            <canvas id="map-canvas" title="Выбери землю, воин, дабы послать свой отряд в бой во имя закрытия сессии"
                    @click="eventCanvas">
                <!-- window.innerWidth -->
                Sorry, canvas not supported
            </canvas>
        </div>
        <br/>
        <DefaultModal v-if="showModal" @close="showModal = false">
            <div slot="header">
                <h3 class="text-danger">На вас напали!</h3>
            </div>

            <div slot="body" class="row"><div v-for="beast in enemies" class="col m-2 border border-danger bg-light text-center rounded d-flex
                 flex-column justify-content-center align-items-center">
                <div><span class="text-black-50">type:</span><span> {{beast.type}}</span></div>
                <div><span class="text-black-50">name:</span> {{beast.name}}</div>
                <div><span class="text-black-50">lvl:</span> {{beast.level}}</div>
            </div>
            </div>
            <div slot="footer" class="row p-2">
                <div class="col p-0">
                    <button class="btn btn-warning text-white mb-1 text-center" @click="run">&nbsp;&nbsp;Убежать&nbsp;&nbsp;&nbsp;<span
                            style="visibility: hidden"><br/> за {{cost}}</span></button>
                    <button class="btn btn-success mt-1 text-center" :class="{'disable': disablePay}" @click="pay">
                        Откупиться<br/> за {{cost}}
                    </button>
                </div>
                <!--<div class="col my-auto">-->
                <button class="btn btn-danger text-center" @click="fight">В БОЙ!</button>
                <!--</div>-->
            </div>

        </DefaultModal>
        <div v-if="showSpinner" id="spinner" class="modal-mask">
            <div class="modal-wrapper">
                <div class="d-flex justify-content-center">
                    <div class="spinner-grow text-success" style="width: 15rem; height: 15rem;" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script> /* eslint-disable*/
import DefaultModal from 'js/components/DefaultModal.vue'
import axios from 'axios'
import {RouteRecord as redirect} from 'vue-router'
export default {
    name: 'Map',
    components: {DefaultModal},
    data: function () {
        return {
            enemies: [{name: 'Базовая', type: 'ЭВМ', lvl: 666}],
            showModal: false,
            showSpinner: false,
            cost: null,
            disablePay: false,
            user: {
                name: user_info.nick,
                level: user_info.level,
                exp: user_info.exp,
                money: user_info.money,
                icon: user_info.icon
            }
        }
    },
    mounted: function () {
        this.draw_map()
        // window.onresize = () => {
        //   this.draw_map();
        // }
    },
    methods: {
        run: function () {
            if (Math.random() < 0.5) {
                showModal = false
            } else this.fight()
        },
        pay () {
            this.showModal = false
            console.log("pay")
            axios
                .get('/fight/pay/' + this.cost)
                .then(result=>{
                    if(!result.data)
                        alert('Вы слишком нищий, сражайтесь!')
                    else this.user.money-=this.cost
                })
        },
        fight: function () {
            location.href = '/fight'
            axios .get('/fight')
                .then(result => {
                    this.enemies = result.data.enemies
                    console.log(result)
                })
        },
        getCost: function () {
            this.cost = Math.round(Math.random() * 100)
        },
        startBattle: function (action) {
            let url = '/fight/' + action
            axios
                .get(url)
                .then(result => {
                    console.log(result)
                })
        },
        eventCanvas: function (e) {
            this.getCost()
            this.disablePay = (this.cost > user_info.money)
            this.showSpinner = true
            let location = this.what_location(e)
            let url = '/map/' + location
            axios
                .get(url)
                .then(result => {
                    this.enemies = result.data.enemies
                    console.log(result)
                })
            setTimeout(() => {
                this.showSpinner = false
                this.showModal = true
            }, 2500)
        },
        what_location: function (e) {
            let l = 0
            let canvas = document.getElementById('map-canvas')
            let ctx = canvas.getContext('2d')
            let w = canvas.width
            let h = canvas.height
            let p1 = this.get_location_shape_param(1)
            let p2 = this.get_location_shape_param(2)
            let p3 = this.get_location_shape_param(3)
            let p4 = this.get_location_shape_param(4)
            let p5 = this.get_location_shape_param(5)
            let p6 = this.get_location_shape_param(6)
            let foo = this.getMousePos(e)
            let x = foo.x
            let y = foo.y
            // first
            if ((p1.c_x - x) * (p1.c_x - x) + (p1.c_y - y) * (p1.c_y - y) <= p1.r * p1.r) {
                l = 1
            } else if ((p2.c_x - x) * (p2.c_x - x) + (p2.c_y - y) * (p2.c_y - y) <= p2.r * p2.r) {
                l = 2
            } else if ((p4.c_x - x) * (p4.c_x - x) + (p4.c_y - y) * (p4.c_y - y) <= p4.r * p4.r) {
                l = 4
            } else if ((p6.c_x - x) * (p6.c_x - x) + (p6.c_y - y) * (p6.c_y - y) <= p6.r * p6.r) {
                l = 6
            } else if ((p1.c_x - x) * (p1.c_x - x) + (p1.c_y - y) * (p1.c_y - y) <= p1.r * p1.r) {
                l = 1
            } else if ((p1.c_x - x) * (p1.c_x - x) + (p1.c_y - y) * (p1.c_y - y) <= p1.r * p1.r) l = 1
            return l
        },
        showLocationName: function (e) {
        },
        draw_map: function (e) {
            let canvas = document.getElementById('map-canvas')
            if (window.innerWidth > window.innerHeight) {
                canvas.width = window.innerWidth / 2.2
                canvas.height = window.innerHeight / 1.5
            } else {
                canvas.width = window.innerWidth / 1.5
                canvas.height = window.innerHeight / 2.2
            }
            this.draw_locations(e)
        },
        draw_locations: function (e) {
            let canvas = document.getElementById('map-canvas')
            let ctx = canvas.getContext('2d')
            let p1 = this.get_location_shape_param(1)
            let p2 = this.get_location_shape_param(2)
            let p3 = this.get_location_shape_param(3)
            let p4 = this.get_location_shape_param(4)
            let p5 = this.get_location_shape_param(5)
            let p6 = this.get_location_shape_param(6)
            let w = canvas.width
            let h = canvas.height
            let minparam = w > h ? h : w
            var background = new Image()
            // background.src = "https://image.freepik.com/vector-gratis/fondo-mar-azul_1110-591.jpg";
            background.src = 'https://i.pinimg.com/736x/1f/90/30/1f9030338b5c277b10d3e13a834a3f60--wave-design-print-design.jpg'
            background.onload = function () {
                ctx.drawImage(background, 0, 0, canvas.width, canvas.height)
                ctx.fillStyle = 'rgba(46, 45, 45, 0.03)'
                ctx.fillRect(0, 0, w, h)
                ctx.fillStyle = 'white'
                ctx.fillRect(0, h - h / 16, w, h)
                // text
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 150% Georgia' //  Выбери землю, воин, дабы послать свой отряд в бой во имя закрытия сессии
                ctx.fillText('ᛠ\tᛡ\tᛢ\tᛣ\tᛤ\tᛥ\tᛦᛃ\tᛄ\tᛅ\tᛆᚰ\tᚱ\tᚲ\tᚳ\tᚴ\tᚵ\tᚶ\tᚷ\tᚸ\tᚹ\tᚺ\tᚻ\tᚼ\tᚽ\tᚾ\tᚿ', 0 + w / 18, h - h / 20 + 1 + h / 32)
                ctx.fill()
                ctx.closePath()
                /* ==  drawing locations == */
                /*
                * first location */
                ctx.fillStyle = '#35d40d'
                ctx.strokeStyle = 'white'// "rgba(176,223,43,0.58)";
                ctx.beginPath()
                ctx.arc(p1.c_x, p1.c_y, p1.r, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 7
                ctx.beginPath()
                ctx.arc(p1.c_x, p1.c_y, p1.r, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 200% Georgia'
                ctx.fillText('ᚱᚣ', p1.c_x - 15, p1.c_y + 10)
                ctx.fill()
                /*
                 * second location */
                ctx.fillStyle = '#f44639'
                ctx.strokeStyle = 'white'// "rgba(223,64,52,0.58)";
                ctx.beginPath()
                ctx.arc(p2.c_x, p2.c_y, p2.r, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 5
                ctx.beginPath()
                ctx.arc(p2.c_x, p2.c_y, p2.r, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 200% Georgia'
                ctx.fillText('ᛈᚸ', p2.c_x - 17, p2.c_y + 10)
                ctx.fill()
                /*
                 * 3 location */
                ctx.fillStyle = '#f6650c'
                ctx.strokeStyle = 'white'// "rgba(223,92,11,0.53)";
                ctx.beginPath()
                ctx.ellipse(p3.c_x, p3.c_y, p3.r_x, p3.r_y, -Math.PI / 2.8, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 2
                ctx.beginPath()
                ctx.ellipse(p3.c_x, p3.c_y, p3.r_x, p3.r_y, -Math.PI / 2.8, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 200% Georgia'
                ctx.fillText('ᚥ', p3.c_x - 15, p3.c_y + 10)
                ctx.fill()
                /*
                 * 4 location */
                ctx.fillStyle = '#6e7298'
                ctx.strokeStyle = 'rgba(75,78,104,0.56)'
                ctx.beginPath()
                ctx.arc(w / 2 + w / 10, h / 2 + h / 4, minparam / 18, 0, 2 * Math.PI)
                ctx.fill()
                ctx.closePath()
                ctx.fillStyle = '#f8fbf1'
                ctx.beginPath()
                ctx.arc(p4.c_x, p4.c_y, minparam / 30, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 5
                ctx.beginPath()
                ctx.arc(p4.c_x, p4.c_y, minparam / 18, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.arc(p4.c_x, p4.c_y, minparam / 30, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 150% Georgia'
                ctx.fillText('ᛤ', p4.c_x - 6, p4.c_y + 8)
                ctx.fill()
                /* 5 location */
                ctx.fillStyle = '#f6df15'
                ctx.strokeStyle = 'white'// "rgba(246,223,21,0.56)";
                ctx.beginPath()
                ctx.ellipse(p5.c_x, p5.c_y, p5.r_x, p5.r_y, -Math.PI / 8, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 3
                ctx.beginPath()
                ctx.ellipse(p5.c_x, p5.c_y, p5.r_x, p5.r_y, -Math.PI / 8, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 200% Georgia'
                ctx.fillText('ᚱᛦᛔᛓ', p5.c_x - 30, p5.c_y + 10)
                ctx.fill()
                /* 6 location */
                ctx.fillStyle = '#f121ea'
                ctx.strokeStyle = 'white'// "rgba(241,33,234,0.55)";
                ctx.beginPath()
                ctx.arc(p6.c_x, p6.c_y, p6.r, 0, 2 * Math.PI)
                ctx.fill()
                ctx.lineWidth = 2
                ctx.beginPath()
                ctx.arc(p6.c_x, p6.c_y, p6.r, 0, 2 * Math.PI)
                ctx.stroke()
                ctx.closePath()
                ctx.closePath()
                ctx.beginPath()
                ctx.fillStyle = '#261f41'
                ctx.font = 'bold 200% Georgia'
                ctx.fillText('ᛃ', p6.c_x - 8, p6.c_y + 10)
                ctx.fill()
            }
        },
        get_pos_on_canvas: function (coo, par) {
            return (6 + coo) * par / 12
        },
        getMouseCoords: function (e) {
            let canvas = document.getElementById('map-canvas')
            let rect = canvas.getBoundingClientRect()
            let w = canvas.width
            let h = canvas.height
            return {
                x: 6 - (w - (e.clientX - rect.left)) * 12 / w,
                y: (6 - (h - (e.clientY - rect.top)) * 12 / h) * (-1)
            }
        },
        getMousePos: function (e) {
            let canvas = document.getElementById('map-canvas')
            let rect = canvas.getBoundingClientRect()
            return {
                x: e.clientX - rect.left,
                y: e.clientY - rect.top
            }
        },
        get_location_shape_param: function (location) {
            let canvas = document.getElementById('map-canvas')
            let w = canvas.width
            let h = canvas.height
            let minparam = w > h ? h : w
            if (location === 1) {
                return {
                    c_x: w / 2,
                    c_y: h / 2,
                    r: minparam / 9
                }
            }
            if (location === 2) {
                return {
                    c_x: w / 2 + w / 6,
                    c_y: h / 2 + h / 9,
                    r: minparam / 15
                }
            }
            if (location === 3) {
                return {
                    c_x: w / 2 + w / 5,
                    c_y: h / 2 - h / 6,
                    r_x: minparam / 6,
                    r_y: minparam / 5
                }
            }
            if (location === 4) {
                return {
                    c_x: w / 2 + w / 10,
                    c_y: h / 2 + h / 4,
                    r: minparam / 18
                }
            }
            if (location === 5) {
                return {
                    c_x: w / 2 - w / 6.5,
                    c_y: h / 2 - h / 4,
                    r_x: minparam / 4,
                    r_y: minparam / 5.5
                }
            } else {
                return {
                    c_x: w / 2,
                    c_y: h / 2 + h / 3,
                    r: minparam / 30
                }
            }
        }
    }
}
</script>

<style scoped>
</style>