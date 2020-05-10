package cn.laoshengle.local.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.utils.ThreadPoolUtil;
import cn.laoshengle.local.service.LocalService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/07 20:40:57
 **/
@Controller
public class LocalController {

    private static final Logger logger = LoggerFactory.getLogger(LocalController.class);

    @Resource
    LocalService localService;

    @GetMapping("/")
    public String index() {
        logger.info("Index Local Controller");
        return "index";
    }

    /**
     * 测试接口00
     *
     * @param message GET入参
     * @return 结果字符串
     */
    @GetMapping("local/hi")
    @ResponseBody
    public String hi(@RequestParam("message") String message) {
        logger.info("{} Local Controller", message);
        return String.format("%s Local Controller", message);
    }

    /**
     * 管理员上传每日最新的精选商品Excel
     *
     * @param file 淘宝给的精选商品Excel
     * @return 接口请求成功
     */
    @PostMapping("local/uploadFeaturedByEveryDay")
    @ResponseBody
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
                localService.insertTaoBaoFeaturedByEveryDay(params);
                logger.info("[TaoBaoController].[uploadFeaturedByEveryDay]------> Asynchronous File Processing Succeeded");
            } catch (Exception e) {
                logger.error("[TaoBaoController].[uploadFeaturedByEveryDay]------> Error:", e);
            }
        });
        //返回文件上传成功
        return CommonConstant.FILE_UPLOAD_SUCCESS_TEXT;
    }
}
