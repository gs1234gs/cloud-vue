import LOGIN_TOKEN from "@/global/constants";
import { cacheSession } from "@/utils/cache";
import axios from "axios";

// 创建axios实例 进行基本参数配置
const sendRequest = axios.create({
  // 默认请求地址，根据环境的不同可在.env 文件中进行修改
  // baseURL: import.meta.env.VITE_APP_BASE_API, // 确保有默认值
  // 设置接口访问超时时间
  timeout: 5000, // request timeout
  // 跨域时候允许携带凭证
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
  },
});
sendRequest.interceptors.request.use(
  (config) => {
     // 从缓存中获取登录令牌
     const token = cacheSession.getCache(LOGIN_TOKEN);
    //  console.log("token",token);
     config.headers['Authorization'] = "Bearer " + token;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
sendRequest.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default sendRequest;



