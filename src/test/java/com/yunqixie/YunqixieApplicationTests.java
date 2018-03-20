package com.yunqixie;

import com.yunqixie.domain.dto.CommentDTO;
import com.yunqixie.domain.dto.TweetDTO;
import com.yunqixie.domain.dto.UserDTO;
import com.yunqixie.service.TweetService;
import com.yunqixie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

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

	@Resource
    TweetService tweetService;

	@Test
	public void userBind(){

        int lastUid = 1;
		for (int i = 0; i < 5; i++) {
			UserDTO userDAO = this.mockUserDTO();

			int uid = userService.insertUserWithUserDTO(userDAO);
			System.out.println("uid is: " + uid);
			Assert.isTrue(uid >lastUid , "insert uid failed");
			lastUid =uid;
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

	@Test
	public void tweetPublishTest(){

        int tid  = tweetService.publish(108,"HAHAHA","");
	    System.out.println("tid is: "+tid);
        Assert.isTrue(tid > 0 , "publish tweet failed");

        tid = tweetService.publish(12000,"invalid user ","");
        System.out.println("tid is: "+tid);
        Assert.isTrue(tid <= 0 , "should not publish tweet success");

    }

    @Test
    public void  commentTest(){

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setTid(1);
        commentDTO.setFrom_uid(108);
        commentDTO.setContent("不错");

        int cid = tweetService.doComment(commentDTO);
        Assert.isTrue(cid > 0 , "comment failed");
    }

    @Test
    public void delCommentTest(){

        int result = tweetService.delComment(2,1,108);
	    Assert.isTrue(result >= 0 ,"remove comment failed");

	    result = tweetService.delComment(1,1,108);
	    Assert.isTrue(result < 0,"delete comment went wrong");



    }

    @Test
    public void zanTest(){

	   int zid = tweetService.zan(1,108);
	   Assert.isTrue(zid > 0 , "zan failed");

	   zid = tweetService.unzan(1,108);
	   Assert.isTrue(zid >= 0 , "unzan failed");

	   zid = tweetService.zan(1,1234);
	   Assert.isTrue(zid < 0 , "invalid param");
    }


}
