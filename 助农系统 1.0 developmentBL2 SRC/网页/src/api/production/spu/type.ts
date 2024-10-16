export type SpuPageType = {
    /**
     * spu描述
     */
    description: string
    /**
     * spu名称
     */
    name: string
    /**
     * spuId
     */
    spuId: number
}

/**
 * SPUDTO
 */
export type SpuDTOType = {
    /**
     * spuId
     */
    id?: number

    /**
     * 描述，原价
     */
    description?: string
    /**
     * 折扣（0-1），折扣（比例）
     */
    discount?: number
    /**
     * 原价
     */
    oldPrice: string
    /**
     * 图片数组，图片地址数组
     */
    picture: string[]
    /**
     * 具体价格
     */
    price: number
    /**
     * 商品规格
     */
    specifications?: SpuSpecificationType[]
    /**
     * spu名称，spu名称
     */
    spuName: string
    /**
     * 库存，库存
     */
    storeCount: number

    /**
     * 分类2ID
     */
    category2Id: number

    attributes?: SpuAttributeType[]
}

export type SpuAttributeType = {
    id?: number
    name?: string
    spuId?: number
    value?: string
}

/**
 * Specification
 */
export type SpuSpecificationType = {
    /**
     * 主键
     */
    id?: number
    /**
     * 规格名称
     */
    name: string
    /**
     * spuId
     */
    spuId: number
    /**
     * 参数值
     */
    value: string
}
export type SpuList = {
    /**
     * spu的ID
     */
    spuId: number
    /**
     * spu名字
     */
    spuName: string
}

/**
 * SPUVO
 */
export type SPUInfoDetailType = {
    attributes: Attribute[]
    description: number
    discount: number
    /**
     * ID 编号
     */
    id: number
    picture: string[]
    price: number
    specifications: Specification[]
    spuName: string
    storeCount: number
}

export type Attribute = {
    name?: string
    values?: string[]
}

export type Specification = {
    id?: number
    name?: string
    spuId?: number
    value?: string
}
