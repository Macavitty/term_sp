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
                            <div class="progress-bar bg-success" role="progressbar" style="width: 100%"
                                 aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-8 mt-lg-0 mt-1 mx-auto border border-danger text-center rounded d-flex
                 flex-column justify-content-center align-items-center">
                        Противник
                        <div class="progress mb-2" style="width: 100%">
                            <div class="progress-bar bg-danger" role="progressbar" style="width: 100%"
                                 aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
                <div id="battleField" class="my_table_fight card example-1 square scrollbar-dusty-grass square thin">

                    <table id="chat">
                        <tbody>
                        <!--                        <button v-if="isReady" class="btn rounded-circle btn-danger" @click="showMsgs">Готов!</button>-->
                        <tr v-if="isReady" v-for="msg in allMessages">
                            {{msg.msg }}
                        </tr>
                        <tr>
                            <button @click="showModalSave = true" class="btn btn-success">Конец</button>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>
            <div id="beastsPanel" class="col">
                <div v-for="beast in enemies" class="m-5 border border-danger bg-light text-center rounded d-flex
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
                <button class="btn btn-success" @click="saveBeasts('no')">Нет</button>
            </div>
            <div slot="footer" class="row">
                <div v-for="failedBeast in failedBeasts" class="col border border-danger text-black rounded m-1 p-2">
                    <div>{{failedBeast.type}}</div>
                    <div>{{failedBeast.name}}</div>
                    <div v-if="isAlive === true" class="text-success">жив</div>
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
                    {type: 'dragon', name: 'Drogo', level: '14', hp: 100, isFighter: true, isAlive: true},
                    {type: 'dragon', name: 'Smaug', level: '14', hp: 100, isFighter: false, isAlive: false}
                ],
                enemyBeasts: [
                    {type: 'dragon', name: 'GG', level: '14', hp: 100, isFighter: false, isAlive: true},
                    {type: 'dragon', name: 'Pika', level: '14', hp: 55, isFighter: true, isAlive: false}
                ],
                failedBeasts: [
                    {type: 'dragon', name: 'Drogo', level: '14', hp: 100, isFighter: true, isAlive: true},
                    {type: 'dragon', name: 'Drogo', level: '14', hp: 100, isFighter: true, isAlive: true},
                    {type: 'dragon', name: 'Drogo', level: '14', hp: 100, isFighter: true, isAlive: true}
                ],
                allMessages: [
                    {msg: null, int: -10}
                ],
                user: {name: 'Dany', level: 10, exp: 1400, money: 50},
                isBattleForSave: false,
                isSaveBeasts: false,
                isWin: true,
                showModalSave: false,
                showModalEnd: false,
                isReady: false
            }
        },
        methods: {
            getMsg() {
                axios
                    .get('/fight/fight')
                    .then((result => {
                        this.allMessages = result.data.msgs
                        this.enemies = result.data.enemies
                        this.failedBeasts = this.enemies
                        this.beasts = result.data.my
                        this.isReady = true
                        console.log(result)
                    }))

            },
            showMsgs() {
                this.isReady = false

                for (let i = 0; i < this.allMessages.length; i++) {

                    setTimeout(function () {
                        allMessages[i].disable = true

                        console.log('show messages')
                    }, 2000)
                }
            },
            showEnd(t) {
                if (t) {
                    this.showModalEnd = true
                }
            },
            saveBattle(e) {
                location.href = '/map'
                // this.showModalSave = false
                // this.showEnd(this.isWin)
                // let url = '/map'
                // axios
                //     .get(url)
                //     .then(result => {
                //         console.log(result)
                //     })
            },
            saveBeasts(e) {
                location.href = '/map'
            }
            //     this.showModalEnd = false
            //     let url = '/saveBeasts/' + e
            //     axios
            //         .get(url)
            //         .then(result => {
            //             console.log(result)
            //         })
            // },
        },
        created() {
            this.getMsg()
        }
    }
</script>

<style scoped>
</style>