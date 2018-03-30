package com.ga.generation;

import java.util.List;

public class QuestionService {

	public static QuestionBean[] getQuestionArray(int type, String substring) {
		// TODO Auto-generated method stub
		return MySQLUtil.getQusetionBean(type,substring);
	}

	public static List<QuestionBean> getQuestionListWithOutSId(
			QuestionBean tmpQuestion) {
		// TODO Auto-generated method stub
		return MySQLUtil.getQuestionListWithOutSId(tmpQuestion);
	}

}
