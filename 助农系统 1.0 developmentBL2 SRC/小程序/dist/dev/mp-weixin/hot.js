"use strict";
const common_vendor = require("./common/vendor.js");
const services_hot = require("./services/hot.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "hot",
  props: {
    type: null
  },
  setup(__props, { expose }) {
    common_vendor.index.setNavigationBarTitle({ title: "商品" });
    common_vendor.ref("");
    common_vendor.ref([]);
    common_vendor.ref(0);
    const getHotRecommandData = async () => {
      let res = await services_hot.getHotPage({
        //技巧： 环境变量，开发环境 修改初始化页面方便测试分页结束
        page: 1,
        pageSize: 10
      });
      console.log(res);
      hotPageData.value = res.data;
      hotData.value = res.data.items;
    };
    const getNewData = async () => {
      if (hotPageData.value.page * hotPageData.value.pageSize < hotPageData.value.counts)
        ;
      else {
        isfinish.value = true;
        common_vendor.index.showToast({
          title: "没有更多数据了",
          icon: "none"
        });
        return;
      }
      hotPageData.value.page += 1;
      console.log(hotPageData.value.page);
      let res = await services_hot.getHotPage({
        //技巧： 环境变量，开发环境 修改初始化页面方便测试分页结束
        page: hotPageData.value.page,
        pageSize: hotPageData.value.pageSize
      });
      console.log(res);
      hotPageData.value = res.data;
      hotData.value = res.data.items;
    };
    expose({
      getNewData
    });
    const onScrolltolower = async () => {
      var _a;
      console.log(hotPageData.value.page);
      console.log(hotPageData.value.pageSize);
      console.log(hotPageData.value.counts);
      if (hotPageData.value.page * hotPageData.value.pageSize >= hotPageData.value.counts) {
        isfinish.value = true;
        common_vendor.index.showToast({
          title: "没有更多数据了",
          icon: "none"
        });
        return;
      }
      hotPageData.value.page += 1;
      console.log(hotPageData.value.page);
      let res = await services_hot.getHotPage({
        //技巧： 环境变量，开发环境 修改初始化页面方便测试分页结束
        page: hotPageData.value.page,
        pageSize: hotPageData.value.pageSize
      });
      console.log(res);
      hotPageData.value = res.data;
      (_a = hotData.value) == null ? void 0 : _a.push(...res.data.items);
    };
    let hotPageData = common_vendor.ref();
    let hotData = common_vendor.ref();
    let isfinish = common_vendor.ref(false);
    common_vendor.onMounted(() => {
      getHotRecommandData();
    });
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(common_vendor.unref(hotData), (goods, k0, i0) => {
          return {
            a: goods.picture,
            b: common_vendor.t(goods.name),
            c: common_vendor.t(goods.price),
            d: goods.id,
            e: `/pages/goods/goods?id=${goods.id}`
          };
        }),
        b: common_vendor.t(common_vendor.unref(isfinish) == true ? "正在加载..." : "没有更多数据了"),
        c: common_vendor.o(onScrolltolower)
      };
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/hot/hot.vue"]]);
exports.MiniProgramPage = MiniProgramPage;
//# sourceMappingURL=hot.js.map
