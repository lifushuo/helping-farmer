// 商品分类的小仓库
import { defineStore } from 'pinia'
import { reqAttrInfoList, reqC1, reqC2, reqC3 } from '@/api/product/attr'
import { AttrInfo, C1, C2, C3 } from '@/api/product/attr/type'
import { ElMessage } from 'element-plus'

const useCategoryStore = defineStore('categoryStore', {
    // 仓库的变量
    state: () => {
        return {
            c1Id: '' as string | number | null,
            c2Id: '' as string | number | null,
            c3Id: '' as string | number | null,
            c1Data: [] as C1[],
            c2Data: [] as C2[],
            c3Data: [] as C3[],
            AttrInfoList: [] as AttrInfo[],
        }
    },
    // 方法
    actions: {
        //
        async getC1() {
            const result = await reqC1()
            if (result.code == 200) {
                this.c1Data = result.data
            } else {
                // ElMessage.error('获取一级分类信息失败')
                return Promise.reject(new Error('获取一级分类信息失败'))
            }
        },
        async getC2() {
            const result = await reqC2(this.c1Id as unknown as number)
            if (result.code == 200) {
                // c2Data.value = result.data
                this.c2Data = result.data
            } else {
                // ElMessage.error('获取二级分类信息失败')
            }
        },
        async getC3() {
            const result = await reqC3(this.c2Id as unknown as number)
            if (result.code == 200) {
                // c3Data.value = result.data
                this.c3Data = result.data
            } else {
                return Promise.reject(new Error('获取二级分类信息失败'))
                // ElMessage.error('获取二级分类信息失败')
            }
        },
        async getAttrInfoList() {
            const result = await reqAttrInfoList(
                this.c1Id as number,
                this.c2Id as number,
                this.c3Id as number,
            )
            if (result.code == 200) {
                this.AttrInfoList = result.data
            } else {
                ElMessage.error('获取属性列表数据失败')
            }
        },
    },
    // 计算属性
    getters: {},
})

export default useCategoryStore
