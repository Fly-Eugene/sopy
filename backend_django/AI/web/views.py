# -*- coding: utf-8 -*-
import subprocess
import shlex
import os
import sys
import json
import requests

from django.shortcuts import render
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view

# tts module
from gtts import gTTS
from django.views.decorators.csrf import csrf_exempt

from EasyOCR.run import Model

# s3 관련 설정
import urllib.request
import boto3

AWS_ACCESS_KEY_ID = "AKIA2ZWH2NHW3PTUEASC"
AWS_SECRET_ACCESS_KEY = "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"
AWS_DEFAULT_REGEION = "ap-northeast-2"
AWS_BUCKET_NAME = "sopy"

client = boto3.client('s3', aws_access_key_id=AWS_ACCESS_KEY_ID,
                      aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
                      region_name=AWS_DEFAULT_REGEION)

# Create your views here.
# text 파일 저장해서, 경로 보내기 !!


def ex_change(txt, target_txt):
    idx = txt.rfind('.') + 1
    return txt[:idx] + target_txt


# 특정 파일 밑에 있는 파일들 불러오기
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


@api_view(['POST'])
def book_ocr(request):
    data = json.loads(request.body.decode('utf-8'))

    path = data['path']
    page_cnt = data['pageSize']
    print("===============================================")
    print(path, page_cnt)
    print("===============================================")
    # name = data['name']  # book123.PNG  ## 책 id

    ocr_model = Model()
    ocr_model.easyOCR(path, page_cnt)

    return Response("OK", status=status.HTTP_201_CREATED)


@csrf_exempt
@api_view(['POST'])
def tts(request):
    data = json.loads(request.body.decode('utf-8'))
    path = data['path']
    page_cnt = data['pageSize']

    # 해당 책의 txt 파일들이 모여있는 경로 저장
    txt_path = "{}/txt".format(path)
    sound_path = "{}/sound".format(path)

    # sound_path 에 파일을 있으면 안만들고, 없으면 만든다.
    os.makedirs(sound_path, exist_ok=True)

    # 해당 path 에서 file 리스트를 불러온다 ex) [0101.txt, 01012.txt, ...]
    # files, count = get_files(txt_path)

    text = ''
    for idx in range(1, int(page_cnt)+1):
        text = text_read(idx, txt_path, text)

    tts_ko = gTTS(text=text, lang='ko')
    tts_ko.save(sound_path + '/1.wav')
    # s3에 저장하는 코드
    client.upload_file(sound_path + '/1.wav',
                       "sopy", sound_path + "/1.wav")

    return JsonResponse({'result': 'OK', 'data': sound_path}, status=status.HTTP_201_CREATED)
    # return JsonResponse({'result': 'ERROR'}, status=status.HTTP_400_BAD_REQUEST)


def text_read(idx, txt_path, text):

    data = urllib.request.urlopen(
        "https://sopy.s3.ap-northeast-2.amazonaws.com/{}/{}.txt".format(txt_path, idx))

    if data:
        for line in data:
            print(line.decode('utf-8'))
            text += line.decode('utf-8')

        return text
