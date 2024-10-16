import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'
import {
    SpuDTOType,
    SPUInfoDetailType,
    SpuList,
    SpuPageType,
} from '@/api/production/spu/type.ts'
enum API {
    SPU_PAGE = '/admin/spu/page',
    SPU_BASE = '/admin/spu',
}

export const getSpuDetailById = async (spuId: number) => {
    return await request2.get<any, ResponseData<SPUInfoDetailType>>(
        API.SPU_BASE + `/${spuId}`,
    )
}

export const deleteSpuById = async (id: number) => {
    return await request2.delete<any, ResponseData<any>>(
        API.SPU_BASE + `?id=${id}`,
    )
}

export const putSpuDataEdit = async (param: SpuDTOType) => {
    return await request2.put(API.SPU_BASE, param)
}

export const getSpuDatabyId = async (spuId: number) => {
    return await request2.get<any, ResponseData<SpuDTOType>>(
        API.SPU_BASE + `/${spuId}`,
    )
}

export const postSpuDataAdd = async (params: SpuDTOType) => {
    return await request2.post<any, ResponseData<any>>(API.SPU_BASE, params)
}

export const getSpuPageData = async (
    pageNumber: number,
    pageSize: number,
    category1Id: number,
    category2Id: number,
    name: string,
) => {
    return request2.get<any, ResponseData<PageData<SpuPageType>>>(
        API.SPU_PAGE +
            `?pageSize=${pageSize}&pageNumber=${pageNumber}&category1Id=${category1Id}&category2Id=${category2Id}&name=${name}`,
    )
}

export const getSpuList = async (category1Id: number, category2Id: number) => {
    return await request2.get<any, ResponseData<{ spuList: SpuList[] }>>(
        API.SPU_BASE + `?category1Id=${category1Id}&category2Id=${category2Id}`,
    )
}
