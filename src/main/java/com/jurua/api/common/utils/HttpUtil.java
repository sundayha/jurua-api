package com.jurua.api.common.utils;

import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class HttpUtil {

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/23 下午5:05
     * @param httpServletResponse httpServletResponse
     * @param fileName 下载文件的名字
     * @apiNote 下载文件添加header和设置内容类型并返回输出流
     * @return ServletOutputStream
     */
    public static ServletOutputStream getDownloadServletOutputStream(HttpServletResponse httpServletResponse, String fileName) throws IOException {
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return httpServletResponse.getOutputStream();
    }
}
