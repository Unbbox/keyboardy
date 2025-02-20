package com.unbox.Keyboardy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unbox.Keyboardy.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
   // 중복 닉네임 찾기
   Optional<Member> findOptionalByNickname(String nickname);
   // 중복 이메일 찾기
   Optional<Member> findByEmail(String email);

   // 아이디 검색
   Optional<Member> findByUserId(String userId);

   @Query("SELECT m.userId FROM Member m WHERE m.userId = :id")
   Optional<Member> findOptionalByUserId(String id);

   // OAuth 아이디 검색
   @Query("SELECT m.userId FROM Member m WHERE m.userId = :userId")
   Member findByOAuthUserId(String userId);
}
