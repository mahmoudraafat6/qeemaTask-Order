package com.qeemaTask.repositories;

import com.qeemaTask.entities.OrderItemEntity;
import com.qeemaTask.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity , Integer> {

}
