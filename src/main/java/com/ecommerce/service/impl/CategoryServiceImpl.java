package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository; 
import com.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);  
	}

	@Override
	public List<Category> getAllCategory() {
	
		return categoryRepository.findAll();
	}

	@Override
	public Boolean existCategory(String name) {
	
		return categoryRepository.existsByName(name);
	}

	// delete category by id 
	 
	@Override
	public Boolean deleteCategory(int id) {
		Category category =categoryRepository.findById(id).orElse(null);
		
		if(!ObjectUtils.isEmpty(category))
		{
			categoryRepository.delete(category); 
			return true;
		}
		return false;
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = categoryRepository.findById(id).orElse(null);
		return category; 
	}

	@Override
	public List<Category> getAllActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveTrue(); 
		return categories;
	}

	@Override
	public Page<Category> getAllCategorPagination(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return categoryRepository.findAll(pageable); 
	}

	

	

}
