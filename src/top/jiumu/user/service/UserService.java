package top.jiumu.user.service;

import top.jiumu.user.dao.UserDao;
import top.jiumu.user.domain.User;

/**
 * 业务逻辑层
 * 
 * @author jiumu
 * 
 */
public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * 注册功能
	 */
	public void regist(User user) throws UserException {
		/*
		 * 1.使用username查询数据库中是否存在 
		 * 2.如果返回值不为null 就抛出异常 
		 * 3.如果为null 就添加用户
		 */
		User _user = userDao.findByUsername(user.getUsername());

		if (_user != null) throw new UserException("用户名" + user.getUsername() + ", 已存在!");
		
		userDao.add(user);
	}
	
	/**
	 * 登录功能
	 */
	public User login(User user){
		User _user = userDao.findByUsername(user.getUsername());
		return _user;
	}
}
















