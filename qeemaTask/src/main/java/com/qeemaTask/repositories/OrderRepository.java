package com.qeemaTask.repositories;


import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
