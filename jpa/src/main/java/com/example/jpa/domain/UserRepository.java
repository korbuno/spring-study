package com.example.jpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	// 주의점 ) 메소드명에 따라 동작규칙이 다름
	// findByUserId 햇다가 실패한 이유 => userId 프로퍼티를 못찾는다고 함
	User findByUserID(String userID);
}
