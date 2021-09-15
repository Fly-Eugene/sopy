from django.urls import path
from . import views

urlpatterns = [
    path('ocr/', views.book_ocr),
    path('tts/', views.tts),
]
