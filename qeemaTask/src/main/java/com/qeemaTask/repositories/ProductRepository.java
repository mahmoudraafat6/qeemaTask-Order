package com.qeemaTask.repositories;

import com.qeemaTask.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity ,Integer> {

    @Query("SELECT p FROM ProductEntity p JOIN p.orderItems oi WHERE oi.order.id = :orderId")
    List<ProductEntity> getItem(@Param("orderId") int orderId);
}
