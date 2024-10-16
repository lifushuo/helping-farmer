// 对外暴露配置路由

// 常量路由
export const constantRoute = [
    {
        // 登录路由
        path: '/login',
        component: () => import('@/views/login/index.vue'),
        name: 'login', //命名路由，
        meta: {
            title: '登录', //菜单标题
            hidden: true,
            icon: 'Promotion', //菜单左侧图标 支持elment-plus图标
        },
    },
    {
        // 登录成功后展示数据的首页路由
        path: '/',
        component: () => import('@/layout/index.vue'),
        name: 'layout',
        meta: {
            // title: 'Layout', //菜单标题
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/home',
        children: [
            {
                path: '/home',
                component: () => import('@/views/home/index.vue'),
                meta: {
                    title: '首页', //菜单标题
                    hidden: false,
                    icon: 'Promotion',
                },
            },
        ],
    },
    {
        // 数据大屏业务
        path: '/screen',
        component: () => import('@/views/screen/index.vue'),
        name: 'Screen',
        meta: {
            title: '数据大屏',
            hidden: false,
            icon: 'Promotion',
        },
    },
    {
        // 404 页面
        path: '/404',
        component: () => import('@/views/404/index.vue'),
        name: '404',
        meta: {
            title: '404', //菜单标题
            hidden: true,
            icon: 'Promotion',
        },
    },
]

// 异步路由
export const asyncRoute = [
    {
        // 操作面板
        path: '/operation',
        component: () => import('@/layout/index.vue'),
        name: 'operation',
        meta: {
            title: '操作面板',
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/operation/op',
        children: [
            {
                path: '/operation/op',
                component: () => import('@/views/operation/index.vue'),
                name: 'Operation',
                meta: {
                    title: '操作面板',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
        ],
    },
    {
        // 数据管理
        path: '/statistic',
        component: () => import('@/layout/index.vue'),
        name: 'statistic',
        meta: {
            title: '统计数据',
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/statistic/total',
        children: [
            {
                path: '/statistic/total',
                component: () =>
                    import('@/views/statistic/totaldata/index.vue'),
                name: 'statisticTotal',
                meta: {
                    title: '数据管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
        ],
    },
    // {
    //     // 权限管理
    //     path: '/acl',
    //     component: () => import('@/layout/index.vue'),
    //     name: 'Acl',
    //     meta: {
    //         title: '权限管理',
    //         hidden: true,
    //         icon: 'Promotion',
    //     },
    //     redirect: '/acl/user',
    //     children: [
    //         {
    //             path: '/acl/user',
    //             component: () => import('@/views/ad/user/index.vue'),
    //             name: 'User',
    //             meta: {
    //                 title: '用户管理',
    //                 hidden: true,
    //                 icon: 'Promotion',
    //             },
    //         },
    //         {
    //             path: '/acl/role',
    //             component: () => import('@/views/ad/roler/index.vue'),
    //             name: 'Role',
    //             meta: {
    //                 title: '角色管理',
    //                 hidden: true,
    //                 icon: 'Promotion',
    //             },
    //         },
    //         {
    //             path: '/acl/permissions',
    //             component: () => import('@/views/ad/permission/index.vue'),
    //             name: 'Permission',
    //             meta: {
    //                 title: '菜单管理',
    //                 hidden: true,
    //                 icon: 'Promotion',
    //             },
    //         },
    //     ],
    // },
    {
        // 员工管理模块
        path: '/orders',
        component: () => import('@/layout/index.vue'),
        name: 'Orders',
        meta: {
            title: '订单管理',
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/orders',
        children: [
            {
                path: '/orders',
                component: () => import('@/views/orders/index.vue'),
                name: 'OrdersList',
                meta: {
                    title: '订单列表',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
        ],
    },
    {
        // 商品管理模块
        path: '/product',
        component: () => import('@/layout/index.vue'),
        name: 'Product',
        meta: {
            title: '商品管理',
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/product/trademark',
        children: [
            {
                path: '/product/fcategory',
                component: () => import('@/views/product/FCategory/index.vue'),
                name: 'FCategory',
                meta: {
                    title: '一级分类管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/product/scategory',
                component: () => import('@/views/product/SCategory/index.vue'),
                name: 'SCategory',
                meta: {
                    title: '二级分类管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/product/trademark',
                component: () => import('@/views/product/trademark/index.vue'),
                name: 'Trademark',
                meta: {
                    title: '品牌管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/product/attr',
                component: () => import('@/views/product/attr/index.vue'),
                name: 'Attr',
                meta: {
                    title: '属性管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/product/spu',
                component: () => import('@/views/product/spu/index.vue'),
                name: 'Spu',
                meta: {
                    title: 'SPU管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/product/sku',
                component: () => import('@/views/product/sku/index.vue'),
                name: 'Sku',
                meta: {
                    title: 'SKU管理',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
        ],
    },
    {
        // 员工管理模块
        path: '/people',
        component: () => import('@/layout/index.vue'),
        name: 'People',
        meta: {
            title: '员工管理',
            hidden: false,
            icon: 'Promotion',
        },
        redirect: '/people/manage',
        children: [
            {
                path: '/people/manage',
                component: () => import('@/views/employee/index.vue'),
                name: 'PeopleList',
                meta: {
                    title: '员工列表',
                    hidden: false,
                    icon: 'Promotion',
                },
            },
            {
                path: '/people/manage/add',
                component: () => import('@/views/employee/addEmployee.vue'),
                name: 'PeopleAdd',
                meta: {
                    title: '添加员工',
                    hidden: true,
                    icon: 'Promotion',
                },
            },
        ],
    },
]

//任意路由
export const anyRoute = [
    {
        // 任意路由
        path: '/:pathMatch(.*)*',
        component: () => import('@/views/404/index.vue'),
        redirect: '/404',
        name: 'Any',
        meta: {
            title: '任意', //菜单标题
            hidden: true,
        },
        children: [],
    },
]
