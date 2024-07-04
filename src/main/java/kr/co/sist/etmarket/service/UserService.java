package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    // 회원번호로 조회
    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }
    
    // 탈퇴 ACTIVE -> DELETE
    public void delete(Long userId) {
    	userDao.deleteUser(userId);
    }
    
    // 회원정보 수정
    public User update(UserDto userDto) {
    	// 데이터베이스에서 기존 사용자 정보를 가져옴
        User existingUser = userDao.findById(userDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userDto.getUserId()));
        
        // 필요한 필드만 업데이트
        if (userDto.getUserName() != null) {
            existingUser.setUserName(userDto.getUserName());
        }
        if (userDto.getUserPassword() != null) {
            existingUser.setUserPassword(userDto.getUserPassword());
        } 
        if (userDto.getUserPhone() != null) {
            existingUser.setUserPhone(userDto.getUserPhone());
        }
        if (userDto.getUserEmail() != null) {
            existingUser.setUserEmail(userDto.getUserEmail());
        }
        if (userDto.getUserImg() != null) {
            existingUser.setUserImg(userDto.getUserImg());
        }

    	return userDao.save(existingUser);
    }
    

}
