package com.sky.controller.user;

import com.sky.dto.OrderSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderSummaryService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderWriteVO;
import com.sky.vo.UserOrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/order")
public class UserOrderController {
    @Resource
    OrderSummaryService userOrderService;

    @PutMapping("/pay/{id}")
    public Result setOrderPayed(@PathVariable("id") Integer id) {
        Result result = userOrderService.setOrderPayed(id);
        return result;
    }

    @PutMapping("/fahuo/{id}")
    public Result setOrderFaHuo(@PathVariable("id") Integer id) {
        Result result = userOrderService.setOrderFahuo(id);
        return result;
    }

    @PutMapping("/shouhuo/{id}")
    public Result setOrderShouHuo(@PathVariable("id") Integer id) {
        Result result = userOrderService.setOrderShouhuo(id);
        return result;
    }

    @PutMapping("/cancel/{id}")
    public Result setOrderCancel(@PathVariable("id") Integer id) {
        Result result = userOrderService.setOrderCancel(id);
        return result;
    }

    @PutMapping("/delete/{id}")
    public Result setOrderDeleted(@PathVariable("id") Integer id) {
        Result result = userOrderService.setOrderDeleted(id);
        return result;
    }

    @GetMapping("orderDetail/{id}")
    public Result getOrderDetail(@PathVariable("id") Integer id) {
        UserOrderDetailVO u = userOrderService.getOrderDetail(id);
        return Result.success(u);

    }

    @GetMapping("/historyOrders")
    public Result getOrderHistory(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam("orderState") Integer status) {


        PageResult pageResult = userOrderService.getOrderHistory(page, pageSize, status);
        return Result.success(pageResult);
    }


    @PostMapping("/submit")
    public Result orderSubmit(@RequestBody OrderSubmitDTO orderSubmitDTO) {
        // 提交订单，获取订单ID
        Integer orderId = userOrderService.submitOrder(orderSubmitDTO);

        // 构建返回结果
        Map<String, Integer> data = new HashMap<>();
        data.put("id", orderId);

        return Result.success(data);

    }

    @GetMapping("/pre")
    public Result writeOrder(){
        OrderWriteVO orderWriteVO = userOrderService.writeOrder();

        return Result.success(orderWriteVO);
    }



}
