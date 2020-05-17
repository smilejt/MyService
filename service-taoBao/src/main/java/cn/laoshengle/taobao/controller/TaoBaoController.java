package cn.laoshengle.taobao.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.GoodsCategoryEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.request.FeaturedRequestEntity;
import cn.laoshengle.core.entity.request.ListEntity;
import cn.laoshengle.core.service.taobao.TaoBaoFeaturedService;
import cn.laoshengle.core.utils.ThreadPoolUtil;
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
    public String uploadFeaturedByEveryDay(@RequestParam("file") MultipartFile file) {
        logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> In");

        //异步启用线程处理文件
        ThreadPoolUtil.newTask(() -> {
            logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]-----> Asynchronous Process File");
            try {
                List<GoodsOriginalDataEntity> params = new ArrayList<>();
                //读取上传的Excel文件
                HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
                //获取一共有多少sheet，然后遍历
                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    HSSFSheet sheet = workbook.getSheetAt(i);
                    //获取sheet中一共有多少行，遍历行
                    int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                    //每行为一个精选实体对象
                    GoodsOriginalDataEntity dataEntity;
                    for (int j = 0; j < physicalNumberOfRows; j++) {
                        if (j == 0) {
                            continue;//标题行
                        }
                        //获取每一行,并存入对象
                        dataEntity = new GoodsOriginalDataEntity();
                        HSSFRow row = sheet.getRow(j);
                        //商品ID
                        dataEntity.setGoodId(Long.parseLong(row.getCell(0).getStringCellValue()));
                        //商品名称
                        dataEntity.setGoodName(row.getCell(1).getStringCellValue());
                        //商品主图URL
                        dataEntity.setGoodPicUrl(row.getCell(2).getStringCellValue());
                        //商品详细URL
                        dataEntity.setGoodDetailedUrl(row.getCell(3).getStringCellValue());
                        //商品一级类目
                        dataEntity.setGoodCategory(row.getCell(4).getStringCellValue());
                        //商品淘宝客链接
                        dataEntity.setTaoBaoKeUrl(row.getCell(5).getStringCellValue());
                        //商品价格
                        dataEntity.setGoodPrice(Double.parseDouble(row.getCell(6).getStringCellValue()));
                        //商品月销量
                        dataEntity.setGoodSalesMonth(Integer.parseInt(row.getCell(7).getStringCellValue()));
                        //收入比例
                        dataEntity.setIncomeRatio(Double.parseDouble(row.getCell(8).getStringCellValue()));
                        //商品佣金
                        dataEntity.setGoodCommission(Double.parseDouble(row.getCell(9).getStringCellValue()));
                        //卖家旺旺名称
                        dataEntity.setSellerWangName(row.getCell(10).getStringCellValue());
                        //卖家ID
                        dataEntity.setSellerId(Long.parseLong(row.getCell(11).getStringCellValue()));
                        //店铺名称
                        dataEntity.setShopName(row.getCell(12).getStringCellValue());
                        //平台类型
                        dataEntity.setPlatformType(row.getCell(13).getStringCellValue());
                        //优惠券ID
                        dataEntity.setCouponId(row.getCell(14).getStringCellValue());
                        //优惠券总量
                        dataEntity.setCouponCount(Integer.parseInt(row.getCell(15).getStringCellValue()));
                        //优惠券剩余
                        dataEntity.setCouponNum(Integer.parseInt(row.getCell(16).getStringCellValue()));
                        //优惠券额度
                        dataEntity.setCouponQuota(row.getCell(17).getStringCellValue());
                        //优惠券开始时间
                        dataEntity.setCouponStartTime(row.getCell(18).getStringCellValue());
                        //优惠券结束时间
                        dataEntity.setCouponEndTime(row.getCell(19).getStringCellValue());
                        //优惠券URL
                        dataEntity.setCouponUrl(row.getCell(20).getStringCellValue());
                        //商品优惠券推广URL
                        dataEntity.setGoodCouponPromoteUrl(row.getCell(21).getStringCellValue());
                        //数据写入List
                        params.add(dataEntity);
                    }
                }
                ListEntity paramsList = new ListEntity();
                paramsList.setDataList(params);
                taoBaoFeaturedService.insertTaoBaoFeaturedByEveryDay(paramsList);
                logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> Asynchronous File Processing Succeeded");
            } catch (Exception e) {
                logger.error("[TaoBaoController].[uploadFeaturedByEveryDay]------> Error:", e);
            }
        });
        //返回文件上传成功
        return CommonConstant.FILE_UPLOAD_SUCCESS_TEXT;
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
