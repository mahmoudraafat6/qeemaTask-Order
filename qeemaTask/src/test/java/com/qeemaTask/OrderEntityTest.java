package com.qeemaTask;

import com.qeemaTask.entities.OrderEntity;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class OrderEntityTest {


    OrderEntity orderEntity ;

    @BeforeEach
    void setup(){
        orderEntity = new OrderEntity();
    }

    @Test
    public void TestOrderEntityCreation(){

        orderEntity.setId(1);
        orderEntity.setCreatedAt(LocalDateTime.now());

        assertNotNull(orderEntity);
        assertEquals(1 , orderEntity.getId());
        assertNotNull(orderEntity.getCreatedAt());

    }



}
