# 📝 ToDo App

Kotlin Multiplatform Mobile(KMM)과 Compose Multiplatform(CMP)을 사용하여 개발한 크로스 플랫폼 할일 관리 앱입니다.

## 주요 기능

- **할일 관리**: 할일 추가, 조회, 수정, 삭제
- **주간 달력**: 좌우 스와이프로 주차별 할일 확인
- **우선순위**: LOW, MEDIUM, HIGH 3단계 우선순위 설정
- **진행률 표시**: 당일 할일 완료율 시각화
- **완료 체크**: 할일 완료/미완료 토글
- **미니멀 디자인**: 직관적이고 깔끔한 UI/UX

## 기술 스택

### 아키텍처
- **MVVM Pattern**: View - ViewModel - Model 계층 분리
- **Clean Architecture**: 단방향 데이터 흐름
- **StateFlow**: 반응형 상태 관리

### 핵심 기술
- **Kotlin Multiplatform Mobile (KMM)**: iOS/Android 코드 공유
- **Compose Multiplatform**: 선언형 크로스 플랫폼 UI (Jetpack Compose UI 공유)
- **SQLDelight**: 데이터베이스
- **Koin**: 경량 의존성 주입 프레임워크
- **Voyager**: Compose Multiplatform 네비게이션
- **kotlinx-datetime**: 멀티플랫폼 날짜/시간 처리

## 지원 플랫폼

- **Android**
- **iOS**

## 시작하기
### 설치 및 실행

#### 1. 프로젝트 클론
```bash
git clone https://github.com/yourusername/todo-app-kmm.git
cd todo-app-kmm
```

#### 2. Android 실행
```bash
# Android Studio에서 프로젝트 열기
# composeApp 모듈 선택
# Run 버튼 클릭 또는

./gradlew :composeApp:installDebug
```

#### 3. iOS 실행
```bash
# CocoaPods 설치
cd iosApp
pod install

# Xcode에서 실행
open iosApp.xcworkspace
```

## 📂 프로젝트 구조

```
DownloadIfYouCan/
├── composeApp/               # 공유 UI 및 플랫폼별 코드
│   ├── commonMain/          # 공통 코드
│   │   ├── kotlin/
│   │   │   ├── ui/
│   │   │   │   ├── screen/      # 화면 (MainScreen, AddToDoScreen 등)
│   │   │   │   └── component/   # 재사용 컴포넌트
│   │   │   ├── viewModel/       # ViewModel (상태 관리)
│   │   │   ├── model/           # 데이터 모델
│   │   │   ├── di/              # 의존성 주입 모듈
│   │   │   └── theme/           # 테마 및 스타일
│   │   └── composeResources/    # 이미지, 폰트 등 리소스
│   ├── androidMain/         # Android 전용 코드
│   │   └── kotlin/
│   │       ├── MainActivity.kt
│   │       └── di/              # Android Context 주입
│   └── iosMain/             # iOS 전용 코드
│       └── kotlin/
│           └── di/              # iOS Driver 구현
├── shared/                  # 공유 비즈니스 로직
│   ├── commonMain/
│   │   └── kotlin/
│   │       └── database/        # SQLDelight Database
│   ├── androidMain/         # Android SQLite Driver
│   └── iosMain/            # iOS SQLite Driver
└── iosApp/                 # iOS 네이티브 진입점
    └── iosApp/
        └── iOSApp.swift
```

## 주요 화면

### 1. 메인 화면 (MainScreen)
- 선택 날짜 할일 표시 (default : 오늘)
- 주간 달력 (좌우 스와이프)
- 선택 날짜 진행률 표시
- 할일 목록
- 할일 추가 버튼
- 할일 삭제 기능

### 2. 할일 추가 화면 (AddToDoScreen)
- 날짜 선택 (Calendar)
- 제목 입력
- 내용 입력
- 우선순위 선택 (LOW/MEDIUM/HIGH)

### 3. 할일 상세 화면 (ToDoDetailScreen)
- 할일 상세 정보 조회
- 우선순위 시각 표시
- 수정 기능

### 4. 할일 수정 화면 (ModifyToDoScreen)
- 기존 정보 로드
- 날짜, 제목, 내용, 우선순위 수정

## 데이터베이스

```sql
CREATE TABLE Todo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    createdAt TEXT NOT NULL,
    dueDate TEXT NOT NULL,
    priority INTEGER NOT NULL DEFAULT 0,
    isDone INTEGER NOT NULL DEFAULT 0,
    completedAt TEXT
);
```

## 디자인
[![Figma](https://www.figma.com/design/K6GQmAV4w4W4C2zuRv0ynV/APP_UI?node-id=0-1&p=f)](링크)

## 실행화면
- Android
<img width="464" height="948" alt="image" src="https://github.com/user-attachments/assets/1910fd27-efec-40a2-8f3e-3d6c78154215" />

<img width="464" height="948" alt="image" src="https://github.com/user-attachments/assets/fc09fbbf-e950-48f3-a78d-db5bb7b793b8" />

<img width="464" height="948" alt="image" src="https://github.com/user-attachments/assets/6ac200e8-c537-4971-baeb-b5596e54bce8" />

- iOS
<img width="464" height="948" alt="스크린샷 2025-11-24 오후 9 13 00" src="https://github.com/user-attachments/assets/8f5b65d9-5433-4039-b974-4692ba5158c3" />

<img width="464" height="948" alt="스크린샷 2025-11-24 오후 9 19 40" src="https://github.com/user-attachments/assets/15474276-dac6-45a2-a653-ea1f8f09953d" />

<img width="464" height="948" alt="스크린샷 2025-11-24 오후 9 13 29" src="https://github.com/user-attachments/assets/df0b2c7d-a59a-4b68-8006-1c3321da60bd" />

## 개발 / 디자인
- **정찬** - [GitHub](https://github.com/cksckckcks)
