package com.inflean.spring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import com.inflean.spring.repository.JdbcMemberRepository;
import com.inflean.spring.repository.JdbcTemplateMemberRepository;
import com.inflean.spring.repository.JpaMemberRepository;
import com.inflean.spring.repository.MemberRepository;
import com.inflean.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


//    JDBC를 위한 데이터 소스 등록
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    JPA는 EntityManager가 소스로 필요
//
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

//
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // MemberRepository 인터페이스를 통해서 구현체 변경
//
//        // return new MemoryMemberRepository();
//        // return new JdbcMemberRepository(dataSource);
//        // return new JdbcTemplateMemberRepository(dataSource);
//        // return new JpaMemberRepository(em);
//    }
}