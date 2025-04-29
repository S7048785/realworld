<script setup lang="ts">
import {useUserStore} from "@/stores/userStore.ts";
import type {ProfileRes} from "@/types/response/user.ts";
import type {ArticleCardRes} from "@/types/response/article.ts";
import {useArticleStore} from "@/stores/articleStore.ts";
import {loginValidToast} from "@/utils/toast.ts";
import {notify} from "@kyvg/vue3-notification";
import {useDebounceFn} from "@vueuse/core";

const userStore = useUserStore();
const articleStore = useArticleStore();
const route = useRoute();
const router = useRouter()
console.log(route.params.userId)

const userInfo = ref<ProfileRes>({} as any);

// 点赞文章
const likeActive = (articleCard: ArticleCardRes) => {

  // 登录校验
  if (!userStore.userInfo) {
    loginValidToast();
    return;
  }
  articleCard.liked = !articleCard.liked;
  articleCard.likeCount += articleCard.liked ? 1 : -1;
  articleStore.articleLike(articleCard.id);
}
// 点赞函数消抖
const debouncedLikeFn = useDebounceFn(likeActive, 200)

// 下滑新增卡片
const addArticleCard = async () => {

    // 若卡片列表个数过小, 则不执行
    if (articleStore.articleCardList.length < 5)
      return;
    await articleStore.getArticleList(null, route.params.username as string);
}
// 新增卡片消抖
// const debouncedAddCardFn = useDebounceFn(addArticleCard, 200);

onMounted(async () => {
  // 清空标签列表
  articleStore.clearTags();
  userInfo.value = await userStore.getUserInfo(route.params.username as string)
  if (!userInfo) {
    return;
  }
  await articleStore.getArticleList(1, userInfo.value.username);
})

</script>

<template>
  <div class="profile-page">
    <div class="user-info">
      <div class="container">
        <div class="row">
          <div class="col-xs-12 col-md-10 offset-md-1">
            <img :src="userInfo.avatar" class="user-img" alt=""/>
            <h4 v-text="userInfo.username"></h4>
            <p v-text="userInfo.bio">
            </p>
            <button class="btn btn-sm btn-outline-secondary action-btn">
              <i class="ion-plus-round"></i>
              &nbsp; Follow {{userInfo.username}}
            </button>
            <button @click="router.push('/settings')" v-show="userInfo.id === userStore.userInfo?.id" class="btn btn-sm btn-outline-secondary action-btn">
              <i class="ion-gear-a"></i>
              &nbsp; Edit Profile Settings
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-md-10 offset-md-1">
          <div class="articles-toggle">
            <ul class="nav nav-pills outline-active">
              <li class="nav-item">
                <a class="nav-link active" href="">我 的</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="">已 收 藏</a>
              </li>
            </ul>
          </div>

          <div class="article-preview"
               v-for="item in articleStore.articleCardList as ArticleCardRes[]"
               :key="item.id"
          >
            <div class="article-meta">
              <a href="/profile/eric-simons"><img :src="item.avatar" alt=""/></a>
              <div class="info">
                <router-link :to="`/profile/${item.author}`"></router-link>
                <a href="/profile/eric-simons" class="author" v-text="item.author"></a>
                <span class="date" v-text="item.createdAt"></span>
              </div>
              <button @click="debouncedLikeFn(item)" :class="{'btn-outline-primary': item.liked}"
                      class="btn btn-sm pull-xs-right">
                <i class="ion-heart"></i> {{ item.likeCount }}
              </button>
            </div>
            <a :href="`/article/${item.id}`" class="preview-link">
              <h1 v-text="item.title"></h1>
              <p v-text="item.description"></p>
              <span>Read more...</span>
              <ul class="tag-list" v-show="item.tags">
                <li class="tag-default tag-pill tag-outline" v-for="item1 in ((item.tags || '').split(','))"
                    v-text="item1"></li>
              </ul>
            </a>
          </div>
          <hr v-card-lazy="addArticleCard" style="margin: 0">
        </div>
        </div>
      </div>
    </div>
</template>

<style scoped>

</style>
