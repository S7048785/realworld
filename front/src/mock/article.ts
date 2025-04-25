import type {Article, ArticleCard} from "@/types/response/article";

export let articleCardListMock: ArticleCard[] = [
  {
    id: '1',
    title: 'How to build webapps that scale',
    description: 'This is the description for the post',
    tagList: ['realworld', 'implementations', 'abc'],
    likeCount: 29,
    liked: true,
    createdAt: '2023-05-01',
    author: {
      username: 'Eric Simons',
      avatar: 'http://i.imgur.com/Qr71crq.jpg',
    }
  },
  {
    id: '2',
    title: 'The song you won\'t ever stop singing. No matter how hard you try.',
    description: 'This is the description for the post',
    tagList: ['Spring', 'Java'],
    likeCount: 32,
    liked: false,
    createdAt: '2024-01-31',
    author: {
      username: 'Albert Pai',
      avatar: 'http://i.imgur.com/N4VcUeJ.jpg',
    }
  },
  {
    id: '3',
    title: '测试标题1',
    description: '测试描述1',
    tagList: ['标签1', '标签2', '标签3'],
    likeCount: 9,
    liked: true,
    createdAt: '2023-05-01',
    author: {
      username: '陈九',
      avatar: 'https://java-web-lws.oss-cn-beijing.aliyuncs.com/hm.jpg',
    }
  }
]
