package com.it.swim.service;

import com.it.swim.dto.CoachExecution;
import com.it.swim.dto.ImageHolder;
import com.it.swim.entity.Coach;

import java.util.List;

public interface CoachService {
    /*
     * @description: 查询全部教练信息
     * @param 
     * @return java.util.List< Coach>
     */
    List<Coach> getCoachList();
    
    /*
     * @description: 通过教练ID获取指定教练信息
     * @param coachId
     * @return  Coach
     */
     Coach getCoachById(long coachId);

    /*
     * @description: 新增教练信息
     * @param coach
     * @param thumbnail
     * @return  CoachExecution
     */
    CoachExecution addCoach( Coach coach, ImageHolder thumbnail);

    /*
     * @description: 修改教练信息
     * @param coach
     * @param thumbnail
     * @return  CoachExecution
     */
    CoachExecution modifyCoach(Coach coach, ImageHolder thumbnail);
    
    /*
     * @description: 删除指定教练信息
     * @param coachId
     * @return  CoachExecution
     */
    CoachExecution deleteCoach(long coachId);
}
