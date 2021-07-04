package sky.main.service;

import java.util.HashMap;

public interface MainService {

	HashMap<String, Object> selectMain(HashMap<String, Object> paraMap) throws Exception;
	HashMap<String, Object> selectMain() throws Exception;
	HashMap<String, Object> selectLogin(HashMap<String, Object> paraMap) throws Exception;
	void selectLogin2(HashMap<String, Object> paraMap) throws Exception;
	
}
