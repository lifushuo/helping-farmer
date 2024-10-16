"use strict";
const common_vendor = require("../../common/vendor.js");
const services_home = require("../../services/home.js");
const services_category = require("../../services/category.js");
require("../../utils/http.js");
require("../../stores/index.js");
require("../../stores/modules/member.js");
if (!Array) {
  const _easycom_uni_search_bar2 = common_vendor.resolveComponent("uni-search-bar");
  _easycom_uni_search_bar2();
}
const _easycom_uni_search_bar = () => "../../node-modules/@dcloudio/uni-ui/lib/uni-search-bar/uni-search-bar.js";
if (!Math) {
  (PageSkeleton + _easycom_uni_search_bar)();
}
const PageSkeleton = () => "./component/PageSkeleton.js";
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "category",
  setup(__props) {
    const bannerList = common_vendor.ref([]);
    const getBannerData = async () => {
      const res = await services_home.getHomeBannerAPI(2);
      bannerList.value = res.result;
      console.log(res.result);
      console.log(bannerList.value);
    };
    const activeIndex = common_vendor.ref(0);
    const categoryList = common_vendor.ref([]);
    const getCategoryTopData = async () => {
      const res = await services_category.getCategoryTopAPI();
      categoryList.value = res.data;
      console.log("getCategoryTopData");
      console.log(categoryList.value);
    };
    const subCategoryList = common_vendor.computed(() => {
      var _a;
      return ((_a = categoryList.value[activeIndex.value]) == null ? void 0 : _a.children) || [];
    });
    const isTrigger = common_vendor.ref(false);
    common_vendor.onLoad(async () => {
      isTrigger.value = true;
      await Promise.all([getBannerData(), getCategoryTopData()]);
      isTrigger.value = false;
    });
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: isTrigger.value
      }, isTrigger.value ? {} : {
        b: common_vendor.p({
          placeholder: "大米"
        }),
        c: common_vendor.f(categoryList.value, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: index,
            c: index === activeIndex.value ? 1 : "",
            d: common_vendor.o(() => activeIndex.value = index, index)
          };
        }),
        d: common_vendor.f(common_vendor.unref(subCategoryList), (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: common_vendor.f(item.goods, (goods, k1, i1) => {
              return {
                a: goods.picture,
                b: common_vendor.t(goods.name),
                c: common_vendor.t(goods.price),
                d: goods.id,
                e: `/pages/goods/goods?id=${goods.id}`
              };
            }),
            c: index
          };
        })
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/category/category.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=category.js.map
