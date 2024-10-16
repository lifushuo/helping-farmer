"use strict";
const utils_http = require("../utils/http.js");
const postMemberCartAPI = (data) => {
  return utils_http.http2({
    method: "POST",
    url: "/user/shoppingCart/add",
    data
  });
};
const getMemberCartAPI = () => {
  return utils_http.http2({
    method: "GET",
    url: "/user/shoppingCart/list"
  });
};
const deleteMemberCartAPI = (data) => {
  return utils_http.http2({
    method: "DELETE",
    url: "/user/shoppingCart/clean",
    data
  });
};
const putMemberCartBySkuIdAPI = (skuId, data) => {
  return utils_http.http2({
    method: "PUT",
    url: `/user/shoppingCart/${skuId}`,
    data
  });
};
const putMemberCartSelectedAPI = (data) => {
  return utils_http.http2({
    method: "PUT",
    url: "/user/shoppingCart/selected",
    data
  });
};
exports.deleteMemberCartAPI = deleteMemberCartAPI;
exports.getMemberCartAPI = getMemberCartAPI;
exports.postMemberCartAPI = postMemberCartAPI;
exports.putMemberCartBySkuIdAPI = putMemberCartBySkuIdAPI;
exports.putMemberCartSelectedAPI = putMemberCartSelectedAPI;
//# sourceMappingURL=cart.js.map
