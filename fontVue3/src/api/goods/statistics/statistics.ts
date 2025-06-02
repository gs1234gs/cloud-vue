import sendRequest from "@/api/request";
//获取商品统计数量
export const getProductCount = ()=>{

  return sendRequest({
      url: '/api/product/amount',
      method: 'get'

    }).then(res => {
      return res;
    }).catch(err => {
      throw err;
    })

}