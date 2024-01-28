package com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Orders {

    @Id
    private Integer id;
    @Column(name = "order_item")
    private String orderItem;
    private Integer price;
    @Column(name = "order_date")
    private Date orderDate;

}
