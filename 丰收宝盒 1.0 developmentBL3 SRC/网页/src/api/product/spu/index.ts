// 商品SPU 的API

import request from '@/utils/request.ts'
import {
    Response,
    SKUData,
    SKUInfoResponse,
    SPUData,
    SPUIMagesResponse,
    SPUPageResponse,
    SPUSaleAttrResponse,
    SPUSaleValueListResponse,
} from '@/api/product/spu/type.ts'

enum API {
    // 获取SPU属性的 分页数据 /admin/product/{page}/{limit}
    GETSPUPAGE_URL = '/admin/product/',
    // 获取SPU下的全部照片
    GETSPUIMAGES_URL = '/admin/product/spuImageList/',
    // 获取SPU下的销售属性价值列表 /admin/product/spuSaleAttrList/{spuId}
    GETSPUATTRLIST_URL = '/admin/product/spuSaleAttrList/',
    // 获取SPU下的全部的属性列表 /admin/product/baseSaleAttrList
    GETSPUSALEATTRLIST_URL = '/admin/product/baseSaleAttrList',

    //保存一个新的SPU 的接口
    POSTSAVESPU_URL = '/admin/product/saveSpuInfo',

    //更新一个以及存在的SPU的接口
    POSTUPDATESPU_URL = '/admin/product/updateSpuInfo',

    // 新增一个SKU
    POSTADDSKUINFO_URL = '/admin/product/saveSkuInfo',

    // 查看某一个已有的spu下全部的售卖的sku
    GETSKUINFO_URL = '/admin/product/findBySpuId/',

    // 删除一个spu /admin/product/deleteSpu/{spuId}
    DELTESPU_URL = '/admin/product/deleteSpu/',
}

// 获取一个三级分类下已有的SPU分类
export const reqSPUPage = (
    page: number,
    limit: number,
    category3Id: string | number,
) => {
    return request.get<any, SPUPageResponse>(
        API.GETSPUPAGE_URL + `${page}/${limit}?category3Id=${category3Id}`,
    )
}

// 获取SPU的图片
export const reqSPUIMAGES = (spuId: number) => {
    return request.get<any, SPUIMagesResponse>(API.GETSPUIMAGES_URL + spuId)
}

// 获取SPU销售属性的 属性
export const reqSPUSaleAttrValueList = (spuId: number) => {
    return request.get<any, SPUSaleValueListResponse>(
        API.GETSPUATTRLIST_URL + spuId,
    )
}

// 获取SPU销售属性
export const reqSPUSaleAttrList = () => {
    return request.get<any, SPUSaleAttrResponse>(API.GETSPUSALEATTRLIST_URL)
}

// 更新或者新增一个SPU
export const reqAddOrUpdateSpu = (data: SPUData) => {
    if (data.id) {
        // 更新一个已存在的SPU数据
        return request.post<any, Response>(API.POSTUPDATESPU_URL, data)
    } else {
        // 添加一个新的 SPU 数据
        return request.post<any, Response>(API.POSTSAVESPU_URL, data)
    }
}

export const reqAddSku = (data: SKUData) => {
    return request.post<any, Response>(API.POSTADDSKUINFO_URL, data)
}

export const reqGetSkuInfoBySpuId = (spuId: number) => {
    return request.get<any, SKUInfoResponse>(API.GETSKUINFO_URL + spuId)
}

export const reqDeleteSpu = (spuId: number) => {
    return request.delete<any, Response>(API.DELTESPU_URL + spuId)
}
