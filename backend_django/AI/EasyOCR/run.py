from easyocr.easyocr import *
import uuid
import os
import urllib.request
from PIL import Image

# s3 관련 설정 => 추후 키 숨김처리 필요
import boto3

AWS_ACCESS_KEY_ID = "AKIA2ZWH2NHW3PTUEASC"
AWS_SECRET_ACCESS_KEY = "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"
AWS_DEFAULT_REGEION = "ap-northeast-2"
AWS_BUCKET_NAME = "sopy"

client = boto3.client('s3', aws_access_key_id=AWS_ACCESS_KEY_ID,
                      aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
                      region_name=AWS_DEFAULT_REGEION)

# GPU 설정
os.environ['CUDA_VISIBLE_DEVICES'] = '0,1'


def get_files(path):
    file_list = []

    # skip hidden file
    files = [f for f in os.listdir(path) if not f.startswith('.')]
    files.sort()
    abspath = os.path.abspath(path)
    for file in files:
        file_path = os.path.join(abspath, file)
        file_list.append(file_path)

    return file_list, len(file_list)


def ex_change(txt, target_txt):
    idx = txt.rfind('.') + 1
    return txt[:idx] + target_txt


# if __name__ == '__main__':
class Model():
    def easyOCR(self, path, page_cnt):
        # # Using default model
        # reader = Reader(['ko'], gpu=True)

        # Using custom model
        reader = Reader(['ko'], gpu=True,
                        model_storage_directory=f'{os.getcwd()}/EasyOCR/workspace/user_network_dir',
                        user_network_directory=f'{os.getcwd()}/EasyOCR/workspace/user_network_dir',
                        recog_network='custom')

        # 특정 책의 이미지 저장된 경로와 txt 파일을 저장할 경로를 생성합니다.
        # img_path = "EasyOCR/workspace/demo_images/{}/img".format(path)
        image_path = path + '/img'
        save_root_path = "{}/txt".format(path)

        # txt 파일 저장할 경로로 폴더를 생성합니다.
        os.makedirs(save_root_path, exist_ok=True)

        # img 저장된 경로 파일에서 모든 img 파일들을 불러옵니다.
        # files, count = get_files(img_path)

        # img 파일 안쪽에 있는 모든 사진들에 대해서
        for idx in range(1, int(page_cnt) + 1):
            # filename = os.path.basename(file)  => 원래 코드...
            filename = f"{idx}.png"

            # result = reader.readtext(file)
            # ============= 윈도우 환경 한정 파일주소 변환 =======================
            repath = image_path.replace(":", "%3A")
            repath = repath.replace("/", "%5C")
            # ============= 배포 시 주석 처리 ===================================
            # print(
            #     "========================================================================")
            # print(
            #     "https://sopy.s3.ap-northeast-2.amazonaws.com/{}/{}.png".format(repath, idx))
            # print(
            #     "========================================================================")

            result = reader.readtext(
                "https://sopy.s3.ap-northeast-2.amazonaws.com/{}/{}.png".format(image_path, idx))

            # ./easyocr/utils.py 733 lines
            # result[0]: bbox
            # result[1]: string
            # result[2]: confidence

            # txt 파일 저장 경로에 해당 사진이름과 동일하게 txt 파일을 만들어 저장합니다.
            text_file = open(
                save_root_path + '/' + ex_change(filename, 'txt'), 'w', encoding="UTF-8")

            for (bbox, string, confidence) in result:
                print("filename: '%s', confidence: %.4f, string: '%s'" %
                      (filename, confidence, string))

                text_file.write("{}\n".format(string))

            text_file.close()

            # (현재 파일 위치, bucket 이름, bucket에 올릴 파일 이름)
            client.upload_file(save_root_path + '/' +
                               '{}.txt'.format(idx), "sopy", path + "/txt/{}.txt".format(idx))

        # return str(name)
