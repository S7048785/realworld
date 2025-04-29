<script setup lang="ts">
import type {ArticleCardRes} from "@/types/response/article.ts";
import {useArticleStore} from "@/stores/articleStore.ts"
import {useUserStore} from "@/stores/userStore.ts";
import {loginValidToast} from "@/utils/toast.ts";
import {notify} from "@kyvg/vue3-notification";

const routerStore = useRouter()
const userStore = useUserStore();
const articleStore = useArticleStore();

// 已选中的标签页的索引
const selectedTabIndex = ref<number>(1);
// 标签页列表
const tabList = computed(() => [
  {
    name: "Your Feed",
    isShow: userStore.isLogin,
    getList: articleStore.getArticleFeedList
  },
  {
    name: "Global Feed",
    isShow: true,
    getList: articleStore.getArticleList
  }
])


// 切换标签页
const toggleIndex = (index: number) => {
  // 登录校验
  if (index === 0 && !userStore.userInfo) {
    loginValidToast();
    return;
  }
  selectedTabIndex.value = index;
  tabList.value[index].getList(1);
}

// 已选中的标签的索引
const selectedTagIndex = reactive<number[]>([]);

// 选中/取消选中标签
const toggleTag = (item: string, index: number) => {
  if (selectedTagIndex.includes(index)) {
    selectedTagIndex.splice(selectedTagIndex.indexOf(index), 1);
    articleStore.tagSelectedList.splice(articleStore.tagSelectedList.indexOf(item), 1)
  } else {
    selectedTagIndex.push(index);
    articleStore.tagSelectedList.push(item)
  }

}

// 清除选中的标签
const clearTag = () => {
  selectedTagIndex.splice(0);
  articleStore.tagSelectedList = [];
}

// 查询选中的标签
const searchTag = async () => {
  // 重置当前页码
  await articleStore.getArticleList(1);
}

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

const isLoading = ref(false);
// 下滑新增卡片
const addArticleCard = async () => {
  if (isLoading.value) return;
  isLoading.value = true;
  try {
    // 若卡片列表个数过小, 则不执行
    if (articleStore.articleCardList.length < 5)
      return;
    await tabList.value[selectedTabIndex.value].getList();
  } finally {
    isLoading.value = false
  }
}

onMounted(() => {
  articleStore.getArticleList(1);
  articleStore.getTagList();
})

</script>

<template>
  <div class="home-page">
    <div class="banner">
      <div class="container">
        <h1 class="logo-font">conduit</h1>
        <p>A place to share your knowledge.</p>
      </div>
    </div>

    <!--    标签页-->
    <div class="container page">
      <div class="row">
        <div class="col-md-9">
          <div class="feed-toggle">
            <ul class="nav nav-pills outline-active">
              <li v-for="(item, index) in tabList" @click="toggleIndex(index)" v-show="item.isShow" class="nav-item">
                <a :class="{'active': selectedTabIndex === index}" class="nav-link"
                   href="javascript: void(0)" v-text="item.name"></a>
              </li>
            </ul>
          </div>

          <!--          文章卡片 -->
          <div >
            <div class="article-preview"
                 v-for="item in articleStore.articleCardList as ArticleCardRes[]"
                 :key="item.id"
            >
              <div class="article-meta">
                <router-link :to="`/profile/${item.author}`"><img :src="item.avatar" alt=""/></router-link>
<!--                <a  :href="`/profile/${item.id}`"><img :src="item.avatar" alt=""/></a>-->
                <div class="info">
                  <router-link :to="`/profile/${item.author}`" class="author" v-text="item.author"></router-link>
<!--                  <a href="/profile/eric-simons" class="author" v-text="item.author"></a>-->
                  <span class="date" v-text="item.createdAt"></span>
                </div>
                <button @click="likeActive(item)" :class="{'btn-outline-primary': item.liked}"
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

        <!--        侧边标签列表-->
        <div class="col-md-3">
          <div class="sidebar">
            <div><span>Popular Tags</span>
              <div style="float: right">
                <button @click="clearTag" style="border: 1px solid #ccc; ">clear</button>
                <button @click="searchTag" style="border: 1px solid #ccc; ">search</button>
              </div>
            </div>

            <div class="tag-list" style="margin-top: 20px">
              <a v-for="(item, index) in articleStore.tagList"
                 @click="toggleTag(item, index)"
                 :key="item"
                 :class="{'tag-primary': selectedTagIndex.includes(index)}"
                 v-text="item"
                 class="tag-pill tag-default"
                 href="javascript: void(0)"
              ></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
