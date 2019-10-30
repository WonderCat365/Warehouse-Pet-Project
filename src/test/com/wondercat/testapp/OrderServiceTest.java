package com.wondercat.testapp;

import com.wondercat.testapp.configuration.AppConfiguration;
import com.wondercat.testapp.entity.Orders;
import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.service.OrderService;
import com.wondercat.testapp.service.WarehouseEmployeeService;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@WebAppConfiguration
@Getter
class OrderServiceTest {

    private SimpleDateFormat sdf;

    private OrderService orderService;
    private WarehouseEmployeeService warehouseEmployeeService;

    private final static Logger LOGGER = Logger.getLogger(OrderServiceTest.class);
    private Date DATE;
    private String NAME;

    private String dateInString = "31-08-";
    private static int YEAR = 2000;

    @Autowired
    OrderServiceTest(OrderService orderService) {
        this.orderService = orderService;
        sdf = new SimpleDateFormat("dd-M-yyyy");
    }

    @Autowired
    void setWarehouseEmployeeService(WarehouseEmployeeService warehouseEmployeeService) {
        this.warehouseEmployeeService = warehouseEmployeeService;
    }

    @BeforeEach
    void dateAlteration() throws ParseException {
        dateInString = dateInString.concat(String.valueOf(YEAR++));
        DATE = sdf.parse(dateInString);
        NAME = "NAME_" + (int) (Math.random() * 100);

    }

    @Test
    void getService_Test(){
        assertThat(getOrderService() != null).isTrue();
    }

    @Test
    void addOrder_Test(){
        getWarehouseEmployeeService().add(WarehouseEmployee.builder().name(NAME).build());

        getOrderService().add(Orders.builder().
                dateCreation(DATE).
                dateExecution(DATE).
                warehouseEmployee(getWarehouseEmployeeService().
                        getWarehouseEmployeeByName(NAME).get()).
                build());
        Optional<Orders> orders = getOrderService().getOrderByWarehouseEmployeeName(NAME);
        LOGGER.debug(orders);
        assertThat(orders.get().getWarehouseEmployee().getName()).isEqualTo(NAME);
    }

    @Test
    void listAllOrders_Test(){

        getWarehouseEmployeeService().add(WarehouseEmployee.builder().
                name(NAME).
                build());
        getOrderService().add(Orders.builder().
                dateCreation(DATE).
                dateExecution(DATE).
                warehouseEmployee(getWarehouseEmployeeService().
                        getWarehouseEmployeeByName(NAME).get()).
                build());

        getOrderService().listAll().forEach(LOGGER::info);

        assertThat(getOrderService().listAll()).isNotEmpty();
    }
}
