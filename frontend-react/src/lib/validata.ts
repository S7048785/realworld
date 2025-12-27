interface UserFormData {
  username: string;
  password: string;
}

/**
 * 校验用户表单数据
 * @param data 包含 username 和 password 的表单数据
 * @param onError 校验失败时的回调函数（可选）
 * @returns 校验通过返回 true，否则返回 false
 */
export function validateUserForm(
  data: UserFormData,
  onError: (message: string) => void
): boolean {
  const errors: {
    username?: string;
    password?: string;
  } = {};

  // 校验 username
  const isUsernameValid =
    data.username.length >= 4 &&
    data.username.length <= 12 &&
    /^[a-zA-Z0-9]+$/.test(data.username);

  if (!isUsernameValid) {
    if (data.username.length < 4 || data.username.length > 12) {
      errors.username = "用户名长度必须为4-12个字符";
    } else if (!/^[a-zA-Z0-9]+$/.test(data.username)) {
      errors.username = "用户名只能包含字母和数字";
    }
  }

  // 校验 password
  const isPasswordValid =
    data.password.length >= 6 && data.password.length <= 17;

  if (!isPasswordValid) {
    errors.password = "密码长度必须为6-17个字符";
  }

  // 如果有错误且提供了回调函数
  if (!isUsernameValid || !isPasswordValid) {
    onError(errors.username || errors.password || "登录失败");
  }

  return isUsernameValid && isPasswordValid;
}
