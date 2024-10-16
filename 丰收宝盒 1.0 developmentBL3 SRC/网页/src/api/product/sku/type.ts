export interface Response {
    code: number
    message: string
    ok: boolean
}

export interface SkuImage {
    id?: number | string
    skuId?: number | string
    spuImgId?: number | string
    imgName?: string
    imgUrl?: string
    isDefault?: string
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface SkuAttrValue {
    id?: number | string
    createTime?: string | Date | null
    updateTime?: string | Date | null
    attrId?: number | string
    valueId?: number | string
    skuId?: number | string
    attrName?: string
    valueName?: string
}

export interface SkuSaleAttrValue {
    id?: number | string
    spuId?: number | string
    skuId?: number | string
    saleAttrId?: number | string
    saleAttrName?: string
    saleAttrValueId?: number | string
    saleAttrValueName?: string
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface Sku {
    id?: number
    tmId?: number | string
    spuId?: number | string
    category3Id?: number | string
    isSale?: number | boolean
    skuName?: string
    skuDesc?: string
    weight?: string | number
    price?: number | string
    skuDefaultImg?: string | number
    skuImageList?: SkuImage[]
    skuAttrValueList?: SkuAttrValue[]
    skuSaleAttrValueList?: SkuSaleAttrValue[]
    createTime?: string | Date | null
    updateTime?: string | Date | null
}

export interface SkuPage {
    total?: number
    size?: number
    current?: number
    pages?: number
    records?: Sku[]
    orders?: [] | null
    optimizeCountSql?: boolean
    hitCount?: boolean
    countId?: null | number
    maxLimit?: null | number
    searchCount?: boolean
}

export interface SkuPageResponse extends Response {
    data: SkuPage
}

export interface SkuInfoResponse extends Response {
    data: Sku
}
