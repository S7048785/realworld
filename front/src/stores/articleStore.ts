import { defineStore} from "pinia"
import type {ArticleCardRes} from "@/types/response/article.ts";
import {
  createArticleAPI,
  deleteArticleAPI,
  getArticleAPI,
  getArticleCardAPI,
  getArticleCardFeedAPI,
  likeArticleAPI,
  updateArticleAPI
} from "@/api/article.ts"
import {getTagListAPI} from '@/api/tags'
import {notify} from "@kyvg/vue3-notification";
export const useArticleStore = defineStore('article', () => {

  // 分页条件
  // const current = ref<number>(1);
  const size = ref<number>(5);

  // 是否还有更多数据
  const hasMoreData = ref(true);

  // 要显示的标签
  const tagList= ref<string[]>([]);

  // 选中的标签
  const tagSelectedList = ref<string[]>([]);

  // 文章卡片
  const articleCardList = ref<any>([]);

  // 获取所有标签
  const getTagList = async () => {
    const res: any = await getTagListAPI()
    tagList.value = res.data.split(',');
  }

  /**
   * 获取卡片列表
   * @param page
   * @param authorId
   * @return boolean 是否最后一页
   */
  const getArticleList = async (page: number, authorId?: number) => {
    if (page === 1) {
      hasMoreData.value = true;
    }
    if (!hasMoreData.value) {
      return true;
    }
    const res: any = await getArticleCardAPI({
      authorId,
      tagList: tagSelectedList.value.join(',') || '',
      limit: page,
      offset: size.value
    })

    // 第一页数据 重置数组
    if (page === 1) {
      articleCardList.value = res.records
    } else
      articleCardList.value.push(...res.records);
    // 是否最后一页数据
    return hasMoreData.value = res.total !== 0;
  }

  // 获取卡片列表 需要登录校验
  const getArticleFeedList = async (page: number, authorId?: number) => {
    // current.value = page;
    const res: any = await getArticleCardFeedAPI({authorId, tagList: tagSelectedList.value.join(',') || '', limit: page, offset: size.value})

    articleCardList.value = res.records;
  }

  // 文章点赞
  const articleLike = async (id: number | string) => {
    await likeArticleAPI(id);
  }


  return {
    articleCardList,
    tagList,
    tagSelectedList,
    getArticleList,
    getArticleFeedList,
    getTagList,
    articleLike
  }
})
