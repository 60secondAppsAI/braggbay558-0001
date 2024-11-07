package com.braggbay558.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay558.domain.WishlistItem;
import com.braggbay558.dto.WishlistItemDTO;
import com.braggbay558.dto.WishlistItemSearchDTO;
import com.braggbay558.dto.WishlistItemPageDTO;
import com.braggbay558.dto.WishlistItemConvertCriteriaDTO;
import com.braggbay558.service.GenericService;
import com.braggbay558.dto.common.RequestDTO;
import com.braggbay558.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishlistItemService extends GenericService<WishlistItem, Integer> {

	List<WishlistItem> findAll();

	ResultDTO addWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

	ResultDTO updateWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

    Page<WishlistItem> getAllWishlistItems(Pageable pageable);

    Page<WishlistItem> getAllWishlistItems(Specification<WishlistItem> spec, Pageable pageable);

	ResponseEntity<WishlistItemPageDTO> getWishlistItems(WishlistItemSearchDTO wishlistItemSearchDTO);
	
	List<WishlistItemDTO> convertWishlistItemsToWishlistItemDTOs(List<WishlistItem> wishlistItems, WishlistItemConvertCriteriaDTO convertCriteria);

	WishlistItemDTO getWishlistItemDTOById(Integer wishlistItemId);







}





