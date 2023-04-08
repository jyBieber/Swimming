package com.it.swim.service.Impl;


import com.it.swim.dao.CoachDao;
import com.it.swim.dao.StatisticsDao;
import com.it.swim.entity.Coach;
import com.it.swim.entity.CoachStatis;
import com.it.swim.entity.TypeStatis;
import com.it.swim.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private CoachDao coachDao;
    @Autowired
    private StatisticsDao statisticsDao;

    /*
     * @description: 营业额统计
     * @param
     * @return java.util.List< TypeStatis>
     */
    @Override
    public List<TypeStatis> turnoverStatistics() {
        return statisticsDao.turnoverStatistics();
    }

    /*
     * @description: 教练业绩统计
     * @param
     * @return java.util.List< CoachStatis>
     */
    @Override
    public List<CoachStatis> performanceStatistics() {

        List<Coach> coaches = coachDao.queryCoach();
        List<CoachStatis> coachStatis = statisticsDao.performanceStatistics();
        Map<Long, Long> existMap = coachStatis.stream().collect(Collectors.toMap(CoachStatis::getCoachId, CoachStatis::getNum));
        if (!CollectionUtils.isEmpty(coaches)) {
            coaches.forEach(x -> {
                if (existMap.get(x.getCoachId()) == null) {
                    CoachStatis coachStatis1 = new CoachStatis();
                    coachStatis1.setCoachId(x.getCoachId());
                    coachStatis1.setCoachName(x.getCoachName());
                    coachStatis1.setNum(0L);
                    coachStatis.add(coachStatis1);
                }
            });
        }


        return coachStatis;
    }
}
