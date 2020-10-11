package com.inflean.spring.repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import com.inflean.spring.domain.Member;
import com.inflean.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL을 통해서 Object와 쿼리 결과 매핑
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL을 통해서 Object와 쿼리 결과 매핑
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }
}
