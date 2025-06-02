import request from "@/api/request";

// 获取所有角色
export const getEntireRoles = ()=>{

  return request({
      url: '/api/role/list',
      method: 'get'

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

  // 获取所有部门
  export const getEntireDepts = ()=>{

    return request({
        url: '/api/dept/list',
        method: 'get',

      }).then(res => {
        return res;
      }).catch(err => {
        throw err;
      })

  }

  export const getEntireMenus = ()=>{

    return request({
        url: '/api/menu/list',
        method: 'get',

      }).then(res => {
        return res;
      }).catch(err => {
        throw err;
      })
  }