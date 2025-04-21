<script setup lang="ts">
const router = useRouter()
const route = useRoute()

const pathList = [{
  path: '/home',
  name: 'Home'
}, {
  path: '/login',
  name: 'Sign in'
}, {
  path: '/register',
  name: 'Sign up'
}]
</script>

<template>
  <div>
    <nav class="navbar navbar-light">
      <div class="container">
        <a class="navbar-brand" href="/">conduit</a>
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
  </div>
</template>
