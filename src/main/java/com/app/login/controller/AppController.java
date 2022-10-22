package com.app.login.controller;

import com.app.login.dto.AppDTO;
import com.app.login.model.App;
import com.app.login.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping()
    public ResponseEntity<AppDTO> createOrUpdatelogin(@RequestBody AppDTO appDTOp) throws Exception {
        return ResponseEntity.ok(appService.createOrUpdatelogin(appDTOp));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<App> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(appService.deleteById(id));
    }

    @GetMapping("/ageLimit/{ageLimit}")
    public List<AppDTO> getofListBetweenAge(List<AppDTO> appDTOS,@PathVariable(value = "ageLimit") int ageLimit){
        return (List<AppDTO>) ResponseEntity.ok(appService.getofListBetweenAge(appDTOS,ageLimit));
    }

    @GetMapping("/info")
    public ResponseEntity<AppDTO> loginSuccessOrFail(@RequestBody AppDTO dto) throws Exception {
        return ResponseEntity.ok(appService.loginSuccessOrFail(dto));
    }
}
