package com.codeDecode.aopDemo.aop;

import com.codeDecode.aopDemo.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {

    // for controller class
    @Before(value = "execution(* com.codeDecode.aopDemo.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Request to " + joinPoint.getSignature()+ " started at "+ new Date());

    }

    @After(value = "execution(* com.codeDecode.aopDemo.controller.EmployeeController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Request to " + joinPoint.getSignature()+ " ended at "+ new Date());

    }

    // for service class

    @Before(value = "execution(* com.codeDecode.aopDemo.service.EmployeeService.*(..))")
    public void beforeAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service layer" + joinPoint.getSignature()+ " started at "+ new Date());

    }

    @After(value = "execution(* com.codeDecode.aopDemo.service.EmployeeService.*(..))")
    public void afterAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service layer " + joinPoint.getSignature()+ " ended at "+ new Date());

    }

    @AfterReturning(value = "execution(* com.codeDecode.aopDemo.service.EmployeeService.addEmployee(..))",returning = "employee")
    public void afterReturningAdviceForAddEmployeeService(JoinPoint joinPoint, Employee employee){
        System.out.println("business logic to save an employee with id "+employee.getId());
    }

    @AfterThrowing(value = "execution(* com.codeDecode.aopDemo.service.EmployeeService.addEmployee(..))",throwing = "exception")
    public void afterThrowingAdviceForAddEmployeeService(JoinPoint joinPoint, Exception exception){
        System.out.println("business logic to save an employee with id "+exception.getMessage());
    }

    @Around(value = "execution(*  com.codeDecode.aopDemo.service.EmployeeService.addEmployee(..))")
    public Employee aroundAdviceForAddEmployeeService(ProceedingJoinPoint joinPoint){
        System.out.println("Inside Around Advice in Aspect : Business logic to save employee started at "+new Date());
        try {
            // we can change the request method parameter using @Around AOP
//            Employee[] empArr = new Employee[1];
//            Employee dummyEmp = new Employee();
//            dummyEmp.setName("dumy");
//            empArr[0]=dummyEmp;
            Employee employee= (Employee) joinPoint.proceed();
            return employee;

        } catch (Throwable e) {
            System.out.println("Inside Around Advice in Aspect : Business logic to save employee failed terribly at "+new Date());

        }
        System.out.println("Inside Around Advice in Aspect : Business logic to save employee ended at "+new Date());
        return null;
    }

}
