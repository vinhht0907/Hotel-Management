package com.example.demo2.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    private Item getItemById(int id){
        for(Item i:items){
            if(i.getP().getId()==id){
                return i;
            }
        }
        return null;
    }

    public int getNumById(int id){
        return getItemById(id).getNum();
    }

    public void addItems(Item t){
        if(getItemById(t.getP().getId())!=null){
            Item m = getItemById(t.getP().getId());
            m.setNum(m.getNum()+t.getNum());
        } else{
            items.add(t);
        }
    }

    public void removeItem(int id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }

}
