package com.braggbay558.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay558.domain.ShippingDetail;
import com.braggbay558.dto.ShippingDetailDTO;
import com.braggbay558.dto.ShippingDetailSearchDTO;
import com.braggbay558.dto.ShippingDetailPageDTO;
import com.braggbay558.dto.ShippingDetailConvertCriteriaDTO;
import com.braggbay558.service.GenericService;
import com.braggbay558.dto.common.RequestDTO;
import com.braggbay558.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingDetailService extends GenericService<ShippingDetail, Integer> {

	List<ShippingDetail> findAll();

	ResultDTO addShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO);

	ResultDTO updateShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO);

    Page<ShippingDetail> getAllShippingDetails(Pageable pageable);

    Page<ShippingDetail> getAllShippingDetails(Specification<ShippingDetail> spec, Pageable pageable);

	ResponseEntity<ShippingDetailPageDTO> getShippingDetails(ShippingDetailSearchDTO shippingDetailSearchDTO);
	
	List<ShippingDetailDTO> convertShippingDetailsToShippingDetailDTOs(List<ShippingDetail> shippingDetails, ShippingDetailConvertCriteriaDTO convertCriteria);

	ShippingDetailDTO getShippingDetailDTOById(Integer shippingDetailId);







}





