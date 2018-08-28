package com.zoudong.fileserver.controller.file;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.zoudong.fileserver.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.zoudong.fileserver.result.base.BaseResult;
import com.zoudong.fileserver.result.base.ResultUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * 基本文件服务器服务
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * @param file
     * @return
     * @TODO 图片上传到mongodb gridfs文件系统(还未做安全处理和缩略图处理)
     * <!DOCTYPE html>
     * <form action="http://localhost:8058/file/image/save" method="post" enctype="multipart/form-data">
     * <input type="file" name="fileName" id="fileName" accept="image/gif" />
     * <input type="Submit"  value="Submit">
     * </from>
     */
    @RequestMapping(value = "/file/image/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResult save(@RequestParam(value = "fileName", required = true) MultipartFile file) {

        log.info("开始上传文件");
        DBObject metaData = new BasicDBObject();
        metaData.put("createdDate", new Date());
        String fileName = UUID.randomUUID().toString();
        log.info("生成文件名: " + fileName);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            gridFsTemplate.store(inputStream, fileName, "image", metaData);
            log.info("文件已经保存: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传失败发生了io异常");
            throw new BusinessException("上传失败发生了io异常");
        }
        log.info("开始上传文件成功结束: ", fileName);
        return ResultUtil.fillSuccesData(fileName);
    }

    /**
     * 开始获取图片
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/file/image/get", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(@RequestParam(value = "fileName", required = true) String fileName) throws IOException {
        log.info("开始获取图片:", fileName);
        List<GridFSDBFile> result = gridFsTemplate
                .find(new Query().addCriteria(Criteria.where("filename").is(fileName)));
        if (result == null || result.size() == 0) {
            log.error("获取图片未找到" + fileName);
            throw new BusinessException("获取图片未找到: " + fileName);
        }
        log.info("获取图片成功:", fileName);
        return IOUtils.toByteArray(result.get(0).getInputStream());
    }

    /**
     * 从文件服务器删除图片
     *
     * @param fileName
     */
    @RequestMapping(value = "/file/image/delete", method = RequestMethod.DELETE)
    public BaseResult delete(@RequestParam(value = "fileName", required = true) String fileName) {
        log.info("开始删除图片", fileName);
        try {
            gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is(fileName)));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除失败未知异常");
            return ResultUtil.fillErrorMsg("unkonw_exception", "删除失败未知异常");
        }
        log.info("删除图片完成", fileName);
        return ResultUtil.fillSuccesData(fileName);
    }


}
