package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configuratioNTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); //getRepository를 하기 위해 구체 클래스 언급
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class); // 원래 구체 클래스를 꺼내는 것 자체가 좋지 않음

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("MemberService -> memberRepository1 = " + memberRepository1);
        System.out.println("OrderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // 세번 new MemberRepository가 호출되어 세개의 인스턴스가 생성되었다고 보이지만 아님
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass()); // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$b61fb99a
        // 순수한 클래스라면 class hello.core.AppConfig 라고 떠야함
        // 내가 만든 AppConfig가 아니고 AppConfig라는 이름으로 AppConfig@CGLIB이 올라가 있음
    }
}
