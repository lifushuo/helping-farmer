import request from '@/utils/request.ts'
import {
    Response,
    SkuInfoResponse,
    SkuPageResponse,
} from '@/api/product/sku/type.ts'

enum API {
    // 获取sku 分页数据 /admin/product/list/{page}/{limit}
    GETSKUPAGE_URL = '/admin/product/list/',
    // 修改sku为销售状态 /admin/product/onSale/{skuId}
    GETSKUONSALE_URL = '/admin/product/onSale/',
    // 修改sku 为下架状态/admin/product/cancelSale/{skuId}
    GETSKUCANCELSALE_URL = '/admin/product/cancelSale/',
    //获取 sku的具体信息 /admin/product/getSkuInfo/{skuId}
    GETSKUINFO_URL = '/admin/product/getSkuInfo/',
    //删除 sku 根据 id /admin/product/deleteSku/{skuId}
    DELETESKU_URL = '/admin/product/deleteSku/',
}

export const reqGetSkuPage = (page: number, limit: number) => {
    return request.get<any, SkuPageResponse>(
        API.GETSKUPAGE_URL + `${page}/${limit}`,
    )
}
export const reqUpdateSkuOnSale = (skuId: number) => {
    return request.get<any, Response>(API.GETSKUONSALE_URL + skuId)
}

export const reqUpdateSkuCancelSale = (skuId: number) => {
    return request.get<any, Response>(API.GETSKUCANCELSALE_URL + skuId)
}

export const reqGetSkuInfoById = (skuId: number) => {
    return request.get<any, SkuInfoResponse>(API.GETSKUINFO_URL + skuId)
}

//删除sku
export const reqDeleteSku = (skuId: number) => {
    return request.delete<any, Response>(API.DELETESKU_URL + skuId)
}
