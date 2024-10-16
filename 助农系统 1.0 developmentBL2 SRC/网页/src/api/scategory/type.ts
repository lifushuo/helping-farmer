export type SCategoryPageType = {
    createTime: string
    createUser: number
    /**
     * 二级分类id
     */
    id: number
    /**
     * 0无效 1有效
     */
    isEffective: number
    /**
     * 二级分类名称
     */
    name: string
    /**
     * 一级分类id
     */
    parentId: number
    /**
     * 一级分类名字
     */
    parentName: string
    updateTime: string
    updateUser: number
}
