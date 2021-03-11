package springMybatisTest;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ex01_SqlSession {
   @Autowired
   private SqlSessionFactory sqlFactory;
   
   @Before
   public void testFactory() {
      System.out.println("** SqlSessionFactory 자동주입 확인 => \n"+sqlFactory);
   }
   
   @Test
   public void testSqlSession() {
      try {
         SqlSession sqlSession = sqlFactory.openSession();
         assertNotNull(sqlSession);
         System.out.println("** sqlSession 주입 확인 => \n" + sqlSession);
      } catch (Exception e) {
         System.out.println("** testSqlSession exception => " + e.toString());
      }
   }

} //class