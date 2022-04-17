package com.lastik.back.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Images")
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    private byte[] img;

}
