
// 定义登录响应数据的接口（如果需要）
export  interface LoginResponse {
  code: number
  msg: string
  data: any
  // 其他字段可以根据实际响应数据添加
}

export interface UserType {
  userName: string
  password: string
}