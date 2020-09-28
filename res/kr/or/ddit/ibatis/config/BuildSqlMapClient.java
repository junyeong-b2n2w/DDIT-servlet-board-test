package kr.or.ddit.ibatis.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildSqlMapClient {
    // ibatis용 SqlMapClient객체 변수 선언
	// mapper(xml파일, 실행 sql문장을 기술 한 파일)에 접근하여 
	// sql 문장을 실행하기 위해서 필요한 객체
	private static SqlMapClient smc;
	
	static{
		try {
			// 1. iBatis 설정파일을 읽어와서 실행한다. (sqlMapConfig.xml파일)
			// 1-1. 문자 인코딩 케릭터 셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정파일 읽어오기
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후
			//		SQL문을 호출해서 실행할 수 있는 객체를 생성한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // 스트림 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return smc;
	}
	
}
