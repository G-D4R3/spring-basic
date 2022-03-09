package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        //OrderServiceImpl orderService = new OrderServiceImpl(); // 의존 관계 setter로 주입하면 여기서 NullPointerException

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy()); // 생성자를 써 순수한 자바 코드로 테스트를 작성할 수 있다.
        Order order = orderService.createOrder(1L, "item1", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        // + 생성자를 써야 final 키워드를 사용할 수 있다. (불변)
    }
}
