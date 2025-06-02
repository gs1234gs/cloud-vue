// 引入 request 模块
import request from '@/api/request';
import LOGIN_TOKEN from '@/global/constants';
import { cacheLocal } from '@/utils/cache';

// 定义 loginApi 函数，接收符合 LoginParams 类型的参数
export const loginApi = ({method="",url="", data={}}) => {
  // 返回一个 Promise 对象
  return request({
      url ,
      method,
      data,
    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

export const getUserInfoById = ({method="",url=""}) => {
  // console.log("==============",cacheLocal.getCache(LOGIN_TOKEN))
  return request({
      url,
      method,
      // headers: {
      //   Authorization: cacheLocal.getCache(LOGIN_TOKEN),
      // },
    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

export const getUserMenusById = (id:number) => {
  return request({
      url : `/api/menu/list/${id}`,
      method : "get",
      // headers: {
      //   Authorization: cacheLocal.getCache(LOGIN_TOKEN),
      // },
    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}
