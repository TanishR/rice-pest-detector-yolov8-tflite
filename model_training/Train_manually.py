from pathlib import Path
from PIL import Image
import os
import yaml
from ultralytics import YOLO
import numpy as np
import multiprocessing

# Function to resize images
def resize_images(directory, size=(640, 640)):
    for file in Path(directory).iterdir():
        if file.suffix.lower() in {'.jpg', '.jpeg', '.png'}:
            try:
                img = Image.open(file).resize(size, Image.LANCZOS)
                img.save(file)
                print(f"Resized: {file}")
            except Exception as e:
                print(f"Error resizing {file}: {e}")

# Function to clean unwanted files
def clean_directory(directory):
    for file in Path(directory).iterdir():
        if file.is_file() and file.suffix.lower() not in {'.jpg', '.jpeg', '.png', '.txt'}:
            try:
                os.remove(file)
                print(f"Removed: {file}")
            except Exception as e:
                print(f"Error removing {file}: {e}")

# Your main logic goes here
def main():
    # Set base dataset path
    base_path = Path(r"C:\Users\ASUS\Desktop\sanni")

    # Clean and resize for all dataset subsets
    for sub in ['train', 'valid']:
        path = base_path / sub
        clean_directory(path)
        resize_images(path)

    # YAML path and creation
    yaml_path = base_path / "data.yaml"
    if not yaml_path.exists():
        yaml_content = {
            'train': str(base_path / "train"),
            'val': str(base_path / "valid"),
            'nc': 1,
            'names': ['rice leaf roller']
        }
        with open(yaml_path, 'w') as f:
            yaml.dump(yaml_content, f)
        print(f"YAML created at: {yaml_path}")

    # Train YOLOv8 model
    model = YOLO("yolov8n.pt")
    model.train(data=str(yaml_path), epochs=50, imgsz=640, batch=16)

    # Save model manually (optional)
    model.save("yolov8_pest_detection_1.pt")

# Safe entry point for multiprocessing (Windows fix)
if __name__ == '__main__':
    multiprocessing.freeze_support()  # Optional for Windows EXE builds
    main()
