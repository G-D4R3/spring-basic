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

public class AppConfig {

    // 관심사의 분리
    // 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.


    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    // new MemoryMemberRepository의 중복이 제거되었다.
    // 구현체만 변경해주면 된다.
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }


}
