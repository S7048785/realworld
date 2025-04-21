<script setup lang="ts">
import {Notifications, notify} from "@kyvg/vue3-notification";

const router = useRouter()
const route = useRoute()

const pathList = [
  {
    path: '/home',
    name: 'Home'
  },
  {
    path: '/article/create',
    name: 'New Article'
  },
  {
    path: '/login',
    name: 'Sign in'
  }
  , {
    path: '/register',
    name: 'Sign up'
  }
]

function toast() {
  notify({
    text: "asdaaaaaaa"
  })
}
</script>

<template>
  <div>
    <nav class="navbar navbar-light">
      <div class="container">
        <a class="navbar-brand" @click="toast" >conduit</a>

        <ul class="nav navbar-nav pull-xs-right">
          <li v-for="(item, index) in pathList" :key="item.path" class="nav-item">
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
    <notifications position="top center" :duration="500" style="top: 20px;"/>
  </div>
</template>

<style lang="less">
  .vue-notification {
    font-size: 18px;
    text-align: center;
  }
</style>
