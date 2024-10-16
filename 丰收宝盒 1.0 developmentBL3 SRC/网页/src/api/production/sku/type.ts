export type SkuInfo = {
    id: number
    skuName: string
    oldPrice?: number
    picture?: string
    price?: number
    storeCount?: number
    isEffective: number
    /**
     * 属性价值的数组，返回文字就可以了
     */
    values?: string[]
}

/**
 * SKUDTO
 */
export type SkuAddType = {
    attributes: number[]
    /**
     * 原价
     */
    oldPrice: string
    /**
     * 图片
     */
    picture: string
    /**
     * sku价格
     */
    price: string
    /**
     * 自己定义的sku名字
     */
    skuName: string
    /**
     * spuId
     */
    spuId: number
    /**
     * 库存
     */
    storeCount: number
}

export type SkuEditType = SkuAddType & { id: number }

export type SkuInfoDetail = {
    /**
     * skuid
     */
    id: number
    /**
     * sku名字
     */
    skuName: string
    /**
     * sku图片
     */
    skuPicture: string
    /**
     * sku原价
     */
    skuOldPrice: number
    /**
     * sku价格
     */
    skuPrice: number
    /**
     * sku库存
     */
    skuStoreCount: number
    /**
     * sku属性
     */
    skuValues: SkuValue[]
    /**
     * spu描述
     */
    spuDescription: string
    /**
     * spu折扣
     */
    spuDiscount: number
    /**
     * spuId
     */
    spuId: number
    /**
     * spu名字
     */
    spuName: string
    /**
     * spu规格
     */
    spuSpecification: SpuSpecification[]
    [property: string]: any
}

type SkuValue = {
    /**
     * 属性名
     */
    name: string
    /**
     * 属性价值
     */
    value: string
}

type SpuSpecification = {
    name: string
    value: string
}
