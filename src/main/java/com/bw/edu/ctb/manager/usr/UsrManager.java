package com.bw.edu.ctb.manager.usr;

import com.bw.edu.ctb.common.qo.usr.TUsrQO;
import com.bw.edu.ctb.dao.entity.usr.AURel;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dao.entity.usr.Login;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import com.bw.edu.ctb.dao.mapper.UarelMapper;
import com.bw.edu.ctb.dao.mapper.UbsrMapper;
import com.bw.edu.ctb.dao.mapper.UlgMapper;
import com.bw.edu.ctb.dao.mapper.UtsrMapper;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@Service
public class UsrManager {
    @Autowired
    private UbsrMapper ubsrMapper;
    @Autowired
    private UarelMapper uarelMapper;
    @Autowired
    private UtsrMapper utsrMapper;
    @Autowired
    private UlgMapper ulgMapper;

    public BUsr getById(Long id){
        return ubsrMapper.getByUid(id);
    }

    public void updateToken(BUsr bUsr){
        int rs = ubsrMapper.updateToken(bUsr.getId(), bUsr.getToken(), bUsr.getExpire());
        if(rs<1){
            promoteException(CtbExceptionEnum.DB_WRITE_ERROR);
        }
    }

    public void createBusr(BUsr bUsr){
        int rs = ubsrMapper.save(bUsr);
        if(rs < 1){
            promoteException(CtbExceptionEnum.DB_WRITE_ERROR);
        }
    }
    public void createTusr(TUsr tUsr){
        int rs = utsrMapper.save(tUsr);
        if(rs < 1){
            promoteException(CtbExceptionEnum.DB_WRITE_ERROR);
        }
    }
    public void createAurel(AURel auRel){
        int rs = uarelMapper.save(auRel);
        if(rs < 1){
            promoteException(CtbExceptionEnum.DB_WRITE_ERROR);
        }
    }

    /**
     * 根据nick查询第三方注册用户
     * @param nick
     * @param type
     * @return
     */
    public List<TUsr> queryTusrByNick(String nick, Integer type){
        TUsrQO q = new TUsrQO();
        q.setNick(nick);
        q.setType(type);
        return utsrMapper.select(q);
    }

    public void createLogin(Login login){
        int rs = ulgMapper.save(login);
        if(rs < 1){
            promoteException(CtbExceptionEnum.DB_WRITE_ERROR);
        }
    }
}
