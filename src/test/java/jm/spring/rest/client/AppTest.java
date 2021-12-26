package jm.spring.rest.client;

import static org.junit.Assert.assertTrue;

import jm.spring.rest.client.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testJson()
    {
        final String etalon = "5ebfebe7cb975dfcf9";
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean(Communication.class);

        String answer = communication.getCodeJmpp314();

        assertTrue( etalon.equals(answer));
        context.close();
    }

    @Test
    public void testEntity()
    {
        final String etalon = "5ebfebe7cb975dfcf9";
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean(Communication.class);

        String answer = communication.getCodeJmpp314Entity();

        assertTrue( etalon.equals(answer));
        context.close();
    }

}
