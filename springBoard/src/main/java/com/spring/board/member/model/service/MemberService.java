package com.spring.board.member.model.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.member.model.dao.MemberDao;
import com.spring.board.member.model.vo.Member;

@Service("mService")
public class MemberService {
	
	@Autowired
	private MemberDao mDao;

	/**
	 *  Service
	 * @param user
	 * @return result
	 */
	public Member loginUser(Member user) {
		return mDao.loginUser(user);
	}

	public void keepLogin(String userId, String sessinId, Date limit) {
		mDao.keepLogin(userId, sessinId, limit);
	}
	
	public Member loginBySession(String sessionId) {
		return mDao.loginBySession(sessionId);
	}
}