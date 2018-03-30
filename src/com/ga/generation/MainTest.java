package com.ga.generation;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 *
 * @author lixiaolin
 * @createDate 2016-06-22 20:23
 */
public class MainTest {
    public static void main(String[] args) {
        Paper resultPaper = null;
        // 迭代计数器
        int count = 0;
        int runCount = 100;
        // 适应度期望值
        double expand = 0.98;
        // 可自己初始化组卷规则rule
        RuleBean rule = new RuleBean();
        rule.setSingleNum(2);//单选
        rule.setSingleScore(2);
        rule.setCompleteNum(1);//填空
        rule.setCompleteScore(3);
        rule.setSubjectiveNum(1);//主观题
        rule.setSubjectiveScore(3);
        rule.setTotalMark(10);//总分
        rule.setDifficulty(0.6);
        
        rule.setPointIds("2#1");
        if (rule != null) {
            // 初始化种群
            Population population = new Population(20, true, rule);
            System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
            while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
                count++;
                population = GA.evolvePopulation(population, rule);
                System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
            }
            System.out.println("进化次数： " + count);
            System.out.println(population.getFitness().getAdaptationDegree());
            resultPaper = population.getFitness();
        }
        System.out.println(resultPaper.getQuestionList());
    }
}
