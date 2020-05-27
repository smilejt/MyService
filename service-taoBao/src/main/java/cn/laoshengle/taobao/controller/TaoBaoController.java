package cn.laoshengle.taobao.controller;

import cn.laoshengle.core.config.EasyExcelListener;
import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.ExcelGoodEntity;
import cn.laoshengle.core.entity.GoodsCategoryEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.request.FeaturedRequestEntity;
import cn.laoshengle.core.entity.request.ListEntity;
import cn.laoshengle.core.service.taobao.TaoBaoFeaturedService;
import cn.laoshengle.core.utils.ThreadPoolUtil;
import com.alibaba.excel.EasyExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 淘宝对外接口
 * @author: 龙逸
 * @createDate: 2020/05/07 15:03:59
 **/
@RestController
@RequestMapping(value = "taoBaoService")
public class TaoBaoController {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoController.class);

    @Resource
    TaoBaoFeaturedService taoBaoFeaturedService;

    /**
     * 测试接口00
     *
     * @param message GET入参
     * @return 结果字符串
     */
    @GetMapping("hi")
    public String hi(@RequestParam("message") String message) {
        logger.info("{} TaoBao Controller", message);
        return String.format("%s TaoBao Controller", message);
    }


    /**
     * 管理员上传每日最新的精选商品Excel
     *
     * @param file 淘宝给的精选商品Excel
     * @return 接口请求成功
     */
    @PostMapping("uploadFeaturedByEveryDay")
    public JsonResult uploadFeaturedByEveryDay(@RequestParam("file") MultipartFile file) {
        logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> In");

        //处理文件
        logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]-----> Process File");
        try {
            List<GoodsOriginalDataEntity> params = new ArrayList<>();

            //使用EasyExcel读取Excel
            EasyExcel.read(file.getInputStream(), ExcelGoodEntity.class, new EasyExcelListener(params)).sheet().doRead();

            logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> File Processing Succeeded");
            ListEntity paramsList = new ListEntity();
            paramsList.setDataList(params);
            //异步请求Service
            ThreadPoolUtil.newTask(() -> {
                logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> Asynchronous Request Start");
                taoBaoFeaturedService.insertTaoBaoFeaturedByEveryDay(paramsList);
            });

            return JsonResult.buildSuccess();
        } catch (Exception e) {
            logger.error("[TaoBaoController].[uploadFeaturedByEveryDay]------> Error:", e);
            return JsonResult.buildFail();
        }
    }

    /**
     * 根据条件查询每日精选商品列表(分页)
     * 每天10点x分之前查询的是前一天的数据,10点x分之后查询的是当天导入的数据
     *
     * @param params 查询参数
     * @return 查询结果集
     */
    @PostMapping("getFeaturedByParams")
    public JsonResult getFeaturedByParams(@RequestBody FeaturedRequestEntity params) {

        logger.info("[TaoBaoController].[getFeaturedByParams]------> In Search Params = {}", params.toString());

        //判断分页参数是否存在
        if (null == params.getPageIndex() || params.getPageIndex() <= 0) {
            params.setPageIndex(CommonConstant.PAGE_INDEX);
        }

        if (null == params.getPageSize() || params.getPageSize() <= 0) {
            params.setPageSize(CommonConstant.PAGE_SIZE);
        }

        //设置处理请求时间
        params.setNewDate(new Date());

        //查询当页数据
        List<GoodsOriginalDataEntity> featuredByParams = taoBaoFeaturedService.getFeaturedByParams(params);
        //统计满足查询条件的数据条数
        Long countNum = taoBaoFeaturedService.countFeaturedByParams(params);

        return JsonResult.buildSuccess("data", featuredByParams).addObject("count", countNum);
    }

    /**
     * 查询所有分类
     *
     * @return 返回分类列表
     */
    @PostMapping("getAllCategory")
    public JsonResult getAllCategory() {
        logger.info("[TaoBaoController].[getAllCategory]------> In");

        //调用接口查询数据
        List<GoodsCategoryEntity> allCategory = taoBaoFeaturedService.getAllCategory();

        logger.info("[TaoBaoController].[getAllCategory]------> Select Result Num = {}", allCategory == null ? 0 : allCategory.size());
        return JsonResult.buildSuccess("data", allCategory);
    }

}
