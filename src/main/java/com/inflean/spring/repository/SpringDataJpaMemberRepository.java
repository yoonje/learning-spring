package com.inflean.spring.repository;

import java.util.Optional;
import com.inflean.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// 스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}