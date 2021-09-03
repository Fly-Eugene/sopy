# 잊기 전에 쓰는 SUB01 복기



다른 트랙은 어떤지 모르겠으나 인공지능 음성 트랙은 명세서가 조금 미비하여 개발 환경을 구성하는데 어려움이 있었음으로 정리한다.



## 개발 환경 구성



개발 환경을 구성하자.



1. 아나콘다 설치

   아나콘다 홈페이지에서 다운받아서 설치한다.

   https://www.anaconda.com/products/individual#Downloads

   

2. CUDA 설치

   CUDA는 NVIDIA에서 제공하는 소프트웨어로 GPU를 사용하게 도와준다.

​		https://developer.nvidia.com/cuda-downloads



3. 가상환경 생성 및 활성화

	```shell
	conda create -n [NAME] python=3.7 #[NAME] : 가상환경 이름
	conda activate [NAME]
	```



4. 프레임워크 및 라이브러리 설치

	```shell
	conda install pytorch torchvision torchaudio cudatoolkit=[본인의 쿠다 버전] -c pytorch
	pip install tensorflow-gpu
	```



5. 스켈레톤 프로젝트 다운로드

   명세에 있는 git repository에 들어가서 git clone 한다.



## REQ 01. Pytorch로 image_captioning 구현하기



사실 이 내용은 pytorch tutorial에 잘 나와 있어서 참고하면 좋다.

https://tutorials.pytorch.kr/beginner/blitz/cifar10_tutorial.html?highlight=image

근데 사실 스켈레톤 프로젝트 상에 Image captiong이 잘 구현되어 있기 때문에 이것은 학습용도의 요구사항이라고 보면된다.

그래서 그냥 Colab에서 진행했다.



### REQ 01-01. 데이터셋 준비 및 전처리

data들은 이미 준비된게 있어서 download한다.

```python
# [0,1] 범위의 데이터셋을 [-1, -1] 범위의 값으로 normalize하도록 transform 정의
transform = transforms.Compose(
    [transforms.ToTensor(),
     transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))])


# torchvision으로 CIFAR10 trainset load, trainset dataloader 정의
trainset = torchvision.datasets.CIFAR10(root='./data', train=True,
                                        download=True, transform=transform)
trainloader = torch.utils.data.DataLoader(trainset, batch_size=128,
                                          shuffle=True, num_workers=2)

# torchvision으로 CIFAR10 testset load, trainset dataloader 정의
testset = torchvision.datasets.CIFAR10(root='./data', train=False,
                                       download=True, transform=transform)
testloader = torch.utils.data.DataLoader(testset, batch_size=128,
                                         shuffle=False, num_workers=2)
# CIFAR10의 10개의 class 정의
classes = ('plane', 'car', 'bird', 'cat',
           'deer', 'dog', 'frog', 'horse', 'ship', 'truck')
```



### REQ 01-02. 분류기 모델 설계



```python
import torch.nn as nn
import torch.nn.functional as F

# Req. 1-2	분류기 모델 설계
class Classifier(nn.Module):
    def __init__(self):
        super(Classifier, self).__init__()
        
        # 이미지 3-channel 입력
        self.conv1 = nn.Conv2d(3, 16, 5)
        self.pool = nn.MaxPool2d(2, 2)

        # 최종 10개의 class에 대한 확률
        self.fc1 = nn.Linear(16*14*14, 128)
        self.fc2 = nn.Linear(128, 10)
    

    def forward(self, x):
       
        x = self.pool(F.relu(self.conv1(x)))
        x = x.view(-1, 16*14*14)
        x = F.relu(self.fc1(x))
        x = self.fc2(x)
        
        return x

classifier = Classifier()
```



### REQ 01-03. Loss function 및 optimizer 정의



```python
import torch.optim as optim

# Req. 1-3	Loss function 및 optimizer정의

# loss function
criterion = nn.CrossEntropyLoss()
# optimizer
optimizer = optim.SGD(classifier.parameters(), lr=0.001, momentum=0.9)
```



### REQ 01-04. 모델 학습



