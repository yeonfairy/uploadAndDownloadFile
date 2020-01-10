package com.spring.board.member.model.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.member.model.vo.Member;

@Repository("mDao")
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * DAO
	 * @param user
	 * @return result
	 */
	public Member loginUser(Member user) {
		return sqlSession.selectOne("memberMapper.loginUser", user);
	}
	
	public void keepLogin(String userId, String sessionId, Date limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("sessionId", sessionId);
		map.put("sessionLimit", limit);
		
		sqlSession.update("memberMapper.keepLogin",map);
	}
	
	public Member loginBySession(String sessionId) {
		return sqlSession.selectOne("memberMapper.loginBySession", sessionId);
	}
	
}