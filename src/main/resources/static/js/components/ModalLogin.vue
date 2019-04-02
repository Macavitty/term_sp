<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              default header
            </slot>
            <button class="btn" @click="$emit('close')">X</button>
          </div>

          <div class="modal-body">
            <slot name="body">
              <div><label>Логин <input v-model="login"/> </label></div>
              <div><label>Пароль <input type="password" v-model="password"/> </label></div>
              {{errMsg}}
            </slot>
          </div>
          <div class="modal-footer">
            <slot name="footer">
              <slot name="button">
              </slot>
              <!--<slot name="okButton" class="btn btn-success" @click="checkUser()">-->
              <!--OK-->
              <!--</slot>-->
              <slot name="okButton" class="btn btn-success" @click="checkUser()">
                OK
              </slot>
              {{authResult}}
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>/* eslint-disable*/
import axios from 'axios'

export default {
  name: 'ModalLogin',

  data () {
    return {
      login: '222',
      password: '2222',
      authResult: null,
      errMsg: null
    }
  },
  methods: {
    // checkUser () {
    //   axios
    //   //обращаемся к серверу
    //     .get('/login', {
    //       login: this.login, password: this.password
    //     })
    //     .setHeader('Origin', '')
    //     .then(response => {
    //       // достаем результат проверки
    //       this.authResult = response.data
    //       if (this.authResult === true) {
    //         location.href = '/map'
    //       }//сообщение если с данными пользователя что-то не так(получаем с сервера)
    //       else {
    //         this.errMsg = response.data
    //       }
    //
    //     })
    //     .catch(error => {
    //       console.log(error)
    //     })
    // },
    // registrUser () {
    //
    // }
  }
}
</script>

<style scoped>
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  /*#startPage*/
  /*{*/
  /*background-image: url("https://cs8.pikabu.ru/post_img/big/2016/03/11/9/145771187312536347.jpg");*/
  /*}*/
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
    color: #42b983;
  }

  .modal-body {
    margin: 20px 0;

  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
</style>
