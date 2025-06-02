import type { IModuleConfig } from "@/components/base-module-form/config-module/type";
import { h } from "vue";
export const moduleConfig:IModuleConfig = {
 pageName: "role",
  header: {
    newTitle: "新建角色",
    editTitle: "编辑角色"
  } ,
   formItem: [
    {
      type: "input",
      label: "角色名称",
      prop: "roleName",
      width: "150px",
      slot: "menuName",
      placeholder: "请输入角色名称",
    },
    {
      type: "input",
      label: "权限字符",
      prop: "roleKey",
      placeholder: "请输入权限字符",
    },
  ],

}