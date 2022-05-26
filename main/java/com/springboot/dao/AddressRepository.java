package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Address;
import com.springboot.model.User;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	void deleteByAddIdNotInAndUser(List<Integer> addressIdList, User user);

}
