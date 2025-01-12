package com.archsoft.model.order;

import com.archsoft.model.AbstractDocument;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Order extends AbstractDocument {

    private LocalDateTime dateTime;
    private String customerId;
    private BigDecimal total;
    private BigDecimal totalNet;
    private String status;
    private String paymentId;
    private Integer percent;
    private List<OrderItem> items = new ArrayList<>();

    public void calculateTotals() {

    }

    public void addItem(String productId, Integer quantity, BigDecimal price, Integer discount) {
        OrderItem orderItem = new OrderItem();

        orderItem.setProductId(productId);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        orderItem.setDiscount(discount);

        items.add(orderItem);

        double sum = calcTotal();
        double sumNet = sum; //calcTotalNet();

        setTotal(new BigDecimal(sum));
        setTotalNet(new BigDecimal(sumNet));
    }

    // TODO: Corrigir
//    private double calcTotalNet() {
//        return items.stream()
//                .mapToDouble(item -> item.getPrice()
//                        .multiply(new BigDecimal(item.getQuantity().toString()))
//                        .multiply((100 - item.getDiscount()) / 100d)))
//                .sum();
//    }

    private double calcTotal() {
        return items.stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice().doubleValue())
                .sum();
    }

    public void removeProduct(String productId) {
        items = items.stream()
                .filter(orderItem -> !orderItem.getProductId().equals(productId))
                .collect(Collectors.toList());
        setTotal(new BigDecimal(calcTotal()));
        setTotalNet(total);
    }
}
