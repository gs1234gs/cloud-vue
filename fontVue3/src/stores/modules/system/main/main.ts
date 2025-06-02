import { getEntireDepts, getEntireMenus, getEntireRoles } from "@/api/system/main/main";
import { defineStore } from "pinia";
import { ref } from "vue";



export const useMainStore = defineStore("main",() =>{
    const entireRoles :any = ref([])
    const entireDepts : any = ref([])
    const entireMenus : any = ref([])


    const getEntireRolesList  = async () => {
        const responseRoles :any= await getEntireRoles()
        const responseDepts :any= await getEntireDepts()
        const responseMenus :any= await getEntireMenus()
        // console.log(responseRoles.data,"=======")
        // console.log("=======",entireDepts.value)
        entireRoles.value = responseRoles.data.data
        entireDepts.value = responseDepts.data.data
        entireMenus.value = responseMenus.data.data
        // console.log("entireRoles",entireRoles.value)
        // console.log("entireDepts",entireDepts.value)
        console.log("entireMenus",entireMenus.value)
    }

    return {
        entireRoles,
        entireDepts,
        entireMenus,
        getEntireRolesList
    }
},

{
  persist: true,
})