package kr.co.sist.etmarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.etenum.UserStatus;
import kr.co.sist.etmarket.service.JoinService;

@Controller
public class JoinController {

	@Autowired
	JoinService joinService;
	
	// 가입폼으로 이동
	@GetMapping("/member/join")
	public String goJoin() {
		return "join/joinForm";
	}
	
	// 회원가입 save
//	@PostMapping("/member/joinProcess")
//	public String joinProcess(@ModelAttribute UserDto userDto, @RequestParam("phoneVerifyCode") String phoneVerifyCode, 
//			Errors errors, Model model) {
//		
//		userDto.setUserStatus(UserStatus.ACTIVE); //회원상태 세팅
//		userDto.setUserJoinType("GENERAL"); // 가입유형 세팅
//		
//		System.out.println("joinController 입력받은 값: "+userDto+", 인증코드: "+phoneVerifyCode);
//		
//		joinService.join(userDto);
//		
//		System.out.println("가입성공");
//		
//		return "redirect:/login";
//	}
	
	@PostMapping("/member/join/loginIdValid")
	@ResponseBody
	public String loginIdValid(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, Model model) {
		System.out.println(userDto.getUserLoginId());
		
		if(bindingResult.hasErrors()) {
			System.out.println("에러");
		} else {
			model.addAttribute("userLoginId", userDto.getUserLoginId());
		}
		return userDto.getUserLoginId();
	}
	

	
	// 유효성 검사
	
	
	
}
