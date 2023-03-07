package com.example.onetoonerestfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")  // Liên kết với nhau qua khóa ngoại address_id
    private Address address;


    /**
    * Mục đích của Cascade là để toàn vẹn dữ liệu, dữ liệu sẽ thống nhất ở 2 bảng,
    * tránh thừa dữ liệu không cần thiết.
    * */
}
