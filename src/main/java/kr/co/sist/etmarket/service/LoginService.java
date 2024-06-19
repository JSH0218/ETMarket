package kr.co.sist.etmarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	@Autowired
	UserDao userDao;
	
	// 로그인
	public Optional<UserDto> login(UserDto userDto) {
		// user에서 userLoginId 값 가져옴
		Optional<UserDto> findUser = userDao.findByUser(userDto.getUserLoginId());
		
		// 아이디가 존재하는지 확인하고, 비밀번호가 일치하는지 확인
		if (findUser.isPresent() && findUser.get().getUserPassword().equals(userDto.getUserPassword())) {
			//System.out.println("LoginService에서 유저정보 출력:"+findUser);
			return findUser;
		}

        return Optional.empty();
    }
	
	// 아이디 찾기
	public Optional<UserDto> findLoginId(String userEmail, String userPhone){
		Optional<UserDto> findLoginId = userDao.findByUserLoginId(userEmail, userPhone);
		
		if(findLoginId.isPresent()) {
			//System.out.println("로그인 아이디 서비스에서 출력:"+findLoginId);			
			return findLoginId;
		}		
		
		return Optional.empty();
	}

}
