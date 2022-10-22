package com.app.login.service;

import com.app.login.dto.AppDTO;
import com.app.login.mapper.AppMapper;
import com.app.login.model.App;
import com.app.login.repository.AppRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppServiceTest {
    @InjectMocks
    private AppService appService;

    @Mock
    private AppRepository repository;

    @Mock
    private AppMapper appMapper;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void createOrUpdatelogin() throws Exception {
        App app=new App();
        AppDTO input =
                AppDTO.builder()
                        .id(100L)//
                        .age(25)
                        .email("sevincbesdas@hotmail.com")
                        .status("success")
                        .password("test")
                        .build();

        when(appMapper.toEntity(input)).thenReturn(app);
        when(repository.save(app)).thenReturn(app);
        when(appMapper.toDto(app)).thenReturn(input);

        AppDTO created = appService.createOrUpdatelogin(input);

        assertEquals(16, created.getAge());
        assertEquals("sevinc@hotmail.com",created.getEmail());

    }
}