// 分类的接口

interface Response {
    code: number
    message: string
    ok: boolean
}

export interface C1 {
    id?: number | null
    name?: string | null
    createTime?: string | null
    updateTime?: string | null
}

export interface C2 {
    id?: number | null
    name?: string | null
    category1Id?: number | null
    createTime?: string | null
    updateTime?: string | null
}
export interface C3 {
    id?: number | null
    name?: string | null
    category2Id?: number | null
    createTime?: string | null
    updateTime?: string | null
}

export interface Attribute {
    id: 234123
    createTime: null
    updateTime: null
    valueName: '111'
    attrId: 9171
}

export interface AttrInfo {
    id: number | null
    attrName: string | null
    categoryId: number | null
    categoryLevel: number | null
    createTime?: string | null
    updateTime?: string | null
    attrValueList: Attribute[]
}

export interface AttrInfoResponse extends Response {
    data: AttrInfo[]
}

export interface C1Response extends Response {
    data: C1[]
}

export interface C2Response extends Response {
    data: C2[]
}
export interface C3Response extends Response {
    data: C3[]
}
// 属性值对象
export interface AttrValue {
    id?: number
    valueName?: string
    attrId?: number
    flag: boolean
}

export interface Attr {
    id?: number
    attrName: string
    categoryId: number | string
    attrValueList: AttrValue[]
    categoryLevel: number
}
