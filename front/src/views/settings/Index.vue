<script  lang="ts" setup="">
import {useUserStore} from "@/stores/userStore.ts";
const userStore = useUserStore();

const profile = reactive({
  avatar: userStore.userInfo?.avatar.valueOf() || '',
  username: userStore.userInfo?.username.valueOf() || '',
  bio: userStore.userInfo?.bio.valueOf() || '',
  password: ''
})

const errorMsg = ref();

// 更新用户信息
const update = async () => {
  // 校验信息
  if (!profile.username) {
    errorMsg.value = '用户名不能为空'
    return;
  }
  if (!profile.avatar) {
    errorMsg.value = '头像不能为空'
    return;
  }

  // TODO: 更新用户信息
}

// 退出登录
const logout = async () => {
  // TODO: 退出登录
}

</script>

<template>
  <div class="settings-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Your Settings</h1>

          <ul class="error-messages">
            <li>That name is required</li>
          </ul>

          <form>
            <fieldset>
              <fieldset class="form-group">
                <input :value="profile.avatar || ''" class="form-control" type="text" placeholder="URL of profile picture" />
              </fieldset>
              <fieldset class="form-group">
                <input :value="profile.username || ''" class="form-control form-control-lg" type="text" placeholder="Your Name" />
              </fieldset>
              <fieldset class="form-group">
              <textarea
                v-text="profile.bio"
                class="form-control form-control-lg"
                rows="8"
                placeholder="Short bio about you"
              ></textarea>
              </fieldset>
              <fieldset class="form-group">
                <input
                  :value="profile.password"
                  class="form-control form-control-lg"
                  type="password"
                  placeholder="New Password"
                />
              </fieldset>
              <button @click="update" class="btn btn-lg btn-primary pull-xs-right">Update Settings</button>
            </fieldset>
          </form>
          <hr />
          <button @click="logout" class="btn btn-outline-danger">Or click here to logout.</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
