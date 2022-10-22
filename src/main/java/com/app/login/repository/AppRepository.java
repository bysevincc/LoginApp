package com.app.login.repository;

import com.app.login.model.App;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppRepository extends JpaRepository<App,Long> {
    App findByEmail(String email);

    App findByPassword(String password);
}
