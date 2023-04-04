package com.it.swim.dao;

import com.it.swim.entity.Coach;

import java.util.List;

/*
 * @description: 教练实体类dao层接口
 */
public interface CoachDao {
    /*
     * @description: 查询所有教练列表
     * @param
     * @return java.util.List<  Coach>
     */
    List<Coach> queryCoach();

    /*
     * @description: 通过coachId查询指定教练信息
     * @param coachId
     * @return   Coach
     */
      Coach queryCoachById(long coachId);

    /*
     * @description: 新增教练信息
     * @param coach
     * @return int
     */
    int addCoach(  Coach coach);

    /*
     * @description: 修改教练信息
     * @param coach
     * @return int
     */
    int modifyCoach(  Coach coach);

    /*
     * @description: 通过coachId删除指定教练
     * @param coachId
     * @return int
     */
    int deleteCoach(long coachId);
}
