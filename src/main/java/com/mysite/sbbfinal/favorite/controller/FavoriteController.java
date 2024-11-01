package com.mysite.sbbfinal.favorite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbbfinal.favorite.dto.Favorite;
import com.mysite.sbbfinal.favorite.dto.Product;
import com.mysite.sbbfinal.favorite.service.FavoriteService;
import com.mysite.sbbfinal.favorite.service.NaverShoppingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {
	
	@Autowired
	private final NaverShoppingService naverShoppingService;
	private final FavoriteService favoriteService;
	
	@GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return naverShoppingService.searchProducts(query);
    }
	
	@PostMapping("/favorites")
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteService.addFavorite(favorite);
        return ResponseEntity.ok("Added to favorites");
    }
	
	@GetMapping("/favorites")
    public List<Favorite> getFavorites(@RequestParam int userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }
	
}
