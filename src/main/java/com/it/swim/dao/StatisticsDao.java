package com.it.swim.dao;

import com.it.swim.entity.CoachStatis;
import com.it.swim.entity.TypeStatis;

import java.util.List;

/*
 * @description: 统计
 */
public interface StatisticsDao {
    /*
     * @description: 教练业绩统计
     * @param
     * @return java.util.List<CoachStatis>
     */
    List<CoachStatis> performanceStatistics();
    /*
     * @description: 营业额统计
     * @param
     * @return java.util.List<TypeStatis>
     */
    List<TypeStatis> turnoverStatistics();

}
