package com.wondercat.testapp;

import com.wondercat.testapp.configuration.AppConfiguration;
import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.service.WarehouseEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@WebAppConfiguration
class WarehouseEmployeeServiceTest {

    private String WAREHOUSEEMPLOYEE_NAME = "NAME";
    private static int COUNTER;
    private Optional<WarehouseEmployee> optionalWarehouseEmployeeTest;
    private final String FULL_QUALIFIED_NAME = "com.wondercat.testapp." +
            "service.WarehouseEmployeeService";

    @Autowired
    private WarehouseEmployeeService warehouseEmployeeService;

    WarehouseEmployeeService getWarehouseEmployeeService() {
        return warehouseEmployeeService;
    }

    @Test
    void checkActualClassIsWired_Test(){

        assertThat(getWarehouseEmployeeService().getClass().getName()).
                isEqualTo(FULL_QUALIFIED_NAME);
    }

    @Test
    void addWarehouseEmployee_Test(){

        getWarehouseEmployeeService().add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME));

        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    void deleteAll_Test(){

        getWarehouseEmployeeService().add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME));
        assertThat(getWarehouseEmployeeService().listAll()).isNotEmpty();
        getWarehouseEmployeeService().deleteAll();
        assertThat(getWarehouseEmployeeService().listAll()).isEmpty();
    }

    @BeforeEach
    void incrementCounterForWarehouseEmployeeName(){
        WAREHOUSEEMPLOYEE_NAME = WAREHOUSEEMPLOYEE_NAME.concat(String.valueOf(
                COUNTER++));
    }

    @Test
    void addAll_Test(){

        getWarehouseEmployeeService().addAll(Arrays.asList(
                new WarehouseEmployee(WAREHOUSEEMPLOYEE_NAME),
                new WarehouseEmployee(WAREHOUSEEMPLOYEE_NAME +
                        WAREHOUSEEMPLOYEE_NAME)));

        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);
        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME +
                WAREHOUSEEMPLOYEE_NAME)).isEqualTo(WAREHOUSEEMPLOYEE_NAME +
                WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    void getWarehouseEmployeeById_Test(){

        long idBeforeAdd;

        Supplier<Long> lambdaWarehouseEmployeeId = () -> {
            Optional<WarehouseEmployee> lastWarehouseEmployee =
                    getWarehouseEmployeeService().getLast();
            return lastWarehouseEmployee.map(WarehouseEmployee::getId).orElse(0L);
        };

        idBeforeAdd = lambdaWarehouseEmployeeId.get();
        getWarehouseEmployeeService().add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME));
        assertThat(idBeforeAdd).isLessThan(lambdaWarehouseEmployeeId.get());

    }

    @Test
    void getWarehouseEmployeeByName_Test(){

        getWarehouseEmployeeService().add(
                new WarehouseEmployee(WAREHOUSEEMPLOYEE_NAME));
        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    void deleteWarehouseEmployee_Test(){
        getWarehouseEmployeeService().add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME));
        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);

    }

    @Test
    void updateWarehouseEmployeeName_Test(){

        getWarehouseEmployeeService().add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME));

        getWarehouseEmployeeService().updateWarehouseEmployeeName(
                getWarehouseEmployeeService().getWarehouseEmployeeByName(
                        WAREHOUSEEMPLOYEE_NAME).get().getId(),
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME);

        assertThat(getWarehouseEmployeeService().getWarehouseEmployeeByName(
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME).get().
                getName()).isEqualTo(
                        WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME);
    }

    /**
     * This method return instance of
     * <code>{{@link Optional<WarehouseEmployee>}}</code>
     * with called method getName()
     *
     * @param name required warehouse employee's name
     * @return warehouse employee's name or {@link String}"NONE" if required
     * object isn't founded
     */
    private String getOptionalWarehouseEmployeeName(String name){
        System.out.println();
        optionalWarehouseEmployeeTest = getWarehouseEmployeeService().
                getWarehouseEmployeeByName(name);

        return optionalWarehouseEmployeeTest.isPresent()
                ? optionalWarehouseEmployeeTest.get().getName()
                : "NONE";
    }

}
