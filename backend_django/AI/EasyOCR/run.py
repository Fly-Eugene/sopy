from easyocr.easyocr import *
import uuid
import os

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
    def easyOCR(self, path, name):
        # # Using default model
        # reader = Reader(['ko'], gpu=True)

        # Using custom model
        reader = Reader(['ko'], gpu=True,
                        model_storage_directory='C:\\Users\\multicampus\\Desktop\\sopy_pjt\\S05P21B107\\backend_django\\AI\\EasyOCR\\workspace\\user_network_dir',
                        user_network_directory='C:\\Users\\multicampus\\Desktop\\sopy_pjt\\S05P21B107\\backend_django\\AI\\EasyOCR\\workspace\\user_network_dir',
                        recog_network='custom')

        # 특정 책의 이미지 저장된 경로와 txt 파일을 저장할 경로를 생성합니다.
        img_path = "{}/{}/img".format(path, name)
        save_root_path = "{}/{}/text".format(path, name)

        # txt 파일 저장할 경로로 폴더를 생성합니다.
        os.makedirs(save_root_path, exist_ok=True)

        # img 저장된 경로 파일에서 모든 img 파일들을 불러옵니다.
        files, count = get_files(img_path)

        # img 파일 안쪽에 있는 모든 사진들에 대해서
        for idx, file in enumerate(files):
            filename = os.path.basename(file)

            result = reader.readtext(file)

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
                # print('bbox: ', bbox)

            text_file.close()

        return str(name)
