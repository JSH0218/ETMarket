package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dao.ItemDto;
import kr.co.sist.etmarket.dto.UserSearchDto;
import kr.co.sist.etmarket.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;

    @PostMapping("/insert")
    public void insertContent(@RequestBody UserSearchDto userSearchDto) {
        System.out.println("userSearchDto = " + userSearchDto);
        userSearchService.insertContent(userSearchDto);
    }

    @GetMapping("/init")
    public List<UserSearchDto> getTop8SearchContent(@RequestParam Long userId) {
        try {
            UserSearchDto userSearchDto = new UserSearchDto();
            userSearchDto.setUserId(userId);
            return userSearchService.getTop8SearchContent(userSearchDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져 클라이언트에게 전달
        }
    }

    @GetMapping("/items")
    public List<ItemDto> findItemsByContentAndItemTitle(@RequestParam String content) {
        return userSearchService.getItemTitle(content);
    }

//    // 인기 검색어 8개 출력
//    @GetMapping("/popular")
//    public List<UserSearchDto> getTop8PopularContent() {
//        return userSearchService.getTop8PopularContent();
//    }

//    // 최근 검색어를 클릭 시 상단으로 이동시키기 위함
//    @PostMapping("/update")
//    public void getUserSearchUpdate(@RequestBody UserSearchDto userSearchDto) {
//        User user = new User();
//        user.setId(userSearchDto.getUserId());
//        userSearchService.getUserSearchUpdate(user, userSearchDto.getContent());
//    }



}