package cn.laoshengle.core.config;

import cn.laoshengle.core.entity.ExcelGoodEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * @description: 模板读取监听类
 * @author: 龙逸
 * @createDate: 2020/05/27 20:31:53
 **/
public class EasyExcelListener extends AnalysisEventListener<ExcelGoodEntity> {

    private static final Logger logger = LoggerFactory.getLogger(EasyExcelListener.class);

    public EasyExcelListener(List<GoodsOriginalDataEntity> params) {
        this.params = params;
    }

    /**
     * 存储数据的数据集
     */
    public List<GoodsOriginalDataEntity> params;
    GoodsOriginalDataEntity entity;

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        //捕捉到异常的处理(暂仅输出错误日志)
        logger.error("[EasyExcelListener].[onException]------> Parsing failed, but continue to parse the next line:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            logger.error("Line {}, Column {} Parsing Exception, The Data Is:{}", excelDataConvertException.getRowIndex(), excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //获取到表头信息(默认第1行)
        logger.info("[EasyExcelListener].[invokeHeadMap]------> Parsing To A Header Data:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void invoke(ExcelGoodEntity excelGoodEntity, AnalysisContext analysisContext) {
        //成功解析一条数据
        entity = new GoodsOriginalDataEntity();
        BeanUtils.copyProperties(excelGoodEntity, entity);
        entity.setGoodId(Long.parseLong(excelGoodEntity.getGoodId()));
        entity.setGoodPrice(Double.parseDouble(excelGoodEntity.getGoodPrice()));
        entity.setGoodSalesMonth(Integer.parseInt(excelGoodEntity.getGoodSalesMonth()));
        entity.setIncomeRatio(Double.parseDouble(excelGoodEntity.getIncomeRatio()));
        entity.setGoodCommission(Double.parseDouble(excelGoodEntity.getGoodCommission()));
        entity.setSellerId(Long.parseLong(excelGoodEntity.getSellerId()));
        entity.setCouponCount(Integer.parseInt(excelGoodEntity.getCouponCount()));
        entity.setCouponNum(Integer.parseInt(excelGoodEntity.getCouponNum()));

        params.add(entity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //表单解析完成的操作(暂交有其他类处理)
        logger.info("[EasyExcelListener].[doAfterAllAnalysed]------> All Data Analysis Is Complete");
    }
}