```python
# Req. 1-4	모델 학습
# model을 device에 올린다 (GPU or CPU) 
# 구현 완료 상태
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
classifier = classifier.to(device)

epochs = 10  #define epochs

# 1) for문으로 epochs 만큼 반복
for epoch in range(epochs):  # epochs 횟수만큼 반복
    
    # loss값 누적
    running_loss = 0.0
    
    # 2) for문으로 trainset이 저장되어 있는 trainloader에서 배치 사이즈 만큼씩 샘플링하여 data load
    for i, data in enumerate(trainloader, 0):
        
        # 3) load한 data에서 input 값과 label로 분리하여 저장
        inputs, labels = data
       
        # 4) 각각의 값을 device에 올린다 (GPU or CPU)
        inputs = inputs.to(device)
        labels = labels.to(device)

        # 5) optimizer에서 gradient 값 0으로 초기화
        optimizer.zero_grad()

        # 6) model에 input값을 입력하여 forward propagation
        outputs = classifier(inputs)

        # 7)  loss function으로 예측값과 label 비교
        loss = criterion(outputs, labels)
        
        # 8) loss 값 backpropagation 하여 gradient 계산
        loss.backward()
        
        # 9) 계산된 gradient를 모두 parameter에 적용
        optimizer.step()

        # 10) loss 값을 합하여 일정 주기(ex.2000 batch) 마다 평균 loss 값 출력 후 초기화
        running_loss += loss.item()
        if i % 2000 == 1999:
          print('[%d, %5d] loss: %.3f' % (epoch + 1, i + 1, running_loss / 2000))

        running_loss = 0.0


# 12) torch.save로 학습이 마친 이후 모델 저장        
# PATH = '경로 설정'
# torch.save(classifier.state_dict(), PATH)

print('Finished Training')
```



### REQ 01-05. 모델 테스트



```python
# Req. 1-5	모델 테스트
# 만약 저장한 모델을 load해야 한다면, 모델의 인스턴스를 생성하고, 모델의 weight이 저장되어 있는 .ckpt 파일을 모델에 load
# new_classifier = Classifier()
# new_classifier.load_state_dict(torch.load('model_weight.ckpt'))   # 파일의 PATH를 넣어주면 된다.
# new_classifier.to(device)

# 1) 모델을 evaluation 모드로 전환

correct = 0
total = 0
# 2) with torch.no_grad로 gradient 계산을 제외
with torch.no_grad():

    # 3) for문으로 testset에 저장되어 있는 testloader에서 배치 사이즈 만큼씩 샘플링하여 data load
    for data in testloader:
        
         # 4) load한 data에서 input 값과 label로 분리하여 저장
        images, labels = data
       
        # 5) 각각의 값을 device에 올린다 (GPU or CPU)
        images = images.to(device)
        labels = labels.to(device)

        # 6) model에 input값을 입력하여 forward propagation
        outputs = classifier(images)

        # 7) 예측한 값들 중 가장 높은 확률의 class 선택
        _, predicted = torch.max(outputs.data, 1)

        # 8) label과 예측한 class 비교하여 정답 확인
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        

# 9) 정답률 출력
print('Accuracy of the network on the 10000 test images: %d %%' % (
    100 * correct / total
))
```



## REQ 02. requirements.txt 작성



### REQ 02-01. requirements.txt 작성

/speak_iamge/IC 디렉토리에 만들어야한다.



1. pyyaml==5.3.1

   ymal 파일을 읽고 쓰기 위한 라이브러리

   

2. yacs==0.1.8

   yacs 파일을 사용하기 위한 라이브러리

   

3. opencv-contrib-python==4.4.0.46

   contrib 모듈(래퍼 패키지)

   

4. requests==2.25.0

   파이썬에서 HTTP Request를 보내기 위한 라이브러리



### REQ 02-02. requirements 설치

**이때 반드시 conda activate된 상태여야 conda 가상환경에서 동작한다.** 

마찬가지로 /speak_iamge/IC 디렉토리에서 설치한다.



## REQ 03. Image Captioning 실행 및 결과 확인



### REQ 03-01. Image Captioning 에 필요한 build file 생성



build를 하기전에 vscode build tool을 다운받아서 설치한다.

https://www.visualstudio.com/ko/thank-you-downloading-visual-studio/?sku=BuildTools&rel=15

그 다음 cmd 창에서 아래의 명령어를 실행하여 build tool을 실행하고

`call "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars64.bat"`

conda activate를 해서 가상환경을 킨 후에

vqa_origin 디렉토리에서

`python setup.py build develop` 명령어로 build한다.



### REQ 03-02. 클래스 이해



1. FeatureExtractor 클래스: 이미지 내에 의미 있는 후보 객체 100개를 선정하여 해당 객체의 bounding box와 특징 벡터 반환
2. Caption_Model 클래스: 100개의 후보 객체의 특징 벡터를 바탕으로 문장 생성



### REQ 03-03. 이미지 캡션 결과 출력



출력만 하는거라 어렵지는 않았다.



```python
if __name__ == '__main__':
    # Caption_Model 클래스에 대한 인스턴스 생성
    caption_model = Caption_Model()
    # 모델에 입력할 이미지 경로 정의
    img_path = './soccer.jpg'
    # cv2.imread 함수를 이용하여 이미지 불러오기
    img_feature = cv2.imread(img_path)
    # 캡션 출력
    print(caption_model.inference(img_path))
    # 사진 보여주기
    cv2.imshow('Sample', img_feature)
    # 요거 안하면 사진 떴다가 바로 꺼짐
    cv2.waitKey()
```







