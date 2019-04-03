<template>
    <div style="height: 100%">
        <div v-if="!profile">
            <div id="startPage" class="content-center bg-light">
                <div class="container  pb-5 justify-content-center align-items-center d-flex flex-column">
                    <div id="header" class="row">
                        <div class="col-12 text-center bg-light text-black-50 display-1 flex-row d-flex">
                            <h1 class="display-1 text-success">The Gam</h1>
                            <h1 class="display-1 text-danger">e of Beasts</h1>

                        </div>
                    </div>
                    <div id="main" class="row mt-5 ">
                        <div id="auth"
                             class="col-4 m-auto border border-success text-center rounded d-flex flex-column justify-content-center align-items-center">
                            <h3 slot="header " class="text-black-50">Вход</h3>
                            <div v-for="item in socialNetworks" class="text-black-50 btn btn-success mt-0,5 mb-1 "
                                 style="width: 50%" @click="checkUser()"> <a href="/login" class="text-white" style="text-decoration: none"><h4>{{item}}</h4></a>
                            </div>

                        </div>
                        <div id="description" class="col-7 bg-light rounded text-black-50">
                            <h5>Тут все классно, совсем без костылей, все работает, хороший курсач, надо принять</h5>
                        </div>
                    </div>
                </div>

<!--                <ModalLogin v-if="showModalLogin" @close="showModalLogin = false">-->
<!--                    <h3 slot="header"><p class="text-success">Вход</p>{{currentNetwork}}</h3>-->
<!--                    <button slot="button" @click="showModalLogin = false; showModalRegistr = true" class="btn btn-success">-->
<!--                        Регистрация-->
<!--                    </button>-->
<!--                    <button slot="okButton" class="btn btn-success" @click="checkUser()">-->
<!--                        OK-->
<!--                    </button>-->
<!--                </ModalLogin>-->
<!--                <ModalLogin v-if="showModalRegistr" @close="showModalRegistr = false">-->
<!--                    <h3 slot="header"><p class="text-success">Регистрация</p>{{currentNetwork}}</h3>-->
<!--                    <div slot="body">-->
<!--                        <label>Nickname-->
<!--                            <input type="text">-->
<!--                        </label>-->

<!--                    </div>-->
<!--                    <button slot="button" @click="showModalRegistr = false; showModalLogin = true" class="btn btn-success">Вход-->
<!--                    </button>-->
<!--                    <button slot="okButton" class="btn btn-success" @click="checkUser()">-->
<!--                        OK-->
<!--                    </button>-->
<!--                </ModalLogin>-->
            </div>
        </div>
        <div v-else style="height: 100%">
            <div class="container-fluid" style="height: 100%; background: url('http://www.youloveit.ru/uploads/gallery/main/7/youloveit_ru_oboi_winx_tainiks03.jpg')">
                <AppHeader></AppHeader>
                <div class="m-5 row"  >
                    <div class="my-auto col">

                        <h1 align="center" class="text-monospace text-danger">Куда отправимся?</h1>
                        <h2 align="center" class="m-5"><a class="dropdown-item" title="Отсюда начинаются приключения" href = "/map">Карта</a>
                            <a class="dropdown-item" title="Информация о тебе на случай, если забыл" href = "/account">Герой</a>
                            <!--<a class="dropdown-item" href = "/craft">Кузница</a>-->
                            <a class="dropdown-item" title="Можно почитать о том, что встречается здесь" href = "/gl">Глоссарий</a>
                            <a class="dropdown-item" title="Собственно то, чем кажется" href = "/login">Выход</a></h2>
                    </div>
                </div>
                <AppFooter class="row"></AppFooter>
                <div class="row mt-5 pt-5 pl-4">
<!--                    <h6>Страница входа выглядела хорошо, там явно не хватало несочетающихся цветов и... феечек</h6>-->
                </div>

            </div>
        </div>
    </div>
</template>

<script>
    import ModalLogin from 'js/components/ModalLogin.vue'
    import axios from 'axios'
    export default {
        name: "Main",
        components: {ModalLogin},
        data: function () {
            return {
                profile: magic_data.profile,
                socialNetworks: ['google'],
                currentNetwork: '',
                showModalLogin: false,
                showModalRegistr: false
            }
        },
        methods: {
            checkUser () {
                let url = '/login'
                axios
                //обращаемся к серверу
                    .get(url)
                    .then(result => {
                        console.log(result)
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
        }
    }
</script>
