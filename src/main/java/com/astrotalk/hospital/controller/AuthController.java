package com.astrotalk.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import com.astrotalk.hospital.constants.Constant;
import com.astrotalk.hospital.dto.UserDto;

import com.astrotalk.hospital.model.User;
import com.astrotalk.hospital.response.ResponseHandler;
import com.astrotalk.hospital.service.UserService;



@Controller
public class AuthController {
	
	 private UserService userService;

	    public AuthController(UserService userService) {
	        this.userService = userService;
	    }

    
	@PostMapping("/register")
	public ResponseEntity<Object> registration(@RequestBody UserDto userDto) {
		try {
			User existingUser = userService.findUserByEmail(userDto.getEmail());
			
			if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
				return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_USER_EXIST, HttpStatus.CONFLICT, null);
     
	        }
			
			userService.saveUser(userDto);
			return ResponseHandler.generateResponse(Constant.SUCCESS_USER_REGISTRATION_MESSAGE, HttpStatus.CREATED,
					null);
			
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
//	@GetMapping("/login")
//	public ResponseEntity<Object> login(@PathVariable Long patientId) {
//		try {
//			Optional<Patient> searchedPatient = patientService.findById(patientId);
//			if (searchedPatient.isEmpty()) {
//				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
//			}
//			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, searchedPatient.get());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//		}
//	}
	
	// handler method to handle login request
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//	
    
// // handler method to handle user registration form submit request
//    @PostMapping("/register/save")
//    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
//                               BindingResult result,
//                               Model model){
//        User existingUser = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "/register";
//        }
//
//        userService.saveUser(userDto);
//        return "redirect:/register?success";
//    }
    
    // handler method to handle list of users
//    @GetMapping("/hospital/users")
//    public String users(Model model){
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "hospital";
//    }
    
 
    
    
}