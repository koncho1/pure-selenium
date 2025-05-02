package com.gui.service;

import com.gui.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class UserService {
    
    Properties properties;

    public UserService() {
        ConfigProvider configProvider = new ConfigProvider();
        this.properties = configProvider.loadConfig();
    }

    public User getUser() {
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
