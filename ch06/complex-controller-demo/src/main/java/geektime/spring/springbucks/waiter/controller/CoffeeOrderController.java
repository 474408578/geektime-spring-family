package geektime.spring.springbucks.waiter.controller;

import geektime.spring.springbucks.waiter.controller.request.NewOrderRequest;
import geektime.spring.springbucks.waiter.model.Coffee;
import geektime.spring.springbucks.waiter.model.CoffeeOrder;
import geektime.spring.springbucks.waiter.service.CoffeeOrderService;
import geektime.spring.springbucks.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author xschen
 */

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable Long id) {
        return orderService.get(id);
    }

    /**
     * consumes: Content-Type:application/json
     * produces: Accept: application/json;charset=UTF-8
     * @param newOrder
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffees = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.creatrOrder(newOrder.getCustomer(), coffees);
    }
}
