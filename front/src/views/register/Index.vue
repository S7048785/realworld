<script  lang="ts" setup="">
import {useUserStore} from "@/stores/userStore.ts";
import router from "@/router";
const userStore = useUserStore();
const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: ''
})

const errorMsg = ref<string>();

const register = async () => {
  console.log(1)
  // 校验密码相同
  if (registerForm.password !== registerForm.confirmPassword) {
    errorMsg.value = '请确认密码是否一致'
    return
  }
  // 清空错误消息
  errorMsg.value = ''

  // 发送注册请求
  const registerSuccess = await userStore.register(registerForm.username, registerForm.password)

  if (!registerSuccess) {
    return;
  }
  // 跳转首页
  router.push('/home')
}

</script>

<template>
  <div class="auth-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Sign up</h1>
          <p class="text-xs-center">
            <a href="/login">Have an account?</a>
          </p>

          <ul class="error-messages">
            <li>That email is already taken</li>
          </ul>

          <form @submit.prevent="register">
            <fieldset class="form-group">
              <input v-model="registerForm.username" class="form-control form-control-lg" type="text" placeholder="Username" autocomplete="off"/>
            </fieldset>
<!--            <fieldset class="form-group">-->
<!--              <input class="form-control form-control-lg" type="text" placeholder="Email" />-->
<!--            </fieldset>-->
            <fieldset class="form-group">
              <input v-model="registerForm.password" class="form-control form-control-lg" type="password" placeholder="Password" autocomplete="current-password"/>
            </fieldset>
            <fieldset class="form-group">
              <input v-model="registerForm.confirmPassword" class="form-control form-control-lg" type="password" placeholder="Confirm password" autocomplete="current-password"/>
            </fieldset>
            <button class="btn btn-lg btn-primary pull-xs-right">Sign up</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
