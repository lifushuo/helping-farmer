import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'
import { AttrAddType, AttrPageDataType } from '@/api/production/attr/type.ts'

enum API {
    ATTR_BASE = '/admin/attr',
    ATTR_PAGE = '/admin/attr/page',
    ATTR_VALUE = '/admin/attr/value',
}

export const getAttributePageData = async (
    category1Id?: number,
    category2Id?: number,
    spuId?: number,
) => {
    return request2.get<any, ResponseData<PageData<AttrPageDataType>>>(
        API.ATTR_PAGE +
            `?category1Id=${category1Id}&category2Id=${category2Id}&spuId=${spuId}`,
    )
}

/**
 *  添加一个spu属性
 * @param param
 */
export const postAttributeEdit = async (param: AttrAddType) => {
    return await request2.post<any, ResponseData<any>>(API.ATTR_BASE, param)
}

/**
 * 删除一个 属性
 * @param spuId
 * @param name
 */
export const delAttributeEdit = async (spuId: number, name: string) => {
    return await request2.delete<any, ResponseData<any>>(
        API.ATTR_BASE + `?spuId=${spuId}&name=${name}`,
    )
}

/**
 * 根据 spuid 和 name获取 具体的数据值
 * @param spuId
 * @param name
 */
export const getAttrDetailsById = async (spuId: number, name: string) => {
    return await request2.get<any, ResponseData<AttrPageDataType>>(
        API.ATTR_BASE + `?spuId=${spuId}&name=${name}`,
    )
}

export const putAttrName = async (
    spuId: number,
    oldName: string,
    newName: string,
) => {
    return await request2.put<any, ResponseData<any>>(
        API.ATTR_BASE + `?spuId=${spuId}&oldName=${oldName}&newName=${newName}`,
    )
}

export const postAttrValueAdd = async (
    spuId: number,
    name: string,
    value: string,
) => {
    return await request2.post<any, ResponseData<any>>(
        API.ATTR_VALUE + `?spuId=${spuId}&name=${name}&value=${value}`,
    )
}
export const putAttrValueEdit = async (id: number, value: string) => {
    return await request2.put<any, ResponseData<any>>(
        API.ATTR_VALUE + `?id=${id}&value=${value}`,
    )
}
export const delAttrValueDelete = async (id: number) => {
    return await request2.delete<any, ResponseData<any>>(
        API.ATTR_VALUE + `?id=${id}`,
    )
}
