package com.geosolution.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geosolution.app.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{
	
	public List<Store> findByName(String shopName);

}
