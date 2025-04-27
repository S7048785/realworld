<script setup lang="ts">
import {Notifications, notify} from "@kyvg/vue3-notification";
import {useUserStore} from "@/stores/userStore.ts";

const userStore = useUserStore();
const router = useRouter()
const route = useRoute()

const isShow = ref(!userStore.userInfo)

const pathList = reactive([
  {
    path: '/home',
    name: 'Home',
    isShow: true
  },
  {
    path: '/article/create',
    name: 'New Article',
    isShow: true
  },
  {
    path: '/login',
    name: 'Sign in',
    isShow: isShow.value
  }
  , {
    path: '/register',
    name: 'Sign up',
    isShow: isShow.value
  },
  {
    path: '/settings',
    name: 'Settings',
    isShow: !isShow.value
  }
])

async function toast() {
  // let res = await getUserInfoAPI(1);
  notify({
    text: 'sss'

  })
}
</script>

<template>
  <div>
    <nav class="navbar navbar-light">
      <div class="container">
        <a class="navbar-brand" @click="toast" >conduit</a>

        <ul class="nav navbar-nav pull-xs-right">
          <li v-for="(item, index) in pathList" :key="item.path" v-show="item.isShow" class="nav-item">
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
