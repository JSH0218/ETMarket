package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.KeyValueMapper;
import kr.co.sist.etmarket.dto.ItemDetailDto;
import kr.co.sist.etmarket.dto.SimpleSellerDto;
import kr.co.sist.etmarket.dto.SimilarItemDto;
import kr.co.sist.etmarket.service.CommonService;
import kr.co.sist.etmarket.service.ItemDetailService;
import kr.co.sist.etmarket.service.SellerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemTempController {

    private final ItemDetailService itemDetailService;
    private final SellerDetailService sellerDetailService;
    private final CommonService commonService;

    @Autowired
    public ItemTempController(ItemDetailService itemDetailService, SellerDetailService sellerDetailService, CommonService commonService) {
        this.itemDetailService = itemDetailService;
        this.sellerDetailService = sellerDetailService;
        this.commonService = commonService;
    }

    @GetMapping("/items/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {

        ItemDetailDto itemDetailDto = itemDetailService.getItemDetail(itemId);
        String updateTime = commonService.convertTime(itemDetailDto.getItemUpdateDate());
        String categoryName = KeyValueMapper.getValue(itemDetailDto.getCategoryName());
        String itemStatus = KeyValueMapper.getValue(itemDetailDto.getItemStatus());
        List<SimilarItemDto> similarItemDtoList = itemDetailService.getSimilarItems(itemDetailDto.getItemId(),itemDetailDto.getCategoryName());

        SimpleSellerDto simpleSellerDto = sellerDetailService.getSimpleSellerInfo(itemDetailDto.getSellerId(),itemId);

        model.addAttribute("itemDetailDto", itemDetailDto);
        model.addAttribute("updateTime", updateTime);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("itemStatus", itemStatus);
        model.addAttribute("similarItemList", similarItemDtoList);
        model.addAttribute("sellerDto", simpleSellerDto);


        return "item/item_detail";
    }

    @GetMapping("/seller/{sellerId}/items")
    public String sellerItems(@PathVariable Long sellerId, Model model) {

        return "seller/seller_detail_item";
    }

    @GetMapping("/seller/{sellerId}/reviews")
    public String sellerReviews(@PathVariable Long sellerId, Model model) {

        return "seller/seller_detail_review";
    }


    @GetMapping("/detail")
    public String itemDetailTest() {

        return "item/item_detail_backup";
    }

    @GetMapping("/seller/item")
    public String sellerItemList() {
        return "seller/seller_detail_item_backup";
    }
    @GetMapping("/seller/review")
    public String sellerReviewList() {
        return "seller/seller_detail_review_backup";
    }
}
