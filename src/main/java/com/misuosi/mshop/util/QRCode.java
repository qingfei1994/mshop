/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Description		: 二维码工具
 * <p/>
 * <br><br>Time		: 2015/5/6 22:32
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QRCode {

    private static final String CHARACTER_SET = "UTF-8";
    private static final Integer MARGIN = 1;
    private static final String FORMAT = "PNG";

    /**
     * 生成二维码
     *
     * @param contents 内容，换行可以用\n
     * @param dest     生成二维码图片地址
     * @param width    宽度
     * @param height   高度
     * @throws com.google.zxing.WriterException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void encode(String contents, String dest, int width, int height) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARACTER_SET);
        hints.put(EncodeHintType.MARGIN, MARGIN);
        BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(dest)));
    }

    /**
     * 生成二维码
     *
     * @param contents 内容，换行可以用\n
     * @param out      生成二维码图片输出流
     * @param width    宽度
     * @param height   高度
     * @throws com.google.zxing.WriterException
     * @throws java.io.IOException
     */
    public static void encode(String contents, OutputStream out, int width, int height) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARACTER_SET);
        hints.put(EncodeHintType.MARGIN, MARGIN);
        BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(matrix, FORMAT, out);
    }

    /**
     * 从一张图片解析出二维码信息
     *
     * @param dest 目标地址
     * @return String 二维码信息
     * @throws java.io.IOException
     * @throws com.google.zxing.NotFoundException
     * @throws com.google.zxing.ChecksumException
     * @throws com.google.zxing.FormatException
     */
    public static String decode(String dest) throws IOException, NotFoundException, ChecksumException, FormatException {
        QRCodeReader reader = new QRCodeReader();
        BufferedImage image = ImageIO.read(new File(dest));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
        Result result = reader.decode(imageBinaryBitmap);
        return result.getText();
    }

    public static void main(String[] args) throws WriterException, IOException, NotFoundException, ChecksumException, FormatException {
        QRCode.encode("tel:+8613800138000", "D:\\target.png", 200, 200);
        System.out.println(QRCode.decode("D:\\target.png"));
    }

}
