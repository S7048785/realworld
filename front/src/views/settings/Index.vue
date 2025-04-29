<script  lang="ts" setup="">
import {useUserStore} from "@/stores/userStore.ts";
import {notify} from "@kyvg/vue3-notification";
const userStore = useUserStore();
const router = useRouter();

const profile = reactive({
  avatar: userStore.userInfo?.avatar.valueOf() || '',
  nickname: userStore.userInfo?.nickname.valueOf() || '',
  bio: userStore.userInfo?.bio.valueOf() || '',
  password: ''
})

const errorMsg = ref();

// 更新用户信息
const update = async () => {
  // 校验信息
  if (!profile.nickname) {
    errorMsg.value = '昵称不能为空'
    return;
  }
  if (!profile.avatar) {
    errorMsg.value = '头像不能为空'
    return;
  }

  // TODO: 更新用户信息
  const isSuccess = await userStore.updateUserInfo(profile)
  if (isSuccess) {
    notify({
      text: '用户信息更新成功',
      type: 'success'
    })
  }
}

// 退出登录
const logout = async () => {
  await userStore.logout();
  // 跳转路由
  await router.push('/home')
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
                <p>头像Url：</p>
                <input v-model="profile.avatar" class="form-control" type="text" placeholder="URL of profile picture" />
              </fieldset>
              <fieldset class="form-group">
                <p>昵称：</p>
                <input v-model="profile.nickname" class="form-control form-control-lg" type="text" placeholder="Your NickName" autocomplete="off"/>
              </fieldset>
              <fieldset class="form-group">
                <p>个人简介：</p>
              <textarea
                v-model="profile.bio"
                class="form-control form-control-lg"
                rows="8"
                placeholder="Short bio about you"
              ></textarea>
              </fieldset>
              <fieldset class="form-group">
                <p>新密码（不修改不填）：</p>
                <input
                  v-model="profile.password"
                  class="form-control form-control-lg"
                  type="password"
                  autocomplete="current-password"
                  placeholder="New Password"
                />
              </fieldset>
              <button @click.prevent="update" class="btn btn-lg btn-primary pull-xs-right">Update Settings</button>
            </fieldset>
          </form>
          <hr />
          <button @click.prevent="logout" class="btn btn-outline-danger">Or click here to logout.</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
