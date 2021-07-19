package com.zhiyu.service.impl;


import com.zhiyu.entity.pojo.dictionary.ColumnDomain;
import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.entity.vo.DictionaryVo;
import com.zhiyu.repository.ColumnDomainRepository;
import com.zhiyu.repository.SystemPermissionRepository;
import com.zhiyu.repository.jpa.BaseNativeSql;
import com.zhiyu.service.DictionaryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2019/11/12
 */
@Service
public class DictionaryServiceImpl extends BaseNativeSql implements DictionaryService {
    @Resource
    ColumnDomainRepository columnDomainRepository;

    @Autowired
    private SystemPermissionRepository systemPermissionRepository;

    @Override
    public Map<String, List<DictionaryVo>> loadKevValueManage(String selectName) {
        List<ColumnDomain> cdList;
        if (StringUtils.isBlank(selectName)) {
            cdList = columnDomainRepository.findAll();
        } else {
            cdList = columnDomainRepository.findAllBySelectName(selectName);
        }
        Map<String, List<DictionaryVo>> linkHashMap = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(cdList)) {
            for (ColumnDomain cd : cdList) {
                List<DictionaryVo> list = new ArrayList<>(16);
                String tableName = cd.getTableName();
                if (StringUtils.isBlank(tableName)) {
                    continue;
                }
                String keyColumn = StringUtils.trim(cd.getKeyColumn());
                String valueColumn = StringUtils.trim(cd.getValueColumn());
                String conditionColumn = StringUtils.trim(cd.getConditionColumn());
                String sql = "select " + keyColumn + ", " + valueColumn;
                sql = sql +
                        "  from  " + tableName +
                        "   where 1=1";
                StringBuilder sb = new StringBuilder();
                if (StringUtils.isNotBlank(conditionColumn)) {
                    sb.append("  and  ").append(conditionColumn);
                }
                List<Object[]> listArr = sqlArrayList(sql + sb.toString());
                for (Object[] objArr : listArr) {
                    DictionaryVo d = new DictionaryVo();
                    for (int j = 0; j < objArr.length; j++) {
                        switch (j) {
                            case 0:
                                d.setKey(objArr[j].toString());
                                break;
                            case 1:
                                d.setValue(objArr[j].toString());
                                break;
                            default:
                                break;
                        }
                    }
                    list.add(d);
                }
                linkHashMap.put(cd.getSelectName(), list);
            }
        }
        return linkHashMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public void permissionSave() {
        SystemPermission systemPermission = new SystemPermission();
        systemPermission.setMenuId(55L);
        systemPermission.setDescription("权限1");
        systemPermissionRepository.save(systemPermission);
        //throw new BusinessException("1");
    }
}
