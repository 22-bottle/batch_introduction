package com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.account;

import com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.order.Orders;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Accounts {

    @Id
    private Integer id;
    @Column(name = "order_item")
    private String orderItem;
    private Integer price;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "account_date")
    private Date accountDate;

    public Accounts(Orders orders) {
        this.id = orders.getId();
        this.orderItem = orders.getOrderItem();
        this.price = orders.getPrice();
        this.orderDate = orders.getOrderDate();
        this.accountDate = new Date();
    }

}
