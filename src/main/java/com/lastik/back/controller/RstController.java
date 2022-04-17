package com.lastik.back.controller;

import com.lastik.back.pojo.Group;
import com.lastik.back.pojo.Image;
import com.lastik.back.pojo.Lastik;
import com.lastik.back.pojo.User;
import com.lastik.back.repo.GroupRepo;
import com.lastik.back.repo.ImageRepo;
import com.lastik.back.repo.LastikRepo;
import com.lastik.back.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RstController {

    @Autowired
    private ImageRepo imgRep;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private LastikRepo lastikRepo;

    @GetMapping("/")
    public String defMap() {
        return "Hello!!! ~ version 2.3.11";
    }

    @GetMapping("/images")
    public List<Image> allImages() {
        return imgRep.findAll();
    }
    @GetMapping("/images/{id}")
    public Image getImage(@PathVariable Long id) throws Exception {
        return imgRep.findById(id).orElseThrow(() -> new Exception(" groupNotFound EXCEPTION!!! " + id));
    }
    @GetMapping("/groups")
    public List<Group> allGroups() {
        return groupRepo.findAll();
    }
    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable Long id) throws Exception {
        return groupRepo.findById(id).orElseThrow(() -> new Exception(" groupNotFound EXCEPTION!!! " + id));
    }
    @GetMapping("/users")
    public List<User> allUsers() {
        return userRepo.findAll();
    }
    @GetMapping("/lastiks")
    public List<Lastik> allLastiks() {
        return lastikRepo.findAll();
    }
//    @GetMapping("/lastiks/allid")
//    public List<String> getLastikIds() {
//        return lastikRepo.findAll().forEach((x) -> {x.getId()});
//    }
    @GetMapping("/lastiks/{id}")
    public Lastik getLastik(@PathVariable Long id) throws Exception {
        return lastikRepo.findById(id).orElseThrow(() -> new Exception(" groupNotFound EXCEPTION!!! " + id));
    }
}
