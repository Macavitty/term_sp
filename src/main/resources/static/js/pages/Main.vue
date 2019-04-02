<template>
    <div>
        <div v-if="!profile"> You need to authorize to continue
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <AppHeader></AppHeader>
<!--            <div class="topnav-right">-->
<!--                <AppNavigation></AppNavigation>-->
<!--            </div>-->
            <h1 align="center">Это первая страничка, куда пользователь попадает после логина/регистрации</h1>
            <h1 align="center">можно разместить мануал по игре</h1>
<!--            <AppFooter></AppFooter>-->
        </div>
    </div>
</template>

<script>
    export default {
        name: "Main",
        data: function () {
            return {
                profile: magic_data.profile,
                userName: 'rgr'
            }
        }
    }
</script>
