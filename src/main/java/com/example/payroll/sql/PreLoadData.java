package com.example.payroll.sql;

import com.example.payroll.classes.Employee;
import com.example.payroll.classes.Order;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.OrderRepository;
import com.example.payroll.util.types.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PreLoadData {
    public static final Logger log = LoggerFactory.getLogger(PreLoadData.class);
    @Bean
    CommandLineRunner initDataBase(EmployeeRepository employeeRepository, OrderRepository orderRepository){
        return args -> {
            List<Employee> savedEmployees = employeeRepository.findAll();
            if (savedEmployees.isEmpty()) {
                log.info("Preloading" +employeeRepository.save( new Employee("joao paulo","santos", "developer")));
            }
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order ->
                log.info("Preloaded " + order)
            );
        };
    }
}
