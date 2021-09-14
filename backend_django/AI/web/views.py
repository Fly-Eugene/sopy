from django.shortcuts import render
from rest_framework.decorators import api_view
import requests


# Create your views here.
@api_view(['POST'])
def book_ocr(request):
    txt_file = request.FILES['file']

    # txt_file = request.FILES

