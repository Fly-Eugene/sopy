package com.ssafy.sopy.util;

import com.ssafy.sopy.service.AwsS3UploadService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class PdfUtil {
    private final FileUtil fileUtil;
    private final AwsS3UploadService awsS3UploadService;

    public PdfUtil(FileUtil fileUtil, AwsS3UploadService awsS3UploadService) {
        this.fileUtil = fileUtil;
        this.awsS3UploadService = awsS3UploadService;
    }

    public File pdfToImg(MultipartFile mf) throws IOException {
        PDDocument pdfDoc = PDDocument.load(mf.getInputStream());
        PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
        File resultImgPath = fileUtil.makeDir("/", "/" + UUID.randomUUID() + "/img/");
        //순회하며 이미지로 변환 처리
        for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
            StringBuilder fileName = new StringBuilder();
            fileName.append(i+1).append(".png");
            String imgFileName = resultImgPath.getPath() + "/" + fileName;
            System.out.println("imgFileName = " + imgFileName);
            //DPI 설정
            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
            // 이미지로 만든다.
            ImageIOUtil.writeImage(bim, imgFileName , 300);

            awsS3UploadService.uploadFile(resultImgPath.getPath(), fileName.toString(), new File(imgFileName));
            // 파일 url
            System.out.println("save path = " + awsS3UploadService.getFileUrl(resultImgPath.getPath(), fileName.toString()));
            // 해당 경로 파일 확인
            System.out.println("resultImgPath = " + resultImgPath.getPath() + fileName.toString());

            // 서버 임시저장 안되면 InputStream 만들어서 저장, 그러면 metadata 문제 처리해줘야함
//            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
//            ByteOutputStream bos = new ByteOutputStream();
//            ImageIO.write(bim, "jpeg", bos);
//            InputStream is = new ByteArrayInputStream(bos.toString().getBytes(StandardCharsets.UTF_8));
//            ImageIOUtil.writeImage(bim, imgFileName , 300);
        }
        pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.

        return resultImgPath;
    }
}
