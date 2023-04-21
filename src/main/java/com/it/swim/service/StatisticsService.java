package com.it.swim.service;

import com.it.swim.entity.CoachStatis;
import com.it.swim.entity.TypeStatis;

import java.util.List;

public interface StatisticsService {
    /*
     * @description: 营业额统计
     * @param
     * @return java.util.List< TypeStatis>
     */
    List<TypeStatis> turnoverStatistics();
    /*
     * @description: 教练业绩统计
     * @param
     * @return java.util.List< CoachStatis>
     */
    List<CoachStatis> performanceStatistics();
}
