package com.example.demo2.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name,cccd,sdt;
    @Column
    private LocalDate checkin,checkout;
    @Column
    private int numofpeople;
    @Column
    private int numofday;
    @Column
    private int status,deleted;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Room> room = new HashSet<>();
//    @ManyToMany
//    private Set<Cart> cart = new HashSet<>();
    public void addRoom(Room room){
        this.room.add(room);
    }
    public Room layTheoId(int id){
        for(Room i:room){
            if(i.getId()==id){
                return i;
            }
        }
        return null;
    }
}
