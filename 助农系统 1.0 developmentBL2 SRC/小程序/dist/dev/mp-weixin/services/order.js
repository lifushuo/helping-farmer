"use strict";
const utils_http = require("../utils/http.js");
const getMemberOrderPreAPI = () => {
  return utils_http.http2({
    method: "GET",
    url: "/user/order/pre"
  });
};
const getMemberOrderPreNowAPI = (data) => {
  return utils_http.http({
    method: "GET",
    url: "/member/order/pre/now",
    data
  });
};
const postMemberOrderAPI = (data) => {
  return utils_http.http2({
    method: "POST",
    url: "/user/order/submit",
    data
  });
};
const getMemberOrderByIdAPI = (id) => {
  return utils_http.http2({
    method: "GET",
    url: `/user/order/orderDetail/${id}`
  });
};
const getPayMockAPI = (id) => {
  return utils_http.http2({
    method: "PUT",
    url: `/user/order/pay/${id}`
  });
};
const getMemberOrderConsignmentByIdAPI = (id) => {
  return utils_http.http2({
    method: "PUT",
    url: `/user/order/fahuo/${id}`
  });
};
const putMemberOrderReceiptByIdAPI = (id) => {
  return utils_http.http2({
    method: "PUT",
    url: `/user/order/shouhuo/${id}`
  });
};
const deleteMemberOrderAPI = (data) => {
  return utils_http.http({
    method: "DELETE",
    url: `/member/order`,
    data
  });
};
const getMemberOrderAPI = (data) => {
  return utils_http.http2({
    method: "GET",
    url: `/user/order/historyOrders`,
    data
  });
};
exports.deleteMemberOrderAPI = deleteMemberOrderAPI;
exports.getMemberOrderAPI = getMemberOrderAPI;
exports.getMemberOrderByIdAPI = getMemberOrderByIdAPI;
exports.getMemberOrderConsignmentByIdAPI = getMemberOrderConsignmentByIdAPI;
exports.getMemberOrderPreAPI = getMemberOrderPreAPI;
exports.getMemberOrderPreNowAPI = getMemberOrderPreNowAPI;
exports.getPayMockAPI = getPayMockAPI;
exports.postMemberOrderAPI = postMemberOrderAPI;
exports.putMemberOrderReceiptByIdAPI = putMemberOrderReceiptByIdAPI;
//# sourceMappingURL=order.js.map
