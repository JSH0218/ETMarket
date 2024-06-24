package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgDao extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByItemItemId(Long itemId);
}
