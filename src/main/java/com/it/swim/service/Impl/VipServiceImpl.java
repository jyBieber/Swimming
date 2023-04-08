package com.it.swim.service.Impl;

import com.it.swim.dao.VipDao;
import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.VipExecution;
import com.it.swim.entity.Vip;
import com.it.swim.enums.VipStateEnum;
import com.it.swim.exception.VipOperationException;
import com.it.swim.service.VipService;
import com.it.swim.util.ImageUtil;
import com.it.swim.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/*
 * @description: VipService实现类
 */
@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipDao vipDao;

    /*
     * @return java.util.List<  Vip>
     * @description: 查询全部会员信息
     */
    @Override
    public List<Vip> getVipList() {
        List<Vip> vips = vipDao.queryVip();
        return vips;
    }

    /*
     * @param vipId
     * @return   Vip
     * @description: 通过会员ID获取指定会员信息
     */
    @Override
    public Vip getVipById(long vipId) {
        return vipDao.queryVipById(vipId);
    }

    /*
     * @param vip
     * @param imageHolder
     * @return   VipExecution
     * @description: 新增会员信息
     */
    @Override
    public VipExecution addVip(Vip vip, ImageHolder imageHolder) {
        //空值判断
        if (vip == null || vip.getVipId() == null){
            return new VipExecution(VipStateEnum.EMPTY);
        }
        try {
            //添加会员信息
            int effectedNum = vipDao.addVip(vip);
            //判断是否添加成功
            if (effectedNum <= 0){
                throw new VipOperationException("添加会员信息失败");
            } else {
                //如果条件成功，判断是否需要处理图片
                if (imageHolder != null){
                    if (imageHolder.getImage() != null){
                        //存储图片
                        try {
                            //生成图片
                            addVipImg(vip,imageHolder);
                        }catch (Exception e){
                            throw new VipOperationException("addVipImg error:" + e.getMessage());
                        }
                        //更新会员头像的图片地址
                        effectedNum = vipDao.modifyVip(vip);
                        //判断是否更新成功
                        if (effectedNum <= 0){
                            throw new VipOperationException("更新图片地址失败");
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new VipOperationException("addVip error:" + e.getMessage());
        }
        return new VipExecution(VipStateEnum.SUCCESS,vip);
    }

    /*
     * @param vip
     * @param imageHolder
     * @return   VipExecution
     * @description: 修改会员信息
     */
    @Override
    public VipExecution modifyVip(Vip vip, ImageHolder imageHolder) {
        //空值判断
        if (vip == null || vip.getVipId() == null){
            return new VipExecution(VipStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Vip tempVip = vipDao.queryVipById(vip.getVipId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempVip.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempVip.getProfileImg());
                    }
                    addVipImg(vip, imageHolder);
                }
            }

            //2.修改会员信息
            //修改会员信息
            int effectedNum = vipDao.modifyVip(vip);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new VipExecution(VipStateEnum.INNER_ERROR);
            }else {
                vip = vipDao.queryVipById(vip.getVipId());
                return new VipExecution(VipStateEnum.SUCCESS,vip);
            }
        }catch (Exception e){
            throw new VipOperationException("modifyVipError:" + e.getMessage());
        }
    }

    /*
     * @param vipId
     * @return   VipExecution
     * @description: 删除指定会员信息
     */
    @Override
    public VipExecution deleteVip(long vipId) {
        // 根据vipId获取原来的图片并删除
        Vip vip = vipDao.queryVipById(vipId);
        if (vip.getProfileImg() != null){
            ImageUtil.deleteFileOrPath(vip.getProfileImg());
        }

        //删除该会员信息
        try {
            int effectedNum = vipDao.deleteVip(vipId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new VipOperationException("会员信息删除失败");
            } else {
                return new VipExecution(VipStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new VipOperationException("deleteVip error:" + e.getMessage());
        }
    }

    private void addVipImg(Vip vip, ImageHolder imageHolder) {
        //获取vip图片目录的相对值路径
        String dest = PathUtil.getVipImagePath(vip.getVipId());
        //在相应目录下生成上传的图片，并返回新生成的图片路径
        String vipImgAddr = ImageUtil.generateThumbnail(imageHolder,dest);
        //将新生成的图片路径赋值到vip的字段中
        vip.setProfileImg(vipImgAddr);
    }
}
