package com.designxplay.arcadextest2.controller;

import com.designxplay.arcadextest2.dto.ItemForm;
import com.designxplay.arcadextest2.entity.Item;
import com.designxplay.arcadextest2.entity.PlayerItem;
import com.designxplay.arcadextest2.repository.ItemRepository;
import com.designxplay.arcadextest2.repository.PlayerItemRepository;
import com.designxplay.arcadextest2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/player")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PlayerItemRepository playerItemRepository;


    @GetMapping("/inputdata")
    public String newArticleForm(Model model)
    {
        model.addAttribute("message", "example");
        logger.debug("server is working");
        return "articles/inputdata";
    }

    @PostMapping("/create")
    public String createArticle(ItemForm form) {
        Item item = form.toEntity();
        Item saved = itemRepository.save(item);

        // 저장된 Item의 정보를 로깅
        logger.info("Item saved: {}", saved);


        return "redirect:/player/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Item articleEntity = itemRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    // 기타 API 엔드포인트 추가
}