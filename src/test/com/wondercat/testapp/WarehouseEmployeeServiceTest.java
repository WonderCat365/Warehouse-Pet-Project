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
import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@WebAppConfiguration
class WarehouseEmployeeServiceTest {

    private String WAREHOUSEEMPLOYEE_NAME = "NAME";
    private static int COUNTER;

    private WarehouseEmployeeService warehouseEmployeeService;

    @Autowired
    WarehouseEmployeeServiceTest(WarehouseEmployeeService warehouseEmployeeService) {
        this.warehouseEmployeeService = warehouseEmployeeService;
    }

    WarehouseEmployeeService getWarehouseEmployeeService() {
        return warehouseEmployeeService;
    }

    @Test
    void checkActualClassIsWired_Test(){

        assertThat(getWarehouseEmployeeService() != null).isTrue();
    }

    @Test
    void addWarehouseEmployee_Test(){

        getWarehouseEmployeeService().add(WarehouseEmployee.builder().
                name(WAREHOUSEEMPLOYEE_NAME).
                build());

        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    void deleteAll_Test(){

        getWarehouseEmployeeService().add(WarehouseEmployee.builder().
                name(WAREHOUSEEMPLOYEE_NAME).
                build());

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
                WarehouseEmployee.builder().
                        name(WAREHOUSEEMPLOYEE_NAME).build(),
                WarehouseEmployee.builder().
                        name(WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME).
                        build()));

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
        getWarehouseEmployeeService().add(WarehouseEmployee.builder().name(WAREHOUSEEMPLOYEE_NAME).build());
        assertThat(idBeforeAdd).isLessThan(lambdaWarehouseEmployeeId.get());

    }

    @Test
    void getWarehouseEmployeeByName_Test(){

        getWarehouseEmployeeService().add(
                WarehouseEmployee.builder().name(WAREHOUSEEMPLOYEE_NAME).build());
        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    void deleteWarehouseEmployee_Test(){
        getWarehouseEmployeeService().add(WarehouseEmployee.builder().name(WAREHOUSEEMPLOYEE_NAME).build());
        assertThat(getOptionalWarehouseEmployeeName(WAREHOUSEEMPLOYEE_NAME)).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME);

    }

    @Test
    void uniqueName_Test(){
        WarehouseEmployee warehouseEmployee = WarehouseEmployee.builder().
                name(WAREHOUSEEMPLOYEE_NAME).
                build();

        getWarehouseEmployeeService().add(warehouseEmployee);
        getWarehouseEmployeeService().add(warehouseEmployee);
//        System.out.println(getWarehouseEmployeeService().listAll().stream().filter(name -> name.getName().equals(WAREHOUSEEMPLOYEE_NAME)).count());
        assertThat(getWarehouseEmployeeService().listAll().stream().
                filter(employee -> employee.getName().
                        equals(WAREHOUSEEMPLOYEE_NAME)).
                count()).isEqualTo(1L);
    }

    @Test
    void updateWarehouseEmployeeName_Test(){

        getWarehouseEmployeeService().add(WarehouseEmployee.builder().name(WAREHOUSEEMPLOYEE_NAME).build());

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

        return getWarehouseEmployeeService().
                getWarehouseEmployeeByName(name).isPresent()
                ? getWarehouseEmployeeService().
                getWarehouseEmployeeByName(name).get().getName()
                : "NONE";
    }

}
