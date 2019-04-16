package ajax.jackson.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lei02 on 2019/4/16.
 */
public class Customer {
    private String name;
    private String id;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity(){
        return "Beijing";
    }

    @JsonIgnore
    public String getBirth(){
        return "19901212";
    }

    public static void main(String[] args) throws JsonProcessingException {
        //1.导入 jar 包
        //2.创建 ObjectMapper 对象
        ObjectMapper mapper = new ObjectMapper();
        //3.调用 mapper.writeValueAsString() 方法，把一个对象转为一个 JSON 字符串
        Customer customer = new Customer("qiqihaer", "1001");
        String jsonStr = mapper.writeValueAsString(customer);
        System.out.println(jsonStr);
        // jackson 使用 getter 方法来定位 JSON 对象的属性
        // 可以通过添加 注解 @JsonIgnore 来忽略某一个 getter 定义的属性
        //{"name":"qiqihaer","id":"1001","city":"Beijing"}
        List<Customer> customers = Arrays.asList(customer, new Customer("beijing", "1200"));
        System.out.println(mapper.writeValueAsString(customers));
        //[{"name":"qiqihaer","id":"1001","city":"Beijing"},{"name":"beijing","id":"1200","city":"Beijing"}]
    }
}
