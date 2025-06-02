import type { IModuleConfig } from "@/components/base-module-form/config-module/type";
import CustomComponent from '../custom/CustomComponent.vue';
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
      label: "角色标识",
      prop: "roleKey",
      placeholder: "请输入权限字符",
    },
    {
      type: "input",
      label: "角色状态",
      prop: "status",
      placeholder: "请输入",
    },
    {
      type: "input",
      label: "角色描述",
      prop: "remark",
      placeholder: "请输入角色描述",
    },
    {
  type:'custom',
  label: "菜单权限",
  menuListId: "menuListId",
  slot: {
    name: "customSlot",
    render: () => h(CustomComponent) // 使用 Vue 的 h 函数创建 VNode
  },
}
  ],

}