import request from '@/utils/request.ts'

/**
 * 获取所有标签
 * @return 标签以逗号分割
 */
export function getTagListAPI() {
  return request({
    url: '/tags'
  })
}
