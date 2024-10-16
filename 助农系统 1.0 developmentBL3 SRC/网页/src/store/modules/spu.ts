// 商品分类的小仓库
import { defineStore } from 'pinia'
import { getFCategoryList } from '@/api/fcategory'
import { FCategoryList } from '@/api/fcategory/type.ts'
import { getSCategoryList } from '@/api/scategory'
import { SCategoryPageType } from '@/api/scategory/type.ts'
import { PageData } from '@/api'
import { AttrInfo } from "@/api/product/attr/type.ts";

const useSpuStore = defineStore('spuStore', {
    // 仓库的变量
    state: () => {
        return {
            c1Id: '' as string | number | null,
            c2Id: '' as string | number | null,
            name: '' as string | number | null,
            c1Data: [] as FCategoryList,
            c2Data: [] as PageData<SCategoryPageType>,
            AttrInfoList: [] as AttrInfo[],
        }
    },
    // 方法
    actions: {
        //
        async getC1() {
            const result = await getFCategoryList()
            if (result.code == 200) {
                this.c1Data = result.data
            } else {
                return Promise.reject(new Error('获取一级分类信息失败'))
            }
        },
        async getC2() {
            const result = await getSCategoryList(this.c1Id)
            if (result.code == 200) {
                this.c2Data = result.data
            } else {
                return Promise.reject(new Error('获取二级分类信息失败'))
            }
        },
    },
    // 计算属性
    getters: {},
})

export default useSpuStore
