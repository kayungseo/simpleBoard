package sky.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import sky.main.dao.MainDAO;
import sky.main.service.MainService;

@Service("MainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	@Resource(name="MainDAO")MainDAO mainDAO;
	
	@Override
	public HashMap<String, Object> selectMain(HashMap<String, Object> paraMap) throws Exception {
		System.out.println("진입성공");
		System.out.println("paraMap : " + paraMap);
		return mainDAO.selectMain(paraMap);
	}
	
	@Override
	public HashMap<String, Object> selectMain() throws Exception {
		System.out.println("진입성공2");
		return mainDAO.selectMain();
	}
	
	@Override
	public HashMap<String, Object> selectLogin(HashMap<String, Object> paraMap) throws Exception {
		System.out.println("mainDAO.selectLogin 진입");
		return mainDAO.selectLogin(paraMap);
	}
	
	@Override
	public void selectLogin2(HashMap<String, Object> paraMap) throws Exception{
		mainDAO.selectLogin2(paraMap);
	}
}
