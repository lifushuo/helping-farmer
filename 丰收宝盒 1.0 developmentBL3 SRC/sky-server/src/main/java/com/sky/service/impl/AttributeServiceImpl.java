package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dto.SpuAttrAddDto;
import com.sky.entity.Attribute;
import com.sky.dao.AttributeDao;
import com.sky.result.Result;
import com.sky.vo.AttrListDataVO;
import com.sky.vo.AttrValueListDataItemVO;
import com.sky.vo.SpuAttrResultValueVo;
import com.sky.vo.SpuAttrResultVo;
import com.sky.service.AttributeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Attribute)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:50:34
 */
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    @Resource
    private AttributeDao attributeDao;

    @Override
    public Result insertSpuAttr(SpuAttrAddDto spuAttrAddDtou) {
        int numOfSpu = spuAttrAddDtou.getValues().length;
        for (int i = 0; i < numOfSpu; i++) {
            Attribute attribute = new Attribute();
            attribute.setSpuId(Math.toIntExact(spuAttrAddDtou.getSpuId()));
            attribute.setName(spuAttrAddDtou.getName());
            attribute.setValue(spuAttrAddDtou.getValues()[i]);
            attributeDao.insertSpuAttr(attribute);
        }

        return Result.success();

    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Attribute queryById(Integer id) {
        return this.attributeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param attribute   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Attribute> queryByPage(Attribute attribute, PageRequest pageRequest) {
        long total = this.attributeDao.count(attribute);
        return new PageImpl<>(this.attributeDao.queryAllByLimit(attribute, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Override
    public Attribute insert(Attribute attribute) {
        this.attributeDao.insert(attribute);
        return attribute;
    }

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Override
    public Attribute update(Attribute attribute) {
        //this.attributeDao.update(attribute);
        //return this.queryById(attribute.getId());
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {
        int result = this.attributeDao.deleteById(id);
        if (result != 0)
            return Result.success();
        else return Result.error("删除该属性值失败");
    }

    @Override
    public Result deleteSpuAttr(Integer spuId, String name) {
        attributeDao.deleteSpuAttr(spuId, name);
        return Result.success();
    }

    @Override
    public Result addSpecificSpuAttr(Integer spuid, String name, String value) {
        Attribute attribute = new Attribute();
        attribute.setSpuId(spuid);
        attribute.setName(name);
        attribute.setValue(value);
        if (attributeDao.insertSpuAttr(attribute) != 0)
            return Result.success();
        else return Result.error("插入一条属性失败");
    }

    @Override
    public Result querySpuAttr(Attribute attribute) {
        attributeDao.querySpuAttr(attribute);
        return Result.success();
    }

    @Override
    public SpuAttrResultVo querySpuBySpuId(Integer spuid, String name) {
        List<Attribute> attribute = attributeDao.querySpuBySpuId(spuid, name);

        List<SpuAttrResultValueVo> lists = new ArrayList<>();

        for (int i = 0; i < attribute.size(); i++) {
            SpuAttrResultValueVo s = new SpuAttrResultValueVo();
            s.setId(attribute.get(i).getId());
            s.setValue(attribute.get(i).getValue());
            lists.add(s);
        }

        SpuAttrResultVo tt = new SpuAttrResultVo();
        tt.setValues(lists);
        tt.setSpuid(String.valueOf(spuid));
        tt.setName(name);

        return tt;

//    SpuAttrResult spuAttrResult=new SpuAttrResult();
//    spuAttrResult.setSpuid(String.valueOf(attribute.getSpuId()));
//    spuAttrResult.setName(attribute.getName());
//    spuAttrResult.getValues().add(String.valueOf(attribute.getSpuId()));
//    spuAttrResult.getValues().add(attribute.getValue());
//        return spuAttrResult;
    }

    @Override
    public Boolean updateSpuAttr(Integer spuId, String oldName, String newName) {
        int update = attributeDao.updateSpuAttr(spuId, oldName, newName);
        if (update > 0)
            return true;
        else return false;
    }

    @Override
    public Result page(Integer spuId) {

        List<Attribute> list = attributeDao.selectAll(spuId);
        List<AttrListDataVO> listDataVOS = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (Attribute attribute : list) {
            String name = attribute.getName();
            boolean b = map.containsKey(name);
            if (b) {
                continue;
            }
            map.put(name, name);
            AttrListDataVO t = new AttrListDataVO();
            List<AttrValueListDataItemVO> it = new ArrayList<AttrValueListDataItemVO>();
            t.setSpuId(spuId);
            t.setName(name);
            for (Attribute i : list) {
                if (i.getName().equals(name)) {
                    AttrValueListDataItemVO sss = new AttrValueListDataItemVO();
                    sss.setId(i.getId());
                    sss.setValue(i.getValue());
                    it.add(sss);
                }
            }
            t.setValues(it);
            listDataVOS.add(t);
        }
        Map<String,Object> ii=new HashMap<String,Object>();
        ii.put("total",listDataVOS.size());
        ii.put("records",listDataVOS);

        return Result.success(ii);
    }
}
