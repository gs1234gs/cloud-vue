import { useLoginStore } from "@/stores/modules/login/login";

export const usePermission = (permission: string) => {
  const loginStore = useLoginStore();
  const {permsMenus} = loginStore
  const isFlag = permsMenus.some((item: any) => {
    return item.includes(permission)
  })

  return !!isFlag
}