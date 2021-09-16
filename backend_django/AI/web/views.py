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

    # "EasyOCR/workspace/demo_images"  => 성공  ## 데이터 저장 장소
    path = data['path']
    name = data['name']  # book123.PNG  ## 책 id

    ocr_model = Model()
    ocr_model.easyOCR(path, name)

    return Response("OK", status=status.HTTP_201_CREATED)


@csrf_exempt
@api_view(['POST'])
def tts(request):
    data = json.loads(request.body.decode('utf-8'))

    path = data['path']  # web/static/text/ => 추후엔 책 이름까지 있는 파일 경로 ??
    name = data['name']  # 아니면 이게 책 이름?

    # 해당 책의 txt 파일들이 모여있는 경로 저장
    txt_path = "{}/{}/text".format(path, name)
    sound_path = "{}/{}/sound".format(path, name)

    # sound_path 에 파일을 있으면 안만들고, 없으면 만든다.
    os.makedirs(sound_path, exist_ok=True)

    # 해당 path 에서 file 리스트를 불러온다 ex) [0101.txt, 01012.txt, ...]
    files, count = get_files(txt_path)

    for idx, file in enumerate(files):
        filename = os.path.basename(file)
        txt = open(txt_path + '/' + filename, 'rt', encoding='UTF8')

        if txt:
            text = ''
            for line in txt.readlines():
                text += line
            tts_ko = gTTS(text=text, lang='ko')
            tts_ko.save(sound_path + '/' + ex_change(filename, 'mp3'))

        return JsonResponse({'result': 'OK', 'data': sound_path}, status=status.HTTP_201_CREATED)
    return JsonResponse({'result': 'ERROR'}, status=status.HTTP_400_BAD_REQUEST)
