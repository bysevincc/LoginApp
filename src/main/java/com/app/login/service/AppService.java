package com.app.login.service;

import com.app.login.mapper.AppMapper;
import com.app.login.model.App;
import com.app.login.dto.AppDTO;
import com.app.login.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository repository;

    private final AppMapper appMapper;

    @Transactional
    public AppDTO createOrUpdatelogin(AppDTO appDTO) throws Exception {
        App app1 = new App();
        String encryptedpassword = null;
        if (repository.findByEmail(appDTO.getEmail()) != null) {
            throw new Exception("email already exists");
        }
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(appDTO.getPassword().getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        appMapper.updateEntityFromDto(appDTO, app1);
        app1.setAge(appDTO.getAge());
        app1.setPassword(encryptedpassword);
        app1.setEmail(appDTO.getEmail());
        repository.save(app1);

        return appMapper.toDto(app1);
    }

    @Transactional
    public App deleteById(Long id) {
        App app = new App();
        if (app != null) {
            this.repository.deleteById(id);
            return app;
        } else {
            log.error("object could not found by id: {}", id);
            throw new RuntimeException("object could not found by id: {}");
        }
    }

    public List<AppDTO> getofListBetweenAge(List<AppDTO> appDTOS, int ageLimit) {

        List<AppDTO> result = appDTOS.stream()  // convert list to stream
                .filter(actor -> actor.getAge() > ageLimit)
                .collect(Collectors.toList());
        return result;
    }

    public AppDTO loginSuccessOrFail(AppDTO appDTO) throws Exception {
        App app = new App();
        appMapper.updateEntityFromDto(appDTO, app);
        try {
            if (repository.findByEmail(appDTO.getEmail()).equals(app.getEmail()) && repository.findByPassword(appDTO.getPassword()).equals(app.getPassword())) {
                app.setStatus("Login Success");
                repository.save(app);
                return appMapper.toDto(app);
            }
        } catch (Exception e) {
            e.printStackTrace();
            app.setStatus("Login failed" + e.getMessage());
        }
        return appMapper.toDto(app);

    }
}