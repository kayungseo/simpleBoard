package sky.main.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper(value="MainDAO")
public interface MainDAO {

	HashMap<String, Object> selectMain(HashMap<String, Object> paraMap) throws Exception;
	HashMap<String, Object> selectMain() throws Exception;
	HashMap<String, Object> selectLogin(HashMap<String, Object> paraMap) throws Exception;
	void selectLogin2(HashMap<String, Object> paraMap) throws Exception;

}
