package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    public static SingletonService getInstance() { // static 영역에 객체 instance를 미리 하나 생성해서 올려둔다. 따라서 이 객체가 필요하면 getInstance로만 조회할 수 있다.
        return instance;
    }

    private SingletonService() { // 새로 생성하지 못함
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {
        SingletonService singletonService1 = new SingletonService();
        SingletonService singletonService2 = new SingletonService();
    }
}
