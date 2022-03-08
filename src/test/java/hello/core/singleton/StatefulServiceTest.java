package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);
        /**
        // ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        //System.out.println("price = " + price);

        // 사용자 A의 주문금액은 10000원이 되어야하는데 20000원이라는 결과가 나옴
        // 스프링빈을 상태공유로 설정했기 때문에
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
         **/

        Assertions.assertThat(userAPrice).isNotEqualTo(userBPrice);
    }


    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}