package com.chengyu.core.controller.goods;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.form.GoodsImportForm;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsExportResult;
import com.chengyu.core.domain.result.GoodsResult;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsGroup;
import com.chengyu.core.model.PmsGoodsSku;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.util.third.manager.ThirdManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @title  商品
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "商品")
@Controller
@RequestMapping("/system")
public class GoodsController extends AdminBaseController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ThirdManager thirdManager;
	@Autowired
	private ThirdConfigService thirdConfigService;
	
	@ApiOperation(value = "店铺商品列表")
	@ResponseBody
	@RequestMapping(value="/goods/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> getList(GoodsSearchForm form,
													   @RequestParam(value = "page", defaultValue = "1") Integer page,
													   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "修改排序")
	@ResponseBody
	@RequestMapping(value="/goods/updateSort", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(Integer id, Integer sort) throws ServiceException {
		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setId(id);
		updateGoods.setSort(sort);
		goodsService.updateGoods(updateGoods);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "批量上架")
	@ResponseBody
	@RequestMapping(value="/goods/shangjia", method=RequestMethod.POST)
	public CommonResult<String> shangjia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.shangjiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "批量下架")
	@ResponseBody
	@RequestMapping(value="/goods/xiajia", method=RequestMethod.POST)
	public CommonResult<String> xiajia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.xiajiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除商品")
	@ResponseBody
	@RequestMapping(value="/goods/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.deleteGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "商品详情")
	@ResponseBody
	@RequestMapping(value="/goods/get", method=RequestMethod.GET)
	public CommonResult<GoodsResult> get(Integer goodsId) {
		GoodsResult goods = goodsService.getGoodsResult(goodsId);
		return CommonResult.success(goods);
	}

	@ApiOperation(value = "商品库列表")
	@ResponseBody
	@RequestMapping(value="/goods/getListByPlatform", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> getListByPlatform(GoodsSearchForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		form.setQueryPlatformGoods(true);
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加编辑商品")
	@ResponseBody
	@RequestMapping(value="/goods/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(GoodsPublishForm publishForm, String groupJson) throws ServiceException {
		if(com.chengyu.core.utils.StringUtils.isNotBlank(groupJson)){
			publishForm.setGroupList(JSONArray.parseArray(groupJson, PmsGoodsGroup.class));
		}
		UmsShop shop = new UmsShop();
		shop.setId(0);
		shop.setName("商品库");
		goodsService.publishGoods(shop, publishForm);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "商品Sku列表")
	@ResponseBody
	@RequestMapping(value = "/goods/getSkuList", method = RequestMethod.GET)
	public CommonResult<List<PmsGoodsSku>> getSkuList(Integer goodsId) {
		List<PmsGoodsSku> list = goodsService.getGoodsSkuList(goodsId).getSkuList();
		return CommonResult.success(list);
	}


	@ApiOperation(value = "导出商品列表")
	@ResponseBody
	@RequestMapping(value="/goods/getExportList", method=RequestMethod.GET)
	public CommonResult<List<GoodsExportResult>> getExportList(GoodsSearchForm form) {
		form.setQueryPlatformGoods(true);
		List<GoodsExportResult> list = goodsService.getExportList(form, 1, 100000);
		return CommonResult.success(list);
	}


	@ApiOperation(value = "导入商品")
	@ResponseBody
	@RequestMapping(value = {"/goods/import"}, method = RequestMethod.POST)
	public CommonResult<String> importPressNeed2(MultipartFile file) throws Exception {
		UmsShop shop = new UmsShop();
		shop.setId(0);
		shop.setName("商品库");
		List<GoodsImportForm> goodsList = uploadExcel(file);
		goodsService.importGoods(shop, 0, goodsList);
		return CommonResult.success(null);
	}

	public List<GoodsImportForm> uploadExcel(MultipartFile file) throws Exception {
		if (file == null) {
			throw new ServiceException("文件不存在");
		}
		String filename = file.getOriginalFilename();
		if (filename == null) {
			throw new ServiceException("文件不存在");
		}
		try {
			// 调用解析文件方法
			return parseRowCell(filename, file.getInputStream());
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
	/**
	 * 解析文件中的数据
	 */
	private List<GoodsImportForm> parseRowCell(String filename, InputStream is) throws ServiceException {
		List<GoodsImportForm> goodsList = new ArrayList<>();
		try {
			Workbook workbook = null;
			// 判断excel的后缀，不同的后缀用不同的对象去解析
			// xls是低版本的Excel文件
			if (filename.endsWith(".xls")) {
				workbook = new XSSFWorkbook(is);
			}
			// xlsx是高版本的Excel文件
			if (filename.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(is);
			}
			if (workbook == null) {
				throw new Exception();
			}

			// 取到excel 中的第一张工作表
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
				throw new Exception();
			}

			// 工作表中第一行是表头，不获取，从第二行开始获取
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				// 获取到这一行的数据
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}

				/**
				 * 以下的取数据，具体看你的Excel文件中有多少列数据，以此类推
				 */
				GoodsImportForm goods = new GoodsImportForm();
				//商品类目
				if (row.getCell(0) != null) {
					if(com.chengyu.core.utils.StringUtils.isEmpty(row.getCell(0).getStringCellValue())){
						continue;
					}
					goods.setCateName(row.getCell(0).getStringCellValue().trim());
				}
				//标题
				if (row.getCell(1) != null) {
					if(com.chengyu.core.utils.StringUtils.isEmpty(row.getCell(1).getStringCellValue())){
						continue;
					}
					goods.setTitle(row.getCell(1).getStringCellValue().trim());
				}
				//商品主图
				if (row.getCell(2) != null) {
					if(com.chengyu.core.utils.StringUtils.isNotEmpty(row.getCell(2).getStringCellValue())){
						goods.setMainImg(row.getCell(2).getStringCellValue().trim());
					}
				}
				//商品相册
				if (row.getCell(3) != null) {
					if(com.chengyu.core.utils.StringUtils.isNotEmpty(row.getCell(3).getStringCellValue())){
						goods.setGoodsImg(row.getCell(3).getStringCellValue().trim());
					}
				}
				//价格
				if (row.getCell(4) != null) {
					row.getCell(4).setCellType(CellType.STRING);
					if(com.chengyu.core.utils.StringUtils.isNotEmpty(row.getCell(4).getStringCellValue())){
						goods.setPrices(row.getCell(4).getStringCellValue().trim());
						goods.setPrice(new BigDecimal(goods.getPrices()));
					}
				}
				//商品描述
				if (row.getCell(5) != null) {
					if(com.chengyu.core.utils.StringUtils.isNotEmpty(row.getCell(5).getStringCellValue())){
						goods.setDescription(row.getCell(5).getStringCellValue().trim());
					}
				}
				goods.setAddTime(DateUtil.date());
				goodsList.add(goods);
			}
			return goodsList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("导入失败");
		}
	}


	@ApiOperation(value = "识别第三方商品详情")
	@ResponseBody
	@RequestMapping(value="/goods/getThirdDetail", method=RequestMethod.GET)
	public CommonResult<GoodsThirdResult> getThirdDetail(String url) throws ServiceException {
		GoodsThirdResult goods = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.GOODS.getKey())).getGoodsDetail(url);
		return CommonResult.success(goods);
	}
}