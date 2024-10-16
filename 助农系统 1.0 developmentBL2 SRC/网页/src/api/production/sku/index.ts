import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'

import {
    SkuAddType,
    SkuEditType,
    SkuInfo,
    SkuInfoDetail,
} from '@/api/production/sku/type.ts'

enum API {
    SKU_BASE = '/admin/sku',
    SKU_LIST = '/admin/sku/list',
    SKU_DETAIL = '/admin/sku/detail',
    SKU_STATUS = '/admin/sku/status',
    SKU_EDIT_DETAIL = '/admin/sku/edit/detail',
}

export const getSKUEditDetail = async (id: number) => {
    return await request2.get<any, ResponseData<SkuEditType>>(
        API.SKU_EDIT_DETAIL + `?id=${id}`,
    )
}

export const getSKUInfoList = async (spuId: number) => {
    return await request2.get<any, ResponseData<PageData<SkuInfo>>>(
        API.SKU_LIST + `?spuId=${spuId}`,
    )
}

export const postSKUAdd = async (param: SkuAddType) => {
    return await request2.post<any, ResponseData<any>>(API.SKU_BASE, param)
}

export const putSkuEdit = async (param: SkuEditType) => {
    return await request2.put<any, ResponseData<any>>(API.SKU_BASE, param)
}

export const deleteSkuById = async (id: number) => {
    return await request2.delete<any, ResponseData<any>>(
        API.SKU_BASE + `?id=${id}`,
    )
}

export const postSkuStatus = async (id: number, status: number) => {
    return await request2.post<any, ResponseData<any>>(
        API.SKU_STATUS + `/${status}?id=${id}`,
    )
}

export const getSkuDetailById = async (id: number) => {
    return await request2.get<any, ResponseData<SkuInfoDetail>>(
        API.SKU_DETAIL + `?id=${id}`,
    )
}
