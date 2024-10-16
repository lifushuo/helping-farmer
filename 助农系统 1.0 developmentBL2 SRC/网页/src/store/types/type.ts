import type { RouteRecordRaw } from 'vue-router'
import { SCategoryPageType } from '@/api/scategory/type.ts'
import { SpuList } from '@/api/production/spu/type.ts'

// 定义小仓库数据state类型

export interface UserState {
    token: string | null
    menuRoutes: RouteRecordRaw[]
    username: string | null
    avatar: string | null
    button: string[]
}

export interface AttrState {
    c1Id: number | undefined
    c2Id: number | undefined
    spuId: number | undefined
    c1Data: { id: number; name: string }[]
    c2Data: SCategoryPageType[]
    spuData: SpuList[]
}
