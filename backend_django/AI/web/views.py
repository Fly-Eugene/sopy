# -*- coding: utf-8 -*-
import subprocess
import shlex
import os
import requests
from rest_framework.response import Response
from rest_framework.decorators import api_view
from django.shortcuts import render
from gtts import gTTS
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

from EasyOCR.run import Model
import sys
sys.path.append("..")


# tts module

# Create your views here.

# text 파일 저장해서, 경로 보내기 !!


@api_view(['GET'])
def book_ocr(request):
    ocr_model = Model()

    # 이미지 링크는 추후 변경할 예정
    file_id = ocr_model.easyOCR(
        'C:\\Users\\multicampus\\Desktop\\sopy_pjt\\S05P21B107\\backend_django\\AI\\EasyOCR\\workspace\\demo_images')

    tts(file_id)

    return Response(file_id)


@csrf_exempt
def tts(file_id):
    path = 'web/static/text/' + file_id + '.txt'
    txt = open(path, 'rt', encoding='UTF8')
    if txt:
        text = ''
        for line in txt.readlines():
            print(line)
            text += line
        tts_ko = gTTS(text=text, lang='ko')
        tts_path = 'web/static/sound/{}.mp3'.format(file_id)
        tts_ko.save(tts_path)

        return JsonResponse({'result': 'OK', 'data': tts_path})
    return JsonResponse({'result': 'ERROR'})
