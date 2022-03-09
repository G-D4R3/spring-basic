package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");
        helloLombok.setAge(12);

        System.out.println("name = " + helloLombok.getName() + ", age = " + helloLombok.getAge());

    }
}
