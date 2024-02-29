package com.it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.it.controller.dto.SsnDTO;
import com.it.repository.SsnRepository;
import com.it.repository.entity.SsnEntity;

@Service
public class SsnServiceImpl implements SsnService
{	// we don't normally expose implementation
	
	@Autowired		// service layer will interact with repository
	private SsnRepository ssnRepository;
	
	
	
	//CONTROLLER WILL EXECUTE THESE METHODS
	
	@Override		// service layer method that returns list of SsnDTO
	public List<SsnDTO> findAll()
	{
		List<SsnEntity> entityList = ssnRepository.findAll();
		List<SsnDTO> ssnDTOs = new ArrayList<SsnDTO>();
		// write logic to convert List<SsnEntity> into List<SsnDTO>
		for(SsnEntity entity: entityList)
		{
			SsnDTO dto = new SsnDTO();		// DTO object
			// copy entity attributes into DTO attribute for whole object 
			BeanUtils.copyProperties(entity, dto);	
			ssnDTOs.add(dto);
		}
		return ssnDTOs;
	}
	
	@Override		// service layer method that saves SSN entity
	public void save(SsnDTO ssnDTO)
	{
		SsnEntity entity = new SsnEntity();	// make an entity class
		// copy DTO attribute data into entity attributes
		BeanUtils.copyProperties(ssnDTO, entity);	
		ssnRepository.save(entity);
	}
	
	@Override		// service layer method that returns SSN DTO by SSN entity
	public SsnDTO findBySSN(String ssn)
	{
		Optional<SsnEntity> optional = ssnRepository.findBySsn(ssn);
		SsnDTO dto = new SsnDTO();		// DTO object
		if(optional.isEmpty())
		{
			// SSN does not exist in the database
			throw new EmptyResultDataAccessException("no result exitst in the database", 0);
		}
		// copy entity attributes into DTO attribute for whole object in optional
		BeanUtils.copyProperties(optional.get(), dto);	
		return dto;
	}
}
