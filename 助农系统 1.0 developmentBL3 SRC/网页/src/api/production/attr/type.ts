export type AttrPageDataType = {
    /**
     * 属性名
     */
    name: string
    /**
     * 属性归属的spu
     */
    spuId: number
    /**
     * 属性价值数组
     */
    values: Value[]
}

export type Value = {
    /**
     * 属性的id
     */
    id?: number
    /**
     * 属性值
     */
    value: string

    flag?: boolean
}

export type AttrAddType = {
    /**
     * 属性名
     */
    name: string
    /**
     * spuId
     */
    spuId: number
    /**
     * 属性值数组
     */
    values: string[]
}
