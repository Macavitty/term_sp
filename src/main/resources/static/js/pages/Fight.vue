<template>
    <div id="fightPage" class="container-fluid"
         style="height: 100%; background: url('https://images5.alphacoders.com/312/312169.jpg');">
        <AppHeader></AppHeader>
        <div id="main" class="row ml-0 mt-5">
            <div id="fight"
                 class="col-lg-8 col-11 p-4 mx-auto mt-2 border border-success text-center bg-light rounded justify-content-center align-items-center">
                <div id="fightState" class="row pb-3">
                    <div class="col-lg-4 col-8  mb-lg-0 mb-1 mx-auto border border-success text-center rounded d-flex
                 flex-column justify-content-center align-items-center">
                        Вы
                        <div class="progress mb-2" style="width: 100%">
                            <div class="progress-bar bg-success" role="progressbar" :style="{width: userHP + '%'}"
                                 aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-8 mt-lg-0 mt-1 mx-auto border border-danger text-center rounded d-flex
                 flex-column justify-content-center align-items-center">
                        Противник
                        <div class="progress mb-2" style="width: 100%">
                            <div class="progress-bar bg-danger" role="progressbar" :style="{width: enemyHP + '%'}"
                                 aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
                <div id="battleField" class="my_table_fight card example-1 square scrollbar-dusty-grass square thin">

                    <table id="chat">
                        <tbody>
                        <tr v-for="(msg, index) in allMessages" v-if="index<=i"
                            :class="[(msg.damage < 0) ? 'text-danger' : 'text-success']">
                            {{msg.msg}}
                        </tr>
                        <tr>
                            <!--<button @click="showModalSave = true" class="btn btn-success">Конец</button>-->

                        </tr>
                        </tbody>

                    </table>
                </div>
                <button @click="illusionBattle" class="btn btn-danger">Аттака</button>

            </div>
            <div id="beastsPanel" class="col">
                <div v-for="beast in beasts" class="m-5 border border-danger bg-light text-center rounded d-flex
                 flex-column justify-content-center align-items-center">
                    <div>type: {{beast.type}}</div>
                    <div>name: {{beast.name}}</div>
                    <div>lvl: {{beast.level}}</div>
                </div>
            </div>

        </div>

        <DefaultModal v-if="showModalSave" @close="showModalSave = false">
            <div slot="header">
                <h3 v-if="isWin">Победа!</h3>
                <h3 v-else>Вы проиграли :с</h3>
            </div>
            <div slot="body">Сохранить ход боя?</div>
            <div slot="footer">
                <button class="btn btn-success" @click="saveBattle('yes')">Да</button>
                <!--showEnd(isWin); isBattleForSave = true-->
                <button class="btn btn-success" @click="saveBattle('no')">Нет</button>
                <!--; showEnd(isWin); isBattleForSave = false-->
            </div>
        </DefaultModal>
        <DefaultModal v-if="showModalEnd" @close="showModalEnd = false">
            <div slot="header"><h3>Забрать проигравших?</h3></div>
            <div slot="body">
                <button class="btn btn-success" @click="saveBeasts('yes')">Да</button>
                <button class="btn btn-success" @click="location.href='/map'">Нет</button>
            </div>
            <div slot="footer" class="row">
                <div v-for="failedBeast in failedBeasts" class="col border text-black rounded m-1 p-2"
                     :class="[failedBeast.isAlive ? 'border-success' : 'border-danger']">
                    <div>{{failedBeast.type}}</div>
                    <div>{{failedBeast.name}}</div>
                    <div v-if="failedBeast.isAlive == true" class="text-success">жив</div>
                    <div v-else class="text-danger">мертв</div>
                </div>
            </div>
        </DefaultModal>
    </div>
</template>

<script>
    import DefaultModal from 'js/components/DefaultModal.vue'
    import axios from 'axios'
    export default {
        name: 'Fight',
        components: {DefaultModal},
        data: function () {
            return {
                beasts: [
                    {type: 'dragon', name: 'Drogo', level: '14', hp: 100, isAlive: true},
                    {type: 'dragon', name: 'Smaug', level: '14', hp: 100, isAlive: false}
                ],
                enemies: [
                    {type: 'dragon', name: 'GG', level: '14', hp: 100, isAlive: true},
                    {type: 'dragon', name: 'Pika', level: '14', hp: 55, isAlive: false}
                ],
                failedBeasts: [
                    {type: 'dragon', name: 'Drogo', level: '14', isAlive: true, isSaved: false},
                    {type: 'dragon', name: 'Drogo', level: '14', isAlive: true, isSaved: false},
                    {type: 'dragon', name: 'Drogo', level: '14', isAlive: true, isSaved: false}
                ],
                allMessages: [
                    {msg: 'dfghjkl1', damage: -10},
                    {msg: 'dfghjkl2', damage: 10},
                    {msg: 'dfghjkl3', damage: -30},
                    {msg: 'dfghjkl4', damage: 40},
                    {msg: 'dfghjkl5', damage: 50}
                ],
                user: {name: 'Dany', level: 10, exp: 1400, money: 50},
                userHP: 100,
                enemyHP: 100,
                isBattleForSave: false,
                isSaveBeasts: false,
                isWin: true,
                showModalSave: false,
                showModalEnd: false,
                isReady: false,
                i: -1,
                attackDisable: false,
            }
        },
        methods: {
            recountHP(hp) {
                if (hp < 0) {
                    this.userHP += hp
                    this.isBadMove = true
                } else {
                    this.enemyHP -= hp
                    this.isBadMove = false
                }
            },
            illusionBattle() {
                if (this.i < this.allMessages.length) {
                    this.i++
                    this.recountHP(this.allMessages[this.i].damage)
                    console.log("recount hp")
                    if (this.enemyHP == 0 | this.userHP == 0) {
                        if (this.enemyHP > 0)
                            this.isWin = false
                        else {
                            this.isWin = true
                        }
                        this.attackDisable = true
                        setTimeout(()=>{
                            this.showModalSave = true
                        }, 500)
                    }
                }
                axios
                    .get('/user/updateUser')

            },
            saveBattle() {
                this.showModalSave = false
                if (this.isWin)
                    this.showModalEnd = true
                else
                    location.href = '/map'
                let url = '/fight/saveSteps'
                axios
                    .get(url)
                    .then(result => {
                        console.log(result)
                    })
            },
            saveBeasts(e) {
                location.href = '/map'
                this.showModalEnd = false
                let url = '/fight/saveBeasts'
                axios
                    .get(url)
                    .then(result => {
                        console.log(result)
                    })
            },
            getData() {
                axios
                    .get('/fight/start')
                    .then((result => {
                        this.allMessages = result.data.msgs
                        this.enemies = result.data.enemies
                        this.failedBeasts = this.enemies
                        this.beasts = result.data.my
                        this.isReady = true
                        console.log(result)
                    }))
            }
        },
        created() {
            this.getData()
        }
    }
</script>

<style scoped>
</style>
©