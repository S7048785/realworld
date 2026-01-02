// src/api/index.ts
// 统一API导出文件
import articleApi from './article';
import userApi from './user';
import commentApi from './comment';

// 导出合并后的API对象
export default {
	article: articleApi,
	user: userApi,
	comment: commentApi
};

// 也可以导出命名API，方便按需导入
export { articleApi, userApi, commentApi };
