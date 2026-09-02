# Rice Pest Detector 🌾

Real-time, on-device rice pest detection using a custom-trained **YOLOv8** model, converted to **TensorFlow Lite** and deployed in a fully offline **Android application**. No cloud dependency, no internet connection required — the mobile camera acts as the sole data acquisition and inference node.

Based on the paper *"Lightweight YOLOv8-TFLite on Edge Devices for Real-Time Rice Pest Detection Without Cloud Dependency"* (currently under review, *Computers and Electronics in Agriculture*, Elsevier).

---

## 🎯 Overview

Pest infestation is one of the largest contributors to rice yield loss globally. Existing detection solutions are largely cloud-dependent, slow, or impractical for field deployment. This project builds an **IoT-ready, edge-AI Android app** that detects and classifies 12 rice pest species (plus generic pest/non-pest categories) directly on-device, in real time, using the phone camera.

**Pipeline:** Dataset (IP-102, Kaggle) → Roboflow annotation → YOLOv8 training → PyTorch → ONNX → TensorFlow Lite conversion → Android integration via CameraX.

## 📊 Results

| Metric | Value |
|---|---|
| mAP@0.5 | **91.3%** |
| mAP@0.5:0.95 | 82.9% |
| Precision | 87.2% |
| Recall | 88.3% |

Trained for 50 epochs on YOLOv8s, batch size 16, 640×640 input, SGD with momentum, on an NVIDIA RTX 3060 (16GB).

Best-performing classes: Asiatic Rice Borer (0.978 mAP@0.5), Rice Water Weevil (0.966), Brown Plant Hopper (0.964).

## 🐛 Detected Classes

Rice Leaf Roller · Rice Leaf Caterpillar · Paddy Stem Maggot · Asiatic Rice Borer · Yellow Rice Borer · Rice Gall Midge · Brown Plant Hopper · Rice Stem Fly · Rice Water Weevil · Rice Leaf Hopper · Rice Shell Pest · Thrips · Pest (General) · Non-Pest (General)

## 🏗️ Architecture

```
Dataset Preparation → Model Training (YOLOv8) → Model Optimization
  (IP-102, Roboflow)     (PyTorch, Ultralytics)    (ONNX → TFLite, FP16)
                                                            ↓
                                              Android App (Kotlin, CameraX)
                                              On-device TFLite Inference
                                              Live Bounding Box Overlay
```

## 🛠️ Tech Stack

- **Model:** YOLOv8s (Ultralytics), trained from scratch
- **Training:** Python 3.11, PyTorch 2.6.0, CUDA 12.4
- **Annotation:** Roboflow
- **Conversion:** PyTorch → ONNX → TensorFlow Lite (FP16 quantized)
- **Mobile App:** Kotlin, Android Studio, CameraX, TensorFlow Lite Interpreter API

## 📱 Features

- Fully offline, on-device inference — no server, no internet required
- Real-time camera detection with live bounding box + confidence overlay
- Gallery image inference option
- Lightweight FP16-quantized model optimized for mobile hardware

## 📂 Project Structure

```
├── model_training/        # YOLOv8 training scripts, config
├── model_conversion/       # ONNX + TFLite conversion pipeline
├── android_app/             # Android Studio project (Kotlin)
│   ├── app/
│   │   ├── src/main/assets/   # .tflite model
│   │   └── src/main/java/     # App source code
└── README.md
```

## 🚀 Getting Started

### Requirements
- Android Studio (latest stable)
- Android device/emulator running API 24+
- Python 3.11+ (only needed if retraining the model)

### Run the App
1. Clone the repo:
```bash
   git clone https://github.com/TanishR/rice-pest-detector-yolov8-tflite.git
```
2. Open `android_app/` in Android Studio
3. Let Gradle sync, then build and run on a device or emulator
4. Grant camera permission when prompted

### Retrain the Model (optional)
Training scripts and the dataset split are in `model_training/`. See inline comments for the Ultralytics YOLOv8 training configuration used (50 epochs, SGD, 640×640 input).

## 📄 Citation

If you use this work, please cite the associated paper (details to be added once published):

> T. Ranjan, Y. Kashyap, R. K. Sinha, S. S. Sahu, "Lightweight YOLOv8-TFLite on Edge Devices for Real-Time Rice Pest Detection Without Cloud Dependency," *Computers and Electronics in Agriculture* (Elsevier), under review.

## 👤 Author

**Tanish Ranjan**
Birla Institute of Technology Mesra
[LinkedIn](https://www.linkedin.com/in/tanishranjan52/) · [GitHub](https://github.com/TanishR)

## 📝 License

Specify a license here (e.g. MIT) once you decide how you want others to use this code.
