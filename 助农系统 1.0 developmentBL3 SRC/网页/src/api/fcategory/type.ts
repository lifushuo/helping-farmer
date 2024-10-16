export type FCategoryPage = {
    createTime: string
    createUser: number
    /**
     * 图片地址
     */
    icon: string
    /**
     * 主键
     */
    id: number
    /**
     * 是否有效
     */
    isEffective: number
    /**
     * 一级分类名字
     */
    name: string
    updateTime: string
    updateUser: number
    [property: string]: any
}

export type FCategory = FCategoryPage & {}

export type FCategoryAddDTO = {
    icon: string
    id?: number
    /**
     * 分类名称
     */
    name: string
}

export type FCategoryList = {
    fcategorylist: { name: string; id: number }[]
}
