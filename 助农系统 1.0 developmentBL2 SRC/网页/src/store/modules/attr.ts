import { defineStore } from 'pinia'
import { AttrState } from '@/store/types/type.ts'
import { getFCategoryList } from '@/api/fcategory'
import { ElMessage } from 'element-plus'
import { getSCategoryList } from '@/api/scategory'
import { getSpuList } from '@/api/production/spu'

export const useAttrStore = defineStore('attrStore', {
    state: (): AttrState => {
        return {
            c1Data: [],
            c2Data: [],
            spuData: [],
            c1Id: '',
            c2Id: '',
            spuId: '',
        }
    },
    actions: {
        async getC1Data() {
            const data = await getFCategoryList()
            if (data.code == 200) {
                this.c1Data = data.data.fcategorylist
            } else {
                ElMessage.error('获取一级分类失败')
            }
        },
        async getC2Data() {
            const result = await getSCategoryList(this.c1Id)
            if (result.code == 200) {
                this.c2Data = result.data.records
            } else {
                // ElMessage.error('获取一级分类信息失败')
                return Promise.reject(new Error('获取二级分类失败'))
            }
        },
        async getSpuData() {
            const result = await getSpuList(
                this.c1Id as number,
                this.c2Id as number,
            )
            if (result.code == 200) {
                this.spuData = result.data.spuList
                console.log(result.data)
            } else {
                // ElMessage.error('获取一级分类信息失败')
                return Promise.reject(new Error('获取spu数据失败'))
            }
        },
    },
})
