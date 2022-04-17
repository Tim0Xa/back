package com.lastik.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private @Id @GeneratedValue Long id;
    private String login;
    private String name;
    @OneToMany
    @JoinColumn(name = "lastiks_id")
    private List<Lastik> lastiks;

}
