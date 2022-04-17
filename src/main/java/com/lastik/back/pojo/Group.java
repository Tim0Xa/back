package com.lastik.back.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Groups")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "gr_icon_id")
    private Image grIcon;
    @OneToMany
    @JoinColumn(name = "images_id")
    private List<Image> images;

}
