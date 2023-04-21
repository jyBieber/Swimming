package com.it.swim.service.Impl;

import com.it.swim.dao.CoachDao;
import com.it.swim.dto.CoachExecution;
import com.it.swim.dto.ImageHolder;
import com.it.swim.entity.Coach;
import com.it.swim.enums.CoachStateEnum;
import com.it.swim.exception.CoachOperationException;
import com.it.swim.service.CoachService;
import com.it.swim.util.ImageUtil;
import com.it.swim.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/*
 * @description: CoachServiceImpl
 */
@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachDao coachDao;

    /*
     * @return java.util.List< Coach>
     * @description: 查询全部教练信息
     */
    @Override
    public List<Coach> getCoachList() {
        return coachDao.queryCoach();
    }

    /*
     * @param coachId
     * @return  Coach
     * @description: 通过教练ID获取指定教练信息
     */
    @Override
    public  Coach getCoachById(long coachId) {
        return coachDao.queryCoachById(coachId);
    }

    /*
     * @param coach
     * @return   CoachExecution
     * @description: 新增教练信息
     */
    @Override
    public CoachExecution addCoach(Coach coach, ImageHolder thumbnail) {
        //空值判断
        if (coach == null || coach.getCoachId() == null){
            return new CoachExecution(CoachStateEnum.EMPTY);
        }
        try {
            //添加教练信息
            int effectedNum = coachDao.addCoach(coach);
            if (effectedNum <= 0){
                throw new CoachOperationException("添加教练失败");
            } else {
                if (thumbnail.getImage() != null){
                    //存储图片
                    try {
                        addCoachImg(coach,thumbnail);
                    }catch (Exception e){
                        throw new CoachOperationException("addCoachImg error:" + e.getMessage());
                    }
                    //更新教练头像的图片地址
                    effectedNum = coachDao.modifyCoach(coach);
                    if (effectedNum <= 0){
                        throw new CoachOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new CoachOperationException("addCoach error:" + e.getMessage());
        }
        return new CoachExecution(CoachStateEnum.SUCCESS,coach);
    }

    /*
     * @param coach
     * @return   CoachExecution
     * @description: 修改教练信息
     */
    @Override
    public CoachExecution modifyCoach( Coach coach, ImageHolder imageHolder) {
        //空值判断
        if (coach == null || coach.getCoachId() == null){
            return new CoachExecution(CoachStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                     Coach tempCoach = coachDao.queryCoachById(coach.getCoachId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempCoach.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempCoach.getProfileImg());
                    }
                    addCoachImg(coach, imageHolder);
                }
            }

            //2.修改教练信息
            //修改教练信息
            int effectedNum = coachDao.modifyCoach(coach);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new CoachExecution(CoachStateEnum.INNER_ERROR);
            }else {
                coach = coachDao.queryCoachById(coach.getCoachId());
                return new CoachExecution(CoachStateEnum.SUCCESS,coach);
            }
        }catch (Exception e){
            throw new CoachOperationException("modifyCoachError:" + e.getMessage());
        }
    }

    /*
     * @param coachId
     * @return   CoachExecution
     * @description: 删除指定教练
     */
    @Override
    public CoachExecution deleteCoach(long coachId) {
        //根据coachId获取原来的图片并删除
         Coach coach = coachDao.queryCoachById(coachId);
        if (coach.getProfileImg() != null){
            ImageUtil.deleteFileOrPath(coach.getProfileImg());
        }

        //删除该教练信息
        try {
            int effectedNum = coachDao.deleteCoach(coachId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new CoachOperationException("教练信息删除失败");
            } else {
                return new CoachExecution(CoachStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new CoachOperationException("deleteCoach error:" + e.getMessage());
        }
    }

    private void addCoachImg( Coach coach, ImageHolder thumbnail) {
        //获取coach图片目录的相对值路径
        String dest = PathUtil.getCoachImagePath(coach.getCoachId());
        //在相应目录下生成上传的图片，并返回新生成的图片路径
        String coachImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        //将新生成的图片路径赋值到coach的字段中
        coach.setProfileImg(coachImgAddr);
    }
}
