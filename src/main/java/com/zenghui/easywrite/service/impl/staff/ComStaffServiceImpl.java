package com.zenghui.easywrite.service.impl.staff;

import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.dao.staff.ComStaffDao;
import com.zenghui.easywrite.service.ComStaffService;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 员工信息等级表(ComStaff)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
@Service("comStaffService")
public class ComStaffServiceImpl implements ComStaffService {
    @Resource
    private ComStaffDao comStaffDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private HttpSession session;

    /**
     * 查找未被删除的
     * @param page
     * @param size
     * @return
     */
    public Page<ComStaff> findAllExist(int page, int size) {
        page--;
        Pageable pageable = PageRequest.of(page, size);
        return comStaffDao.findAllExistAccount(pageable);
    }

    /**
     * 查找已被软删除的
     * @param page
     * @param size
     * @return
     */
    public Page<ComStaff> findAllDeleted(int page,int size){
        page--;
        Pageable pageable=PageRequest.of(page,size);
        return comStaffDao.findAllDeletedAccount(pageable);
    }

    /**
     * 分页返回标题或内容包含某关键字的视频条目（员工使用），当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param page
     * @param size
     * @param direction
     * @return
     */
    public Page<ComStaff> findAllByKeywords(String keywords, String active, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        Boolean activeTemp = Boolean.parseBoolean(active);
        return comStaffDao.findAccountByKeywords(keywords, activeTemp, pageable);
    }

    /**
     * 通过id查找某一个用户
     * @param id
     * @return
     */
    public ComStaff findOneById(String id) {
        return comStaffDao.findNicknameById(id);
    }

    /**
     * 通过nickname查找某一个用户
     * @param nickname
     * @return
     */

    public ComStaff findOne(String nickname) {
        return comStaffDao.findByNickname(nickname);
    }

    /**
     * 添加用户
     * @param account
     */
    public void add(ComStaff account) {
        account.setCreateAt(new Date());
        account.setUpdateAt(new Date());
        account.setPassword("123456");
        account.setId(snowflakeIdWorker.nextId());
        account.setCreateBy((String) session.getAttribute("user"));
        account.setUpdateBy((String) session.getAttribute("user"));
        account.setIsDel(false);
        comStaffDao.save(account);
    }

    /**
     * 软删除或恢复用户
     * @param ids
     */
    public void modify(String ids, String active) {
        String[] deleteNicknames = ids.split(",");
        Boolean activeTemp = Boolean.parseBoolean(active);
        for (String deleteNickname : deleteNicknames) {
            comStaffDao.deleteByNickname(activeTemp, deleteNickname, new Date(), (String) session.getAttribute("user"));
        }
    }

    /**
     * 更新用户
     * @param account
     */
    public void update(ComStaff account) {
        ComStaff accountTemp = this.findOneById(account.getId());
        BeanUtils.copyProperties(account, accountTemp);
        accountTemp.setUpdateAt(new Date());
        accountTemp.setUpdateBy((String) session.getAttribute("user"));
        comStaffDao.saveAndFlush(accountTemp);
    }

    /**
     * 判断用户是否存在，用户不存在，直接返回，用户存在，进入下一步密码判断
     * @param nickname
     * @return
     */
    public boolean isExistByNickname(String nickname) {
        ComStaff account = comStaffDao.findByNickname(nickname);
        if(account != null){
            return !account.getIsDel();
        }
        return false;
    }

}
