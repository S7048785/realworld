<script setup lang="ts">
import {Notifications, notify} from "@kyvg/vue3-notification";
import {useUserStore} from "@/stores/userStore.ts";
const router = useRouter()
const route = useRoute()


const userStore = useUserStore();

const pathList = computed(() => [
  {
    path: '/home',
    name: 'Home',
    isShow: true
  },
  {
    path: '/article/create',
    name: 'New Article',
    isShow: userStore.isLogin
  },
  {
    path: '/login',
    name: 'Sign in',
    isShow: !userStore.isLogin
  }
  , {
    path: '/register',
    name: 'Sign up',
    isShow: !userStore.isLogin
  },
  {
    path: `/profile/${userStore.userInfo?.username}`,
    name: 'My Profile',
    isShow: userStore.isLogin
  },
  {
    path: '/settings',
    name: 'Settings',
    isShow: userStore.isLogin
  }
])

</script>

<template>
  <div>
    <nav class="navbar navbar-light">
      <div class="container">
        <router-link class="navbar-brand" to="/home">conduit</router-link>
<!--        <a class="navbar-brand" @click="toast" >conduit</a>-->

        <ul class="nav navbar-nav pull-xs-right">
          <li v-for="item in pathList" :key="item.path" v-show="item.isShow" class="nav-item">
            <a class="nav-link" @click="router.push(item.path)" :class="{'active': route.path === item.path}" href="javascript: void(0)">{{ item.name }}</a>
          </li>
        </ul>
      </div>
    </nav>

    <router-view/>

    <footer>
      <div class="container">
        <a href="/" class="logo-font">conduit</a>
        <span class="attribution">
      An interactive learning project from <a href="https://thinkster.io">Thinkster</a>. Code &amp;
      design licensed under MIT.
    </span>
      </div>
    </footer>
    <notifications position="top center" :duration="1000" style="top: 20px;"/>
  </div>
</template>

<style lang="less">
  .vue-notification {
    font-size: 18px;
    text-align: center;
  }
</style>
