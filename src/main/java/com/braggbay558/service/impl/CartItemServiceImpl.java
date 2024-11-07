package com.braggbay558.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay558.dao.GenericDAO;
import com.braggbay558.service.GenericService;
import com.braggbay558.service.impl.GenericServiceImpl;
import com.braggbay558.dao.CartItemDAO;
import com.braggbay558.domain.CartItem;
import com.braggbay558.dto.CartItemDTO;
import com.braggbay558.dto.CartItemSearchDTO;
import com.braggbay558.dto.CartItemPageDTO;
import com.braggbay558.dto.CartItemConvertCriteriaDTO;
import com.braggbay558.dto.common.RequestDTO;
import com.braggbay558.dto.common.ResultDTO;
import com.braggbay558.service.CartItemService;
import com.braggbay558.util.ControllerUtils;





@Service
public class CartItemServiceImpl extends GenericServiceImpl<CartItem, Integer> implements CartItemService {

    private final static Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

	@Autowired
	CartItemDAO cartItemDao;

	


	@Override
	public GenericDAO<CartItem, Integer> getDAO() {
		return (GenericDAO<CartItem, Integer>) cartItemDao;
	}
	
	public List<CartItem> findAll () {
		List<CartItem> cartItems = cartItemDao.findAll();
		
		return cartItems;	
		
	}

	public ResultDTO addCartItem(CartItemDTO cartItemDTO, RequestDTO requestDTO) {

		CartItem cartItem = new CartItem();

		cartItem.setCartItemId(cartItemDTO.getCartItemId());


		cartItem.setQuantity(cartItemDTO.getQuantity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		cartItem = cartItemDao.save(cartItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CartItem> getAllCartItems(Pageable pageable) {
		return cartItemDao.findAll(pageable);
	}

	public Page<CartItem> getAllCartItems(Specification<CartItem> spec, Pageable pageable) {
		return cartItemDao.findAll(spec, pageable);
	}

	public ResponseEntity<CartItemPageDTO> getCartItems(CartItemSearchDTO cartItemSearchDTO) {
	
			Integer cartItemId = cartItemSearchDTO.getCartItemId(); 
 			Integer quantity = cartItemSearchDTO.getQuantity(); 
 			String sortBy = cartItemSearchDTO.getSortBy();
			String sortOrder = cartItemSearchDTO.getSortOrder();
			String searchQuery = cartItemSearchDTO.getSearchQuery();
			Integer page = cartItemSearchDTO.getPage();
			Integer size = cartItemSearchDTO.getSize();

	        Specification<CartItem> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cartItemId, "cartItemId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, quantity, "quantity"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CartItem> cartItems = this.getAllCartItems(spec, pageable);
		
		//System.out.println(String.valueOf(cartItems.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(cartItems.getTotalPages()));
		
		List<CartItem> cartItemsList = cartItems.getContent();
		
		CartItemConvertCriteriaDTO convertCriteria = new CartItemConvertCriteriaDTO();
		List<CartItemDTO> cartItemDTOs = this.convertCartItemsToCartItemDTOs(cartItemsList,convertCriteria);
		
		CartItemPageDTO cartItemPageDTO = new CartItemPageDTO();
		cartItemPageDTO.setCartItems(cartItemDTOs);
		cartItemPageDTO.setTotalElements(cartItems.getTotalElements());
		return ResponseEntity.ok(cartItemPageDTO);
	}

	public List<CartItemDTO> convertCartItemsToCartItemDTOs(List<CartItem> cartItems, CartItemConvertCriteriaDTO convertCriteria) {
		
		List<CartItemDTO> cartItemDTOs = new ArrayList<CartItemDTO>();
		
		for (CartItem cartItem : cartItems) {
			cartItemDTOs.add(convertCartItemToCartItemDTO(cartItem,convertCriteria));
		}
		
		return cartItemDTOs;

	}
	
	public CartItemDTO convertCartItemToCartItemDTO(CartItem cartItem, CartItemConvertCriteriaDTO convertCriteria) {
		
		CartItemDTO cartItemDTO = new CartItemDTO();
		
		cartItemDTO.setCartItemId(cartItem.getCartItemId());

	
		cartItemDTO.setQuantity(cartItem.getQuantity());

	

		
		return cartItemDTO;
	}

	public ResultDTO updateCartItem(CartItemDTO cartItemDTO, RequestDTO requestDTO) {
		
		CartItem cartItem = cartItemDao.getById(cartItemDTO.getCartItemId());

		cartItem.setCartItemId(ControllerUtils.setValue(cartItem.getCartItemId(), cartItemDTO.getCartItemId()));

		cartItem.setQuantity(ControllerUtils.setValue(cartItem.getQuantity(), cartItemDTO.getQuantity()));



        cartItem = cartItemDao.save(cartItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CartItemDTO getCartItemDTOById(Integer cartItemId) {
	
		CartItem cartItem = cartItemDao.getById(cartItemId);
			
		
		CartItemConvertCriteriaDTO convertCriteria = new CartItemConvertCriteriaDTO();
		return(this.convertCartItemToCartItemDTO(cartItem,convertCriteria));
	}







}
