<script setup lang="ts">
import {useUserStore} from "@/stores/userStore.ts";
import {isPassword, isUserName} from "@/utils/regex.ts";
import {USER_CONSTANT} from "@/constant/user.ts";
import {notify} from "@kyvg/vue3-notification";

const router = useRouter()
const userStore = useUserStore();

const loginForm = reactive({
  username: '',
  password: ''
})

const errorMsg = ref<string>();

const login = async () => {

  // 用户名校验
  if (!isUserName(loginForm.username)) {
    errorMsg.value = USER_CONSTANT.USERNAME_VALID
    return;
  }
  // 密码校验
  if (!isPassword(loginForm.password)) {
    errorMsg.value = USER_CONSTANT.PASSWORD_VALID;
    return;
  }
  // 清空错误消息
  errorMsg.value = ''

  // 发送登录请求
  const loginSuccess = await userStore.login(loginForm.username, loginForm.password);
  if (!loginSuccess) {
    // 清空密码
    loginForm.password = '';
    return;
  }
  notify({
    text: '登录成功',
    type: 'success'
  })
  await router.push('/home')
}
</script>

<template>
  <div class="auth-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Sign in</h1>
          <p class="text-xs-center">
            <a href="/register">Need an account?</a>
          </p>

          <ul class="error-messages" v-show="errorMsg">
            <li v-text="errorMsg"></li>
          </ul>

          <form @submit.prevent="login">
            <fieldset class="form-group">
              <input class="form-control form-control-lg" type="text" placeholder="Username"
                     autocomplete="off"
                     v-model="loginForm.username"/>
            </fieldset>
            <fieldset class="form-group">
              <input class="form-control form-control-lg" autocomplete="current-password"
                     type="password" placeholder="Password" v-model="loginForm.password"/>
            </fieldset>
            <button class="btn btn-lg btn-primary pull-xs-right">Sign in</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
