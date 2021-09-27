package com.ssafy.sopy.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PdfUtil {
    private final FileUtil fileUtil;

    public PdfUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public File pdfToImg(MultipartFile mf) throws IOException {
        List<String> savedImgList = new ArrayList<>(); //저장된 이미지 경로를 저장하는 List 객체
        PDDocument pdfDoc = PDDocument.load(mf.getInputStream());
        PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
        File resultImgPath = fileUtil.makeDir("/", "/" + UUID.randomUUID() + "/img/");
        //순회하며 이미지로 변환 처리
        for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
            String imgFileName = resultImgPath.getPath() + "/" + (i+1) + ".png";

            //DPI 설정
            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);

            // 이미지로 만든다.
            ImageIOUtil.writeImage(bim, imgFileName , 300);

            //저장 완료된 이미지를 list에 추가한다.
//            savedImgList.add(makeDownloadUrl4Uuid(imgFileName));
        }
        pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.
        return resultImgPath;
    }
}
