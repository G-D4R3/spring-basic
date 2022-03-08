package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     @Bean memberService -> new MemoryMemberRepository()
     @Bean orderService -> new MemoryMemberRepository()
     -> 싱글톤이 깨질까? 바로 테스트를 해보는 게 낫다

    * 예상 call soutm
     call AppConfig.memberService
     call AppConfig.memberRepository
     call AppConfig.memberRepository
     call AppConfig.orderService
     call AppConfig.memberRepository

    * 실제 call soutm
     call AppConfig.memberService
     call AppConfig.memberRepository
     call AppConfig.orderService

     => @Configuration 때문에
     **/



    // 관심사의 분리
    // 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    // new MemoryMemberRepository의 중복이 제거되었다.
    // 구현체만 변경해주면 된다.
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
       // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
