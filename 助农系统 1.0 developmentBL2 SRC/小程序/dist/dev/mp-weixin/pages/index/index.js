"use strict";
const common_vendor = require("../../common/vendor.js");
const services_home = require("../../services/home.js");
const composables_index = require("../../composables/index.js");
require("../../utils/http.js");
require("../../stores/index.js");
require("../../stores/modules/member.js");
if (!Math) {
  (CustomNavbar + PageSkeleton + XtxSwiper + Hot)();
}
const CustomNavbar = () => "./components/CustomNavbar.js";
const XtxSwiper = () => "../../components/XtxSwiper.js";
const PageSkeleton = () => "./components/PageSkeleton.js";
const Hot = () => "../hot/hot2.js";
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "index",
  setup(__props) {
    const bannerList = common_vendor.ref([]);
    const categoryList = common_vendor.ref([]);
    const getHomeBannerData = async () => {
      bannerList.value = [
        {
          imgUrl: "https://www.2008php.com/2020_Website_appreciate/2020-02-29/20200229221059.jpg",
          id: "",
          hrefUrl: "",
          type: 0
        },
        {
          imgUrl: "https://photo.16pic.com/00/61/17/16pic_6117697_b.jpg",
          id: "",
          hrefUrl: "",
          type: 0
        },
        {
          imgUrl: "https://img.zhisheji.com/fxapi/fximg/4c8e194bbd580c4470dd78ccba5717b2.jpg",
          id: "",
          hrefUrl: "",
          type: 0
        },
        {
          imgUrl: "https://img.zcool.cn/community/0170f2560e37fb6ac7251df8efa697.jpg",
          id: "",
          hrefUrl: "",
          type: 0
        }
      ];
    };
    const getHomeCategory = async () => {
      let result = await services_home.getHomeCategoryAPI();
      categoryList.value = result.result;
    };
    let hotPanelList = common_vendor.ref([]);
    const getHomeHotPanel = async () => {
      let result = await services_home.getHomeHotAPI();
      hotPanelList.value = result.result;
    };
    const isLoading = common_vendor.ref(true);
    common_vendor.onLoad(async () => {
      isLoading.value = true;
      await Promise.all([getHomeBannerData(), getHomeCategory(), getHomeHotPanel()]);
      isLoading.value = false;
    });
    const { guessRef, onScrolltolower } = composables_index.useGuessList();
    let isTriggered = common_vendor.ref(false);
    const onRefresherrefresh = async () => {
      var _a;
      isTriggered.value = true;
      (_a = guessRef.value) == null ? void 0 : _a.resetData();
      await Promise.all([getHomeBannerData(), getHomeCategory(), getHomeHotPanel()]);
      isTriggered.value = false;
      console.log(hotRef.value);
      hotRef.value.getNewData();
    };
    let hotRef = common_vendor.ref();
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: isLoading.value
      }, isLoading.value ? {} : {
        b: common_vendor.p({
          list: bannerList.value
        }),
        c: common_vendor.sr(hotRef, "7fa068b0-3", {
          "k": "hotRef"
        }),
        d: common_vendor.p({
          type: "1"
        })
      }, {
        e: common_vendor.unref(isTriggered),
        f: common_vendor.o(
          //@ts-ignore
          (...args) => common_vendor.unref(onScrolltolower) && common_vendor.unref(onScrolltolower)(...args)
        ),
        g: common_vendor.o(onRefresherrefresh)
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=index.js.map
