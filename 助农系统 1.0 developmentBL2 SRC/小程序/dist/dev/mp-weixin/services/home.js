"use strict";
const utils_http = require("../utils/http.js");
const getHomeBannerAPI = (distributionSite = 1) => {
  return utils_http.http({
    url: "/home/banner",
    method: "GET",
    data: {
      distributionSite
    }
  });
};
const getHomeCategoryAPI = () => {
  return utils_http.http({
    method: "GET",
    url: "/home/category/mutli"
  });
};
const getHomeHotAPI = () => {
  return utils_http.http({
    method: "GET",
    url: "/home/hot/mutli"
  });
};
exports.getHomeBannerAPI = getHomeBannerAPI;
exports.getHomeCategoryAPI = getHomeCategoryAPI;
exports.getHomeHotAPI = getHomeHotAPI;
//# sourceMappingURL=home.js.map
