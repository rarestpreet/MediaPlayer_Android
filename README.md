# Media Player Android

An Android Media Player application that allows users to play audio and video files directly on their device. The app provides a simple and user-friendly interface for browsing, selecting, and playing media files stored on the device.

---

## Features

- Play audio and video files from the device
- Pause and resume media playback
- Skip forward and backward
- Browse media files from storage
- Volume control support
- Simple and clean user interface

---

## Tech Stack

- **Language:** Kotlin
- **Platform:** Android
- **IDE:** Android Studio
- **Media APIs:** Android `MediaPlayer` / ExoPlayer
- **Build Tool:** Gradle

> Android media apps commonly rely on playback libraries such as ExoPlayer, which supports many audio/video formats and streaming protocols.

---

## Project Structure

```text
MediaPlayer_Android
│
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/example/mediaplayer
│   │   │   │   ├── MainActivity
│   │   │   │   ├── MediaPlayerService
│   │   │   │   └── PlayerController
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   ├── drawable
│   │   │   │   └── values
│   │   │   └── AndroidManifest.xml
│
└── build.gradle
```

---

## 🚀 Getting Started

### 1️. Clone the repository

```bash
git clone https://github.com/rarestpreet/MediaPlayer_Android.git
```

### 2️. Open in Android Studio

1. Open **Android Studio**
2. Click **Open Project**
3. Select the cloned project folder

### 3️. Build and Run

1. Connect an Android device or start an emulator
2. Click **Run ▶** in Android Studio

---

## Permissions

This app may require permissions to access media files on the device, depending on your Android version:

- **Android 13+ (API 33+):** `READ_MEDIA_AUDIO`, `READ_MEDIA_VIDEO`
- **Android 12 and below:** `READ_EXTERNAL_STORAGE`

Make sure permissions are declared in `AndroidManifest.xml` and requested at runtime where required.

---

## Learning Objectives

This project helps developers understand:

- Android media playback
- Handling audio/video files
- Implementing UI controls for media
- Managing Android permissions for storage
- Building a basic Android media application

---

## Future Improvements

- Playlist support
- Material UI redesign

---

## Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Submit a Pull Request
