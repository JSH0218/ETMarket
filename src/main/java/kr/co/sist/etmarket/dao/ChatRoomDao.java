package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ChatRoom;
import kr.co.sist.etmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomDao extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findBySender(User sender);

    // 해당 itemId와 senderId에 대한 데이터가 없으면
    ChatRoom findByItem_ItemIdAndSender_UserId(Long itemId, Long senderId);

    ChatRoom findByChatroomId(Long chatroomId);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.sender = :user OR cr.receiver = :user ORDER BY cr.createDate desc ")
    List<ChatRoom> findBySenderOrReceiver(@Param("user") User user);

}