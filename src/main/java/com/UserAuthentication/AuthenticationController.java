package com.UserAuthentication;




import com.UserAuthentication.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@RestController public class AuthenticationController {
    private List<User> userList = new ArrayList<>();



    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody User request) {
        String userName = request.getUserName();
        String password = request.getPassword();

        if (userName.isEmpty() || password.isEmpty()){
            return  ResponseEntity.badRequest().body("Username and password are required fields");
        }

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);

        userList.add(newUser);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody User request) {
        String userName = request.getUserName();
        String password = request.getPassword();

        boolean isValidLogin = validateLogin(userName, password);

        if (isValidLogin) {
            return ResponseEntity.ok("Login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

    private boolean validateLogin(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName.trim()) && user.getPassword().equalsIgnoreCase(password.trim())) {
                return true;
            }
        }
        return false;


    }


    @GetMapping(path = "/getUser")
    public ResponseEntity<List> getUser (){
        if (userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userList);
    }



    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) {
        boolean removed = userList.removeIf(user -> user.getUserName().equalsIgnoreCase(userName));

        if (removed) {
            return ResponseEntity.ok("Användaren har tagits bort");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}