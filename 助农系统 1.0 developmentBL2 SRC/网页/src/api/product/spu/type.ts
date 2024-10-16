// SPU接口的类型

export interface Response {
    code: number | null
    message: string | null
    ok: boolean | null
}

export interface SPUInfo {
    spuId?: number | string | null
    tmId?: number | string
    category3Id: number | string | null
    spuName: string | null
    description: string | null
    spuSaleAttrList: null | SPUSaleAttrValue[]
    spuImageList: null | SPUImage[]
    updateTime?: Date | null | string
    createTime?: Date | null | string
}

// SPU 照片数据
export interface SPUImage {
    id?: number | null
    imgName?: string | null
    imgUrl?: string | null
    spuId: number | null
    createTime?: string | Date
    updateTime?: string | Date
    name?: string | null
    url?: string | null
}

// SPU 属性 价值

export interface SPUSaleAttrValue {
    id?: number | null
    spuId: number | null
    baseSaleAttrId: number | null
    saleAttrName: string | null
    saleAttrValueName: string | null
    isChecked?: string | boolean | null
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface SPUSaleAttr {
    id?: number | null
    name: string | null
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface SPUPageResponse extends Response {
    data: {
        total: number
        size: number
        current: number
        pages: number
        optimizeCountSql: boolean
        countId: number | null
        maxLimit: number | null
        hitCount: boolean | null
        searchCount: boolean | null
        orders: [] | null
        records: SPUInfo[]
    }
}

export interface SPUIMagesResponse extends Response {
    data: SPUImage[]
}

export interface SKUInfo {
    id?: number | string | null
    spuId: number | string | null
    tmId: number | string | null
    category3Id: number | string | null
    price: number | string | null
    skuName: string | null
    skuDesc: string | null
    skuDefaultImg: string | null
    weight: number | string | null
    isSale: number | string | null
    skuImageList: null | []
    skuAttrValueList: null | []
    skuSaleAttrValueList: null | []
    updateTime: string | Date | null
    createTime: string | Date | null
}

export interface SKUInfoResponse extends Response {
    data: SKUInfo[]
}
export interface SPUSaleValueList {
    id?: number | null
    spuId?: number | null
    baseSaleAttrId: number
    saleAttrName: string | null
    spuSaleAttrValueList: SPUSaleAttrValue[] | null
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface SPUData {
    id?: number | string | null
    tmId?: number | string
    category3Id: string | number | null
    spuName: string | null
    description: string | null
    updateTime?: string | Date | null
    createTime?: string | Date | null
    spuImageList: SPUImage[] | null
    spuSaleAttrList: SPUSaleValueList[] | null
}

interface SKUAttrValue {
    id?: number
    skuId: number
    attrId: number
    valueId: number
    attrName: string
    valueName: string
    createTime: string | Date | null
    updateTime: string | Date | null
}

interface SKUImage {
    id?: number | string
    skuId: number | string
    spuImgId: number | string
    imgUrl: string
    imgName: string
    isDefault: string
    createTime: string | Date | null
    updateTime: string | Date | null
}

interface SKUSaleAttrValue {
    id?: number | string
    skuId: number | string
    spuId: number | string
    saleAttrId: number | string
    saleAttrValueId: number | string
    saleAttrName: string
    saleAttrValueName: string
    createTime: string | Date | null
    updateTime: string | Date | null
}

export interface SKUData {
    id?: number | string //spuID
    spuId?: number | string //已有的spuID
    category3Id?: number | string //三级分类ID
    tmId?: number | string //品牌的ID
    // v-model 收集到的属性
    price?: number | string //价格
    isSale?: number | boolean // 是否出售
    skuName?: string | null //sku的名字
    skuDesc?: string | null // sku描述
    weight?: string | null //重量
    skuDefaultImg?: string //sku的默认照片
    updateTime?: string | Date | null
    createTime?: string | Date | null
    skuAttrValueList?: SKUAttrValue[] //平台属性的收集
    skuImageList?: SKUImage[] //sku图片的地址
    skuSaleAttrValueList?: SKUSaleAttrValue[] //销售属性的ID
}

export interface SPUSaleValueListResponse extends Response {
    data: SPUSaleValueList[]
}

export interface SPUSaleAttrResponse extends Response {
    data: SPUSaleAttr[]
}
