package com.inflean.spring.repository;

import java.util.List;
import java.util.Optional;
import com.inflean.spring.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
