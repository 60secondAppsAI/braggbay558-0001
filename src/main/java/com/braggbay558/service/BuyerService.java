package com.braggbay558.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay558.domain.Buyer;
import com.braggbay558.dto.BuyerDTO;
import com.braggbay558.dto.BuyerSearchDTO;
import com.braggbay558.dto.BuyerPageDTO;
import com.braggbay558.dto.BuyerConvertCriteriaDTO;
import com.braggbay558.service.GenericService;
import com.braggbay558.dto.common.RequestDTO;
import com.braggbay558.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BuyerService extends GenericService<Buyer, Integer> {

	List<Buyer> findAll();

	ResultDTO addBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

	ResultDTO updateBuyer(BuyerDTO buyerDTO, RequestDTO requestDTO);

    Page<Buyer> getAllBuyers(Pageable pageable);

    Page<Buyer> getAllBuyers(Specification<Buyer> spec, Pageable pageable);

	ResponseEntity<BuyerPageDTO> getBuyers(BuyerSearchDTO buyerSearchDTO);
	
	List<BuyerDTO> convertBuyersToBuyerDTOs(List<Buyer> buyers, BuyerConvertCriteriaDTO convertCriteria);

	BuyerDTO getBuyerDTOById(Integer buyerId);







}





