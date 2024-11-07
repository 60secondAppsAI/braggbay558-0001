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
import com.braggbay558.dao.DiscountDAO;
import com.braggbay558.domain.Discount;
import com.braggbay558.dto.DiscountDTO;
import com.braggbay558.dto.DiscountSearchDTO;
import com.braggbay558.dto.DiscountPageDTO;
import com.braggbay558.dto.DiscountConvertCriteriaDTO;
import com.braggbay558.dto.common.RequestDTO;
import com.braggbay558.dto.common.ResultDTO;
import com.braggbay558.service.DiscountService;
import com.braggbay558.util.ControllerUtils;





@Service
public class DiscountServiceImpl extends GenericServiceImpl<Discount, Integer> implements DiscountService {

    private final static Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

	@Autowired
	DiscountDAO discountDao;

	


	@Override
	public GenericDAO<Discount, Integer> getDAO() {
		return (GenericDAO<Discount, Integer>) discountDao;
	}
	
	public List<Discount> findAll () {
		List<Discount> discounts = discountDao.findAll();
		
		return discounts;	
		
	}

	public ResultDTO addDiscount(DiscountDTO discountDTO, RequestDTO requestDTO) {

		Discount discount = new Discount();

		discount.setDiscountId(discountDTO.getDiscountId());


		discount.setPercentage(discountDTO.getPercentage());


		discount.setValidTill(discountDTO.getValidTill());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		discount = discountDao.save(discount);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Discount> getAllDiscounts(Pageable pageable) {
		return discountDao.findAll(pageable);
	}

	public Page<Discount> getAllDiscounts(Specification<Discount> spec, Pageable pageable) {
		return discountDao.findAll(spec, pageable);
	}

	public ResponseEntity<DiscountPageDTO> getDiscounts(DiscountSearchDTO discountSearchDTO) {
	
			Integer discountId = discountSearchDTO.getDiscountId(); 
    			String sortBy = discountSearchDTO.getSortBy();
			String sortOrder = discountSearchDTO.getSortOrder();
			String searchQuery = discountSearchDTO.getSearchQuery();
			Integer page = discountSearchDTO.getPage();
			Integer size = discountSearchDTO.getSize();

	        Specification<Discount> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, discountId, "discountId"); 
			
			
 			

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

		Page<Discount> discounts = this.getAllDiscounts(spec, pageable);
		
		//System.out.println(String.valueOf(discounts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(discounts.getTotalPages()));
		
		List<Discount> discountsList = discounts.getContent();
		
		DiscountConvertCriteriaDTO convertCriteria = new DiscountConvertCriteriaDTO();
		List<DiscountDTO> discountDTOs = this.convertDiscountsToDiscountDTOs(discountsList,convertCriteria);
		
		DiscountPageDTO discountPageDTO = new DiscountPageDTO();
		discountPageDTO.setDiscounts(discountDTOs);
		discountPageDTO.setTotalElements(discounts.getTotalElements());
		return ResponseEntity.ok(discountPageDTO);
	}

	public List<DiscountDTO> convertDiscountsToDiscountDTOs(List<Discount> discounts, DiscountConvertCriteriaDTO convertCriteria) {
		
		List<DiscountDTO> discountDTOs = new ArrayList<DiscountDTO>();
		
		for (Discount discount : discounts) {
			discountDTOs.add(convertDiscountToDiscountDTO(discount,convertCriteria));
		}
		
		return discountDTOs;

	}
	
	public DiscountDTO convertDiscountToDiscountDTO(Discount discount, DiscountConvertCriteriaDTO convertCriteria) {
		
		DiscountDTO discountDTO = new DiscountDTO();
		
		discountDTO.setDiscountId(discount.getDiscountId());

	
		discountDTO.setPercentage(discount.getPercentage());

	
		discountDTO.setValidTill(discount.getValidTill());

	

		
		return discountDTO;
	}

	public ResultDTO updateDiscount(DiscountDTO discountDTO, RequestDTO requestDTO) {
		
		Discount discount = discountDao.getById(discountDTO.getDiscountId());

		discount.setDiscountId(ControllerUtils.setValue(discount.getDiscountId(), discountDTO.getDiscountId()));

		discount.setPercentage(ControllerUtils.setValue(discount.getPercentage(), discountDTO.getPercentage()));

		discount.setValidTill(ControllerUtils.setValue(discount.getValidTill(), discountDTO.getValidTill()));



        discount = discountDao.save(discount);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DiscountDTO getDiscountDTOById(Integer discountId) {
	
		Discount discount = discountDao.getById(discountId);
			
		
		DiscountConvertCriteriaDTO convertCriteria = new DiscountConvertCriteriaDTO();
		return(this.convertDiscountToDiscountDTO(discount,convertCriteria));
	}







}
