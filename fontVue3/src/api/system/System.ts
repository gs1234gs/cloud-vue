import request from "../request";

/*
 * 通用增删改查请求
 */

export const postPageListData = ( pageName?: string,queryInfo?: any) => {
  // 确保 pageName 被传入，并正确拼接 URL
  return request({
    url: `/api/${pageName}/list`, // 使用模板字符串拼接 URL
    method: 'post',
    // 如果 roleInfoQuery 需要传递到请求中，可以添加到 params 或 data 中
    data: queryInfo, // 假设 roleInfoQuery 是查询参数
  })
    .then((res) => {
      return res; // 直接返回响应结果
    })
    .catch((err) => {
      throw err; // 抛出错误，让调用者处理
    });
};


// 分页查询，跟据页码查询
export const getPageListData = (pageName?:string) => {

  // console.log("===================",currentPage, pageSize);
  return request({
      url: `/api/${pageName}/list`,
      method: 'get',

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

//删除页面信息，通过id
export const deletePageById = (pageName: string ,pageId : number) => {

  return request({
      url: `/api/${pageName}/delete/${pageId}`,
      method: 'delete',

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

// 添加页面信息
export const postAddPageInfo = (pageName: string ,pageInfo?: any) => {

  return request({
      url: `/api/${pageName}/add`,
      method: 'post',
      data: pageInfo

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}

// 修改页面信息
export const putUpdatePage = (pageName: string ,pageInfo: any) => {

  return request({
      url: `/api/${pageName}/update`,
      method: 'put',
      data: pageInfo

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}
