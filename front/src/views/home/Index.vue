<script setup lang="ts">
import {articleCardListMock} from "@/mock/article.ts";
import type {ArticleCard} from "@/types/response/article.ts";

const articleCardList = reactive(articleCardListMock);

// 已选中的标签页的索引
const selectedTabIndex = ref<number>(0);

// 标签页列表
const tabList = reactive([
  "Your Feed",
  "Global Feed",
])

// 切换标签页
const toggleIndex = (index: number) => {
  selectedTabIndex.value = index;
}

// 已选中的标签的索引
const selectedTagIndex = reactive<number[]>([]);

// 标签列表
const tagList = reactive<string[]>([
  "programming", "javascript", "emberjs", "angularjs", "react", "mean", "node", "rails"
]);

// 选中/取消选中标签
const toggleTag = (index: number) => {
  if (selectedTagIndex.includes(index)) {
    selectedTagIndex.splice(selectedTagIndex.indexOf(index), 1);
  } else {
    selectedTagIndex.push(index);
    // TODO: 查询符合标签的文章
  }
}

// 清除选中的标签
const clearTag = () => {
  selectedTagIndex.splice(0);
}

// 点赞文章
const likeActive = (articleCard: ArticleCard) => {

  articleCard.liked = !articleCard.liked;
  articleCard.likeCount += articleCard.liked ? 1 : -1;
  // TODO: 点赞文章
}


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
              <li v-for="(item, index) in tabList" @click="toggleIndex(index)" class="nav-item">
                <a :class="{'active': selectedTabIndex === index}" class="nav-link" href="javascript: void(0)" v-text="item"></a>
              </li>
            </ul>
          </div>

<!--          文章预览-->
          <div class="article-preview" v-for="(item, index) in articleCardList" :key="item.id">
            <div class="article-meta">
              <a href="/profile/eric-simons"><img :src="item.author.avatar" /></a>
              <div class="info">
                <router-link :to="`/profile/${item.author.username}`"></router-link>
                <a href="/profile/eric-simons" class="author" v-text="item.author.username"></a>
                <span class="date" v-text="item.createdAt"></span>
              </div>
              <button @click="likeActive(item)" :class="{'btn-outline-primary': item.liked}" class="btn btn-sm pull-xs-right">
                <i class="ion-heart"></i> {{item.likeCount}}
              </button>
            </div>
            <a :href="`/article/${item.id}`" class="preview-link">
              <h1 v-text="item.title"></h1>
              <p v-text="item.description"></p>
              <span>Read more...</span>
              <ul class="tag-list">
                <li class="tag-default tag-pill tag-outline" v-for="item1 in item.tagList" v-text="item1"></li>
              </ul>
            </a>
          </div>

<!--          分页条-->
          <ul class="pagination">
            <li class="page-item active">
              <a class="page-link" href="">1</a>
            </li>
            <li class="page-item">
              <a class="page-link" href="">2</a>
            </li>
          </ul>
        </div>

<!--        侧边标签列表-->
        <div class="col-md-3">
          <div class="sidebar">
            <div><span>Popular Tags</span> <button @click="clearTag" style="border: 1px solid #ccc">clear</button></div>
            <div class="tag-list" style="margin-top: 10px">
              <a v-for="(item, index) in tagList"
                 @click="toggleTag(index)"
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
