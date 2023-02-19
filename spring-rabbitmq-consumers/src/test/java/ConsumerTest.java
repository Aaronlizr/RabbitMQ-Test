import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName:ConsumerTest
 * @Auther: lizhr
 * @Description: 测试
 * @Date: 2023/2/19 18:26
 * @Version: v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-rabbitmq-consumer.xml")
public class ConsumerTest {

    @Test
    public void test(){
        while (true){

        }
    }
}
