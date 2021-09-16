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


# if __name__ == '__main__':
class Model():
    def easyOCR(self, img_path):
        # # Using default model
        # reader = Reader(['ko'], gpu=True)

        # Using custom model
        reader = Reader(['ko'], gpu=True,
                        model_storage_directory='C:\\Users\\multicampus\\Desktop\\sopy_pjt\\S05P21B107\\backend_django\\AI\\EasyOCR\\workspace\\user_network_dir',
                        user_network_directory='C:\\Users\\multicampus\\Desktop\\sopy_pjt\\S05P21B107\\backend_django\\AI\\EasyOCR\\workspace\\user_network_dir',
                        recog_network='custom')

        # files, count = get_files('./workspace/demo_images')
        files, count = get_files(img_path)

        for idx, file in enumerate(files):
            filename = os.path.basename(file)

            result = reader.readtext(file)

            # ./easyocr/utils.py 733 lines
            # result[0]: bbox
            # result[1]: string
            # result[2]: confidence

            save_root_path = 'web/static/text/'
            file_id = uuid.uuid4()
            text_file = open(
                save_root_path + "{}".format(file_id) + '.txt', 'w', encoding="UTF-8")

            for (bbox, string, confidence) in result:
                print("filename: '%s', confidence: %.4f, string: '%s'" %
                      (filename, confidence, string))

                text_file.write("{}\n".format(string))
                # print('bbox: ', bbox)

            text_file.close()

        return str(file_id)
