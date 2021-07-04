package sky.border.serviceImpl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import sky.border.dao.BorderDAO;
import sky.border.service.BorderService;

@Service("BorderService")
public class BorderServiceImpl extends EgovAbstractServiceImpl implements BorderService	{
	@Resource(name="BorderDAO")BorderDAO borderDAO;//DAO 사용하기 위해서 
	@Override
	public void insertBorder(HashMap<String, Object> paramMap) throws Exception{
		borderDAO.insertBorder(paramMap);
	}
	
	
}
