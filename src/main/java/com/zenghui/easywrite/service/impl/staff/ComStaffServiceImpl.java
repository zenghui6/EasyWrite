package com.zenghui.easywrite.service.impl.staff;

import cn.hutool.core.util.ObjectUtil;
import com.zenghui.easywrite.common.exception.ApiAsserts;
import com.zenghui.easywrite.dto.LoginDTO;
import com.zenghui.easywrite.dto.RegisterDTO;
import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.dao.staff.ComStaffDao;
import com.zenghui.easywrite.jwt.JwtUtil;
import com.zenghui.easywrite.service.ComStaffService;
import com.zenghui.easywrite.utils.MD5Utils;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public Page<ComStaff> findAllByKeywords(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
//        Boolean activeTemp = Boolean.parseBoolean(active);
        return comStaffDao.findAccountByKeywords(keywords, pageable);
    }

    @Override
    public Page<ComStaff> findAllByKeywordsAndLevel(String keywords, String level, int page, int size, Sort.Direction direction) {
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
//        Boolean activeTemp = Boolean.parseBoolean(active);
        return comStaffDao.findAccountByKeywordsAndLevel(keywords,level, pageable);
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
     * 注册用户，分配权限为普通员工，管理员员工不提供注册接口
     * @param dto
     * @return
     */
    @Override
    public ComStaff executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        ComStaff account = comStaffDao.findComStaffByNicknameEqualsAndNameEquals(dto.getNickName(),dto.getName());
        if (!ObjectUtil.isEmpty(account)){
            ApiAsserts.fail("账号或者昵称已存在！");
        }
        ComStaff addAccount = ComStaff.builder()
                .nickname(dto.getNickName())
                .name(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .createAt(new Date())
                .id(snowflakeIdWorker.nextId())
                .createBy("user")
                .updateAt(new Date())
                .isDel(false)
                .updateBy("user")
                .level("staff")
                .build();
        comStaffDao.save(addAccount);

        return addAccount;
    }

    /**
     * 登录,并返回token
     * @param dto
     * @return
     */
    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            ComStaff account = comStaffDao.findByName(dto.getUsername());
            String level = dto.getLevel();
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if (!encodePwd.equals(account.getPassword()) || !level.equals(account.getLevel()))
            {
                throw new Exception("密码错误||等级错误");
            }
            token = JwtUtil.generateToken(String.valueOf(account.getName()),String.valueOf(account.getLevel()));
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败or等级验证错误=======>{}", dto.getUsername());
        }
        return token;
    }

    @Override
    public ComStaff getUserByUsername(String username) {
        return comStaffDao.findByName(username);
    }

    @Override
    public void updata(ComStaff comStaff) {
        comStaffDao.save(comStaff);
    }

    /**
     * 通过nickname查找某一个用户
     * @param name
     * @return
     */

    public ComStaff findOne(String name) {
        return comStaffDao.findByName(name);
    }

    /**
     * 添加用户
     * @param account
     */
    public void add(ComStaff account) {
        account.setCreateAt(new Date());
        account.setUpdateAt(new Date());
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
     * @param name
     * @return
     */
    public boolean isExistByName(String name) {
        ComStaff account = comStaffDao.findByName(name);
        if(account != null){
            return !account.getIsDel();
        }
        return false;
    }

}
