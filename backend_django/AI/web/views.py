from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
import requests
import os
import shlex
import subprocess

# Create your views here.

# text 파일 저장해서, 경로 보내기 !!


@api_view(['GET'])
def book_ocr(request):
    # txt_file = request.FILES['file']
    txt_file = './OCR_img/'

    command_line = f"python demo.py \
    --Transformation None \
    --FeatureExtraction VGG \
    --SequenceModeling BiLSTM \
    --Prediction CTC \
    --image_folder demo_image/ \
    --input_channel 1 \
    --output_channel 256 \
    --hidden_size 256 \
    --saved_model ./saved_models/None-VGG-BiLSTM-CTC-Seed1111/best_accuracy.pth"

    args = shlex.split(command_line)

    print('==================================================================')
    print(args)
    pwd = os.getcwd()
    os.chdir('./deep-text-recognition-benchmark')
    p = subprocess.Popen(args)
    # os.chdir('./web')
    # p = subprocess.Popen("python ttest.py")
    print('==================================================================')

    return Response('123456789')
