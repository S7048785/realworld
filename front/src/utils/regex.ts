
/**
 * 校验用户名: 长度为2-12位的汉字字母数字下划线(开头必须是字母)
 */
export function isUserName(str: string): boolean {
  return /^[\u4E00-\u9FA5a-zA-z0-9]{2,12}$/.test(str);

}
/**
 * 校验密码 6-17位字母数字下划线
 */
export function isPassword(str: string): boolean {
  return /^\w{5,17}$/.test(str);
}
