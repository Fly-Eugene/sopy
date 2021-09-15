from django.shortcuts import render
from rest_framework.decorators import api_view
import requests

# tts module
from gtts import gTTS
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@api_view(['POST'])
def book_ocr(request):
    txt_file = request.FILES['file']

    # txt_file = request.FILES

@csrf_exempt
def tts(request):
    path = ''
    txt = open('test.txt', 'rt', encoding='UTF8')
    if txt:
        text = ''
        for line in txt.readlines():
            text += line
        tts_ko = gTTS(text='text', lang='ko')
        tts_path = 'output.mp3'
        tts_ko.save(tts_path)

        return JsonResponse({'result': 'OK','data': tts_path})
    return JsonResponse({'result': 'ERROR'})