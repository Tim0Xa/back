package com.lastik.back.loader;

import com.lastik.back.pojo.Group;
import com.lastik.back.pojo.Image;
import com.lastik.back.pojo.Lastik;
import com.lastik.back.pojo.User;
import com.lastik.back.repo.GroupRepo;
import com.lastik.back.repo.ImageRepo;
import com.lastik.back.repo.LastikRepo;
import com.lastik.back.repo.UserRepo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbLoader {

    private static final Logger log = LoggerFactory.getLogger(DbLoader.class);

    @Bean
    CommandLineRunner clr(ImageRepo imgRep, GroupRepo grpRep, LastikRepo lskRep, UserRepo usrRep) {
        System.out.println("\n \t\t>>> Start CommandLineRunner <<< \n\t\t\t\t\t Load DB \n");

        return args -> {

            System.out.println("\n\t\t\t <<< Load Data >>>\n");

            /** source for Groups */
//            File srcGroups = null;
            /** source for Lastiks */
//            File srcLastik = null;

            List<Group> groups = new ArrayList<>();
            List<Image> images = new ArrayList<>();
            List<Lastik> lastiks = new ArrayList<>();
            List<User> users = new ArrayList<>();

            /*  fill in groups and images  */
            getGroups(groups, images);

            /*  Scanning for lastiks  */
            getLastiks(lastiks, images);

            /*  Set users  */
            User paid = new User();
            User free = new User();

            paid.setName("Am Paid");
            paid.setLogin("paidUserLogin");

            free.setName("Am FreeE");
            free.setLogin("simpleLogin");

            users.add(paid);
            users.add(free);

            /*  Saving all images to DB  */
            saveAllToDB(imgRep, images);
            /*  Saving all groups to DB  */
            saveAllToDB(grpRep, groups);
            /*  Saving all lastiks to DB  */
            saveAllToDB(lskRep, lastiks);
            /*  Saving all users to DB  */
            saveAllToDB(usrRep, users);

            System.out.println("\n\t\t\t\t <<< LOADED >>>\n");
        };
    }

    private void getLastiks(List<Lastik> lastiks, List<Image> images) {

        /** source for Groups */
        File srcGroups = getSrcFolder("/Lastiks");

        if (srcGroups != null && srcGroups.listFiles().length > 0) {

            for (File lastik : srcGroups.listFiles()) {
                System.out.println("  > " + lastik.getName());
                Lastik lstk = new Lastik();
                Image img = new Image();

                img.setName(lastik.getName());
                img.setImg(this.readImage(lastik));
                lstk.setName(lastik.getName());
                lstk.setImage(img);

                lastiks.add(lstk);
                images.add(img);
            }
        }
    }

    private byte[] readImage(File file) {
        if (file != null && file.length() > 0)
            try {
                return IOUtils.toByteArray(new FileInputStream(file.getPath()));
            } catch (Exception e) {
                System.out.println(e);
                log.error(" readImage ", e);
            }
        return new byte[0];
    }

    private void saveAllToDB(JpaRepository jRep, List list) {
        /**  Saving all SOMETHING to DB  */
        try {
            if (list != null && !list.isEmpty()) {
                jRep.saveAll(list);
            }
        } catch (Exception e) {
            System.out.println(" > Save Images: " + e);
        }
    }

    private File getSrcFolder(String addr) {
        /** get source folder */
        System.out.println(" Getting source folder for: " + addr);
        if (getClass().getResource(addr) == null) return null;
        else return new File(getClass().getResource(addr).getFile());
    }

    private void getGroups(List groups, List images) {

        /** source for Groups */
        File srcGroups = getSrcFolder("/Groups");

        if (srcGroups != null && srcGroups.listFiles().length > 0) {

            for (File gr : srcGroups.listFiles()) {
                System.out.println("  > " + gr.getName());

                if (gr == null || gr.getName().equals("")) continue;

                Group group = new Group();
                group.setName(gr.getName());

                List<Image> imgs = new ArrayList<>();

                for (File img : gr.listFiles()) {
                    System.out.println("  > " + img.getName() + " " + img.getPath());
                    if (img == null
                            || img.getName().equals("")
                            || img.length() < 1) {
                        System.out.println("   > " + "EXIT!");
                        continue;
                    }
                    Image image = new Image();
                    image.setName(img.getName());
                    image.setImg(readImage(img));
                    imgs.add(image);
                }

                group.setImages(imgs);
                images.addAll(imgs);
                groups.add(group);
            }
        }
    }
}
