import { defineStore} from "pinia"
import {
  createArticleAPI,
  deleteArticleAPI,
  getArticleAPI,
  getArticleCardAPI,
  getArticleCardFeedAPI,
  likeArticleAPI,
  updateArticleAPI
} from "@/api/article.ts"
import {useDebounceFn} from "@vueuse/core";
import {getTagListAPI} from '@/api/tags'
export const useArticleStore = defineStore('article', () => {

  // 分页条件
  // const current = ref<number>(1);
  const size = ref<number>(10);

  // 是否还有更多数据
  const hasMoreData = ref(true);

  // 要显示的标签
  const tagList= ref<string[]>([]);

  // 选中的标签
  const tagSelectedList = ref<string[]>([]);

  // 文章卡片
  const articleCardList = ref<any>([]);

  // 当前页码
  const currentPage = ref(1);

  // 获取所有标签
  const getTagList = async () => {
    const res: any = await getTagListAPI()
    tagList.value = res.data.split(',');
  }

  /**
   * 获取卡片列表
   * @param page
   * @param username
   * @return boolean 是否最后一页
   */
  const getArticleList = useDebounceFn(async (page?: number | any, username?: string) => {
    if (page === 1) {
      currentPage.value = page;
      hasMoreData.value = true;
    }
    if (!hasMoreData.value) {
      // 防止没有数据后 仍然请求。且会让toast只显示一次
      return true;
    }
    const res: any = await getArticleCardAPI({
      username,
      tagList: tagSelectedList.value.join(',') || '',
      limit: currentPage.value,
      offset: size.value})
    currentPage.value++;
    // 第一页数据 重置数组
    if (page === 1) {
      articleCardList.value = res.records
    } else
      articleCardList.value.push(...res.records);
    // 是否最后一页数据
    return hasMoreData.value = !(res.total < res.pageSize || res.toal == 0);
  }, 200);

  // 获取卡片列表 需要登录校验
  const getArticleFeedList = async (page?: number, username?: string) => {
    if (page === 1) {
      currentPage.value = page;
      hasMoreData.value = true;
    }
    // 判断还有没有更多数据
    if (!hasMoreData.value) {
      return true;
    }
    const res: any = await getArticleCardFeedAPI({
      username,
      tagList: tagSelectedList.value.join(',') || '',
      limit: currentPage.value,
      offset: size.value})
    currentPage.value++;
    // 第一页数据 重置数组
    if (page === 1) {
      articleCardList.value = res.records
    } else
      articleCardList.value.push(...res.records);
    // 是否最后一页数据
    return hasMoreData.value = res.total !== 0;
  }

  // 文章点赞
  const articleLike = async (id: number | string) => {
    await likeArticleAPI(id);
  }

  // 清空标签列表
  const clearTags = () => {
    tagSelectedList.value = [];
  }


  return {
    articleCardList,
    tagList,
    tagSelectedList,
    getArticleList,
    getArticleFeedList,
    getTagList,
    articleLike,
    clearTags
  }
})
