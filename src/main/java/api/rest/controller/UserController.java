package api.rest.controller;

import api.rest.request.UserRequest;
import api.rest.response.UserResponse;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse>createUser(@RequestBody UserRequest userRequest){
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/allUser")
    public ResponseEntity<List<UserResponse>>findAllUser(){
        List<UserResponse> responses = userService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    @GetMapping(value = "/{idUser}")
    public ResponseEntity<UserResponse>findByUser(@PathVariable Long idUser){
        UserResponse response = userService.findByUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping(value = "/{idUser}")
    public ResponseEntity<UserResponse>updateUser(@RequestBody UserRequest userRequest,@PathVariable Long idUser){
        UserResponse response = userService.updateUser(userRequest,idUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(value = "/{idUser}")
    public ResponseEntity<UserResponse>deleteUser(@PathVariable Long idUser){
        UserResponse response = userService.deleteUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
