package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {

    /**
     * key 是商品编号
     * value 是商品信息
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){

        // 先查看购物车是否已添加过此商品，如果已添加，则数量累加，如果没有添加过，直接放到集合中
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 之前没添加过
            items.put(cartItem.getId(),cartItem);
        } else {
            // 之前添加过
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
        }


    }


    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count) {
        // 先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(count)));
        }

    }




    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal cartTotalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            cartTotalPrice = cartTotalPrice.add(entry.getValue().getTotalPrice());
        }

        return cartTotalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
