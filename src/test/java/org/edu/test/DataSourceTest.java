package org.edu.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Junit 자바단위 테스트. DataSource 지정후 DB(Hsql,Mysql,Oracle)접속,
 * 데이터베이스 테이블에 Create,Read,Update,Delete 테스트용 클래스.
 * 제이유닛4클래스를 사용.
 * 단위테스트는 톰캣이 실행되지 않아도 작동이 되야함.
 * 그래서, 테스트 클래스 상단에 servelet-context.xml 이러한 설정파일을 불러들여서 실행이 가능하게 함.
 * @author 한미래
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourceTest {

	@Inject
	DataSource dataSource; //자바에서처럼 new 오브젝트를 생성하지 않고, 스프링에서는 @inject로 오브젝트 생성.
	
	@Test
	public void dbConnectTest() throws SQLException {
		Connection connection = dataSource.getConnection();
		
				
	}
	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("JUnit테스트 시작입니다.");
	}

}
