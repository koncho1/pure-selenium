package com.gui.service;

import com.gui.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    Properties properties = new Properties();

    public void loadProps() {

        try {
            properties.load(Files.newInputStream(Paths.get("src/main/java/com/gui/resources/test_data.properties")));
        } catch (IOException e) {
            logger.info("An error loading the file has occurred");
        }
    }

    public User getUser() {
        loadProps();
        User user = new User();
        user.setFirstName(properties.getProperty("firstname"));
        user.setLastName(properties.getProperty("lastname"));
        user.setEmail(properties.getProperty("email"));
        user.setAddress(properties.getProperty("address"));
        user.setCity(properties.getProperty("city"));
        user.setZipcode(properties.getProperty("zipcode"));
        user.setLogin(properties.getProperty("login"));
        user.setPassword(properties.getProperty("password"));
        return user;
    }
}
