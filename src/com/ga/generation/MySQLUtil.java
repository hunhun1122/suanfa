package com.ga.generation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mysql数据库操作类
 * 
 * @author liufeng
 * @date 2013-12-01
 */
public class MySQLUtil {
	/**
	 * 获取Mysql数据库连接
	 * 
	 * @return Connection
	 */
	private Connection getConn() {
		String url = "jdbc:mysql://127.0.0.1:3306/suanfa";
		String username = "root";
		String password = "123";
		Connection conn = null;
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放JDBC资源
	 * 
	 * @param conn 数据库连接
	 * @param ps
	 * @param rs 记录集
	 */
	private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 获取上一次的聊天类别
	 * 
	 * @param openId 用户的OpenID
	 * @return chatCategory
	 */
	public static int getLastCategory(String openId) {
		int chatCategory = -1;
		String sql = "select chat_category from chat_log where open_id=? order by id desc limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			if (rs.next()) {
				chatCategory = rs.getInt("chat_category");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatCategory;
	}

	/**
	 * 根据知识id随机获取一个答案
	 * 
	 * @param knowledgeId 问答知识id
	 * @return
	 */
	public static String getKnowledSub(int knowledgeId) {
		String knowledgeAnswer = "";
		String sql = "select answer from knowledge_sub where pid=? order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, knowledgeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				knowledgeAnswer = rs.getString("answer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeAnswer;
	}

	

	/**
	 * 保存聊天记录
	 * 
	 * @param openId 用户的OpenID
	 * @param createTime 消息创建时间
	 * @param reqMsg 用户上行的消息
	 * @param respMsg 公众账号回复的消息
	 * @param chatCategory 聊天类别
	 */
	public static void saveChatLog(String openId, String createTime, String reqMsg, String respMsg, int chatCategory) {
		String sql = "insert into chat_log(open_id, create_time, req_msg, resp_msg, chat_category) values(?, ?, ?, ?, ?)";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, createTime);
			ps.setString(3, reqMsg);
			ps.setString(4, respMsg);
			ps.setInt(5, chatCategory);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
	}

	public static QuestionBean[] getQusetionBean(int type, String substring) {
		
		List<QuestionBean> QuestionBeanList = new ArrayList<QuestionBean>();
		String sql = "select * from questionbean where  type=? and pointid in (?)";	
		StringBuffer sb=new StringBuffer();
		sb.append("select * from questionbean where  type=? and pointid in (");
		sb.append(substring);
		sb.append(")");
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, type);
			//ps.set(2, substring);
			rs = ps.executeQuery();
			while (rs.next()) {
				QuestionBean questionBean = new QuestionBean();
				questionBean.setId(rs.getInt("id"));
				questionBean.setType(rs.getInt("type"));
				questionBean.setDifficulty(rs.getDouble("difficulty"));
				questionBean.setPointId(rs.getLong("pointId"));
				questionBean.setStem(rs.getString("stem"));
				questionBean.setChoice1(rs.getString("choice1"));
				questionBean.setChoice2(rs.getString("choice2"));
				questionBean.setChoice3(rs.getString("choice3"));
				questionBean.setChoice4(rs.getString("choice4"));
				questionBean.setAnswer(rs.getString("answer"));
				questionBean.setUserId(rs.getInt("userId"));
				questionBean.setCreateTime(rs.getDate("createTime"));
				questionBean.setUserName(rs.getString("userName"));
				questionBean.setKnowledgeName(rs.getString("knowledgeName"));
		
			
				QuestionBeanList.add(questionBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		QuestionBean[] QuestionBeans=new QuestionBean[QuestionBeanList.size()];
		int i=0;
		for(QuestionBean q:QuestionBeanList){
			QuestionBeans[i]=q;
			i++;
		}
		//QuestionBean[] QuestionBeans = (QuestionBean[]) QuestionBeanList.toArray();
		return QuestionBeans;
	}

	public static List<QuestionBean> getQuestionListWithOutSId(
			QuestionBean tmpQuestion) {

		List<QuestionBean> QuestionBeanList = new ArrayList<QuestionBean>();
		String sql = "select * from questionbean where  type=? and pointid =? and score=?";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tmpQuestion.getType());
			ps.setLong(2, tmpQuestion.getPointId());
			ps.setDouble(3, tmpQuestion.getScore());
			rs = ps.executeQuery();
			while (rs.next()) {
				QuestionBean questionBean = new QuestionBean();
				questionBean.setId(rs.getInt("id"));
				questionBean.setType(rs.getInt("type"));
				questionBean.setDifficulty(rs.getDouble("difficulty"));
				questionBean.setPointId(rs.getLong("pointId"));
				questionBean.setStem(rs.getString("stem"));
				questionBean.setChoice1(rs.getString("choice1"));
				questionBean.setChoice2(rs.getString("choice2"));
				questionBean.setChoice3(rs.getString("choice3"));
				questionBean.setChoice4(rs.getString("choice4"));
				questionBean.setAnswer(rs.getString("answer"));
				questionBean.setUserId(rs.getInt("userId"));
				questionBean.setCreateTime(rs.getDate("createTime"));
				questionBean.setUserName(rs.getString("userName"));
				questionBean.setKnowledgeName(rs.getString("knowledgeName"));		
			
				QuestionBeanList.add(questionBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		
		return QuestionBeanList;
	}
}
