import { defineStore } from "pinia";
import { ref } from "vue";
import { getProductCount } from "@/api/goods/statistics/statistics";
import { GOODS_STATISTICS_GLOBAL_NAME, STATISTICS_GLOBAL_NAME } from "@/global/statisticsGlobalName";
import { cacheSession } from "@/utils/cache";

export const useStatisticsStore = defineStore(STATISTICS_GLOBAL_NAME,() =>{
    const goodsStatistics : any = ref(cacheSession.getCache(GOODS_STATISTICS_GLOBAL_NAME) || [])

    const getGoodsStatistics = async () => {
        const response :any= await getProductCount()
        //将数据缓存到session中
        cacheSession.setCache(GOODS_STATISTICS_GLOBAL_NAME,response.data.data)
        console.log("response.data.data",response.data.data)
        console.log("response.data.data",goodsStatistics.value)
    }

    return {
        goodsStatistics,
        getGoodsStatistics
    }


},
{
    persist: true
})