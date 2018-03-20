package com.yunqixie;

import com.yunqixie.domain.dto.UserDTO;
import com.yunqixie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YunqixieApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Resource
	UserService userService;

	@Test
	public void userBind(){


		for (int i = 0; i < 20; i++) {
			UserDTO userDAO = this.mockUserDTO();

			/*
			int uid = userService.insertUserDao(userDAO.getOpenid(), userDAO.getUnionid(), userDAO.getNickname(), userDAO.getAvatar(),
					userDAO.getCountry(), userDAO.getProvince(), userDAO.getCity(), userDAO.getSex(),
					userDAO.getBirthday().toString(), "");
					*/
			int uid = userService.insertUserWithUserDTO(userDAO);
			System.out.println("uid is: " + uid);
		}

	}

    @Test
	public void userInfo(){


		for (int i = 60 ; i < 80 ; i+= 3){
			UserDTO userDAO = userService.getUserDTO(i);
			if (userDAO != null) {
				System.out.println("user info : " + userDAO.getUid() + " name: " +
						userDAO.getNickname() + " openid: " + userDAO.getOpenid());
			}else{
				System.out.println("user is null for uid ="+i);
			}

		}
	}

	@Test
	public void loginTest(){

		ArrayList<String>openids = new ArrayList<>();

		openids.add("722309200");
		openids.add("1390370180");
		openids.add("123");

		for (String openid:openids) {
			UserDTO userDAO = userService.getUserDTOWithOpenid(openid);
			if (userDAO != null){
				System.out.println("user info : " + userDAO.getUid() + " name: " +
						userDAO.getNickname() + " openid: " + userDAO.getOpenid());
			}else{
				System.out.println("user is null for openid: "+openid);
			}
		}

	}

	@Test
	public  void updateTest(){

		UserDTO userDAO = userService.getUserDTO(60);
		userDAO.setNickname("dengdengdengdeng");
		userDAO.setAvatar("http://www.good.com");

		System.out.println("nick name is: "+userDAO.getNickname() + "\n province is: "+userDAO.getProvince());

		/*
		userService.updateUser(userDAO.getUid(),userDAO.getOpenid(),userDAO.getUnionid(),
				userDAO.getNickname(),userDAO.getAvatar(),userDAO.getCountry(),userDAO.getProvince(),
				userDAO.getCity(),userDAO.getSex(),userDAO.getBirthday().toString(),userDAO.getMobile());
//*/
		userService.updateUserWithUserDTO(userDAO);

	}

	private UserDTO mockUserDTO(){

		long time = System.currentTimeMillis();
		UserDTO userDAO = new UserDTO();
		Random random = new Random(time);
		long openid = random.nextInt()*random.nextInt()*random.nextInt();
		System.out.println("openid is: "+openid);
		userDAO.setOpenid(""+openid);
		userDAO.setUnionid("123456789");
		userDAO.setNickname("TestOne"+openid);
		userDAO.setAvatar("http://www.baidu.com");
		userDAO.setCountry("中国");
		userDAO.setProvince("河北");
		userDAO.setCity("保定");
		userDAO.setSex(0);
        Timestamp birday =  new Timestamp(time);
		userDAO.setBirthday(birday);

		return userDAO;
	}
}
