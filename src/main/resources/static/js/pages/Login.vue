<template>
    <div id="startPage" class="content-center bg-light">
        <div class="container  pb-5 justify-content-center align-items-center d-flex flex-column">
            <div id="header" class="row">
                <div class="col-12 text-center bg-light text-black-50 display-1 flex-row d-flex">
                    <h1 class="display-1 text-success">Gam</h1>
                    <h1 class="display-1 text-danger">e of Beasts</h1>

                </div>
            </div>
            <div id="main" class="row mt-5 ">
                <div id="auth"
                     class="col-4 m-auto border border-success text-center rounded d-flex flex-column justify-content-center align-items-center">
                    <h3 slot="header " class="text-black-50">Вход</h3>
                    <div v-for="item in socialNetworks" class="text-black-50 btn btn-success mt-0,5 mb-1 "
                         style="width: 50%" @click="showModalLogin = true; currentNetwork = item">{{item}}
                    </div>

                </div>
                <div id="description" class="col-7 bg-light rounded  text-black-50">
                    <h5>Тут все очень классно, совсем без костылей, все работает, нормальный курсач, надо принять</h5>
                </div>
            </div>
        </div>

        <ModalLogin v-if="showModalLogin" @close="showModalLogin = false">
            <h3 slot="header"><p class="text-success">Вход</p>{{currentNetwork}}</h3>
            <button slot="button" @click="showModalLogin = false; showModalRegistr = true" class="btn btn-success">
                Регистрация
            </button>
            <button slot="okButton" class="btn btn-success" @click="checkUser()">
                OK
            </button>
        </ModalLogin>
        <ModalLogin v-if="showModalRegistr" @close="showModalRegistr = false">
            <h3 slot="header"><p class="text-success">Регистрация</p>{{currentNetwork}}</h3>
            <div slot="body">
                <label>Nickname
                    <input type="text">
                </label>

            </div>
            <button slot="button" @click="showModalRegistr = false; showModalLogin = true" class="btn btn-success">Вход
            </button>
            <!--<button slot="okButton" class="btn btn-success">-->
            <!--OK-->
            <!--</button>-->
        </ModalLogin>
    </div>
</template>

<script>
    export default {
        name: "Login"

    }
</script>

<style scoped>

</style>
